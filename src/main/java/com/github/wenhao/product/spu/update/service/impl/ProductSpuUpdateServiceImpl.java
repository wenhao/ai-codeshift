package com.github.wenhao.product.spu.update.service.impl;

import com.github.wenhao.product.brand.entity.po.ProductBrand;
import com.github.wenhao.product.category.entity.po.ProductCategory;
import com.github.wenhao.product.exception.BusinessException;
import com.github.wenhao.product.property.entity.po.ProductProperty;
import com.github.wenhao.product.spu.create.vo.ProductSkuSaveReqVO;
import com.github.wenhao.product.spu.entity.po.ProductSku;
import com.github.wenhao.product.spu.entity.po.ProductSpu;
import com.github.wenhao.product.spu.update.mapper.ProductSpuUpdateMapper;
import com.github.wenhao.product.spu.update.service.ProductSpuUpdateService;
import com.github.wenhao.product.spu.update.vo.ProductSpuUpdateReqVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 商品SPU更新Service实现类
 */
@Service
@RequiredArgsConstructor
public class ProductSpuUpdateServiceImpl implements ProductSpuUpdateService {

    private static final Long PARENT_ID_ROOT = 0L;
    private static final Long PROPERTY_ID_DEFAULT = 0L;
    private static final String PROPERTY_NAME_DEFAULT = "默认";
    private static final Long VALUE_ID_DEFAULT = 0L;
    private static final String VALUE_NAME_DEFAULT = "默认";

    private final ProductSpuUpdateMapper productSpuUpdateMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateSpu(ProductSpuUpdateReqVO updateReqVO) {
        // 2. 参数校验：根据请求参数中的商品编号校验是否存在
        ProductSpu existingSpu = validateSpuExists(updateReqVO.getId());

        // 3. 参数校验：根据请求参数中的商品分类编号进行校验
        validateCategory(updateReqVO.getCategoryId());

        // 4. 参数校验：根据请求参数中的商品品牌编号进行校验
        validateBrand(updateReqVO.getBrandId());

        // 5. 参数校验：对请求参数中的SKU列表进行完整性、规范性校验
        validateSkus(updateReqVO.getSkus(), updateReqVO.getSpecType());

        // 6. 数据转换：将请求参数转换为数据库实体对象
        ProductSpu productSpu = convertToSpu(updateReqVO);

        // 7. 数据初始化：初始化数据转换后的商品SPU数据对象
        initSpuData(productSpu, updateReqVO.getSkus(), updateReqVO.getStatus());

        // 8. 数据库操作：更新商品SPU数据对象到数据库
        productSpuUpdateMapper.updateById(productSpu);

        // 9. 数据库操作：根据已在数据库保存的商品SPU编号，批量更新对应的SKU信息
        updateSkus(updateReqVO.getId(), updateReqVO.getSkus());

        // 10. 方法返回：返回true
        return true;
    }

    /**
     * 校验SPU是否存在
     */
    private ProductSpu validateSpuExists(Long spuId) {
        ProductSpu spu = productSpuUpdateMapper.selectSpuById(spuId);
        if (spu == null) {
            throw new BusinessException("1_008_005_000", "商品 SPU 不存在");
        }
        return spu;
    }

    /**
     * 校验商品分类
     */
    private void validateCategory(Long categoryId) {
        ProductCategory category = productSpuUpdateMapper.selectCategoryById(categoryId);
        if (category == null) {
            throw new BusinessException("1_008_001_000", "商品分类不存在");
        }
        if (category.getStatus() != null && category.getStatus() != 1) {
            throw new BusinessException("1_008_001_004", String.format("商品分类(%s)已禁用，无法使用", category.getName()));
        }

        // 校验分类层级：必须使用第二级的商品分类及以下
        int level = calculateCategoryLevel(categoryId);
        if (level < 2) {
            throw new BusinessException("1_008_005_001", "商品分类不正确，原因：必须使用第二级的商品分类及以下");
        }
    }

    /**
     * 计算分类层级
     */
    private int calculateCategoryLevel(Long categoryId) {
        int level = 0;
        Long currentId = categoryId;
        while (currentId != null && !PARENT_ID_ROOT.equals(currentId)) {
            ProductCategory category = productSpuUpdateMapper.selectCategoryById(currentId);
            if (category == null) {
                break;
            }
            level++;
            currentId = category.getParentId();
        }
        return level;
    }

    /**
     * 校验商品品牌
     */
    private void validateBrand(Long brandId) {
        ProductBrand brand = productSpuUpdateMapper.selectBrandById(brandId);
        if (brand == null) {
            throw new BusinessException("1_008_002_000", "品牌不存在");
        }
        if (brand.getStatus() != null && brand.getStatus() != 1) {
            throw new BusinessException("1_008_002_001", "品牌已禁用");
        }
    }

    /**
     * 校验SKU列表
     */
    private void validateSkus(List<ProductSkuSaveReqVO> skus, Boolean specType) {
        if (skus == null || skus.isEmpty()) {
            throw new BusinessException("1_008_006_000", "商品 SKU 不存在");
        }

        // 若传入的单规格为false时，为SKU列表中第1个SKU的单规格商品添加默认的1个属性数组
        if (!Boolean.TRUE.equals(specType)) {
            ProductSkuSaveReqVO firstSku = skus.get(0);
            if (firstSku.getProperties() == null || firstSku.getProperties().isEmpty()) {
                ProductSkuSaveReqVO.Property property = new ProductSkuSaveReqVO.Property();
                property.setPropertyId(PROPERTY_ID_DEFAULT);
                property.setPropertyName(PROPERTY_NAME_DEFAULT);
                property.setValueId(VALUE_ID_DEFAULT);
                property.setValueName(VALUE_NAME_DEFAULT);
                firstSku.setProperties(java.util.Collections.singletonList(property));
            }
        }

        // 收集所有属性编号
        Set<Long> allPropertyIds = new HashSet<>();
        Set<Long> allValueIds = new HashSet<>();
        Integer propertySize = null;

        for (ProductSkuSaveReqVO sku : skus) {
            List<ProductSkuSaveReqVO.Property> properties = sku.getProperties();
            if (properties == null) {
                continue;
            }

            // 校验每个SKU的属性数组大小应该相等
            if (propertySize == null) {
                propertySize = properties.size();
            } else if (properties.size() != propertySize) {
                throw new BusinessException("1_008_006_002", "一个 SPU 下的每个 SKU，其属性项必须一致");
            }

            for (ProductSkuSaveReqVO.Property property : properties) {
                if (property.getPropertyId() != null) {
                    // 校验属性编号是否重复
                    if (!allPropertyIds.add(property.getPropertyId())) {
                        throw new BusinessException("1_008_006_001", "商品 SKU 的属性组合存在重复");
                    }
                }
                if (property.getValueId() != null) {
                    // 校验属性值编号是否重复
                    if (!allValueIds.add(property.getValueId())) {
                        throw new BusinessException("1_008_006_003", "一个 SPU 下的每个 SKU，必须不重复");
                    }
                }
            }
        }

        // 校验属性编号是否存在
        if (allPropertyIds != null && !allPropertyIds.isEmpty()) {
            List<Long> propertyIdList = new ArrayList<>(allPropertyIds);
            // 移除默认属性ID
            propertyIdList.removeIf(id -> PROPERTY_ID_DEFAULT.equals(id));
            if (propertyIdList != null && !propertyIdList.isEmpty()) {
                List<ProductProperty> existProperties = productSpuUpdateMapper.selectPropertyByIds(propertyIdList);
                if (existProperties == null || existProperties.size() != propertyIdList.size()) {
                    throw new BusinessException("1_008_003_000", "属性项不存在");
                }
            }
        }
    }

    /**
     * 转换为SPU实体
     */
    private ProductSpu convertToSpu(ProductSpuUpdateReqVO updateReqVO) {
        ProductSpu productSpu = new ProductSpu();
        BeanUtils.copyProperties(updateReqVO, productSpu);
        return productSpu;
    }

    /**
     * 初始化SPU数据
     */
    private void initSpuData(ProductSpu productSpu, List<ProductSkuSaveReqVO> skus, Integer status) {
        // 将SKU列表中最小的销售价格作为商品SPU的初始商品价格
        Integer minPrice = skus.stream()
            .map(ProductSkuSaveReqVO::getPrice)
            .filter(p -> p != null)
            .min(Integer::compareTo)
            .orElse(0);
        productSpu.setPrice(minPrice);

        // 将SKU列表中最小的市场价作为商品SPU的初始市场价
        Integer minMarketPrice = skus.stream()
            .map(ProductSkuSaveReqVO::getMarketPrice)
            .filter(p -> p != null)
            .min(Integer::compareTo)
            .orElse(0);
        productSpu.setMarketPrice(minMarketPrice);

        // 将SKU列表中最小的成本价作为商品SPU的初始成本价
        Integer minCostPrice = skus.stream()
            .map(ProductSkuSaveReqVO::getCostPrice)
            .filter(p -> p != null)
            .min(Integer::compareTo)
            .orElse(0);
        productSpu.setCostPrice(minCostPrice);

        // 将SKU列表中的库存加总作为商品SPU的初始库存
        Integer totalStock = skus.stream()
            .map(ProductSkuSaveReqVO::getStock)
            .filter(s -> s != null)
            .reduce(0, Integer::sum);
        productSpu.setStock(totalStock);

        // 若商品状态为null则设置商品销量和浏览量都为0
        if (status == null) {
            productSpu.setSalesCount(0);
            productSpu.setBrowseCount(0);
        }
    }

    /**
     * 更新SKU列表
     */
    private void updateSkus(Long spuId, List<ProductSkuSaveReqVO> skus) {
        // 根据已在数据库保存的商品SPU编号，查询所有的商品SKU
        List<ProductSku> existingSkus = productSpuUpdateMapper.selectSkuBySpuId(spuId);

        // 将每一个商品SKU按照属性值编号（valueId）从小到大排序，把所有属性值的编号依次拼接成一个完整字符串作为每个SKU的key
        Map<String, Long> existingSkuKeyMap = new HashMap<>();
        if (existingSkus != null) {
            for (ProductSku sku : existingSkus) {
                String key = buildSkuKey(sku.getProperties());
                existingSkuKeyMap.put(key, sku.getId());
            }
        }

        // 遍历SKU列表
        List<ProductSku> skuListToInsert = new ArrayList<>();
        List<ProductSku> skuListToUpdate = new ArrayList<>();

        for (ProductSkuSaveReqVO skuVO : skus) {
            ProductSku sku = convertToSku(spuId, skuVO);
            String key = buildSkuKeyFromReqVO(skuVO.getProperties());

            // key值与上述相等的则更新
            if (existingSkuKeyMap.containsKey(key)) {
                sku.setId(existingSkuKeyMap.get(key));
                skuListToUpdate.add(sku);
                existingSkuKeyMap.remove(key);
            } else {
                // key值与上述不相等的则插入
                skuListToInsert.add(sku);
            }
        }

        // 批量更新存在的SKU
        if (!skuListToUpdate.isEmpty()) {
            productSpuUpdateMapper.batchUpdateSku(skuListToUpdate);
        }

        // 批量插入新SKU
        if (!skuListToInsert.isEmpty()) {
            productSpuUpdateMapper.batchInsertSku(skuListToInsert);
        }

        // 剩下的SKU需要删除
        for (Long skuIdToDelete : existingSkuKeyMap.values()) {
            productSpuUpdateMapper.deleteSkuById(skuIdToDelete);
        }
    }

    /**
     * 构建SKU key (用于 ProductSku.Property)
     */
    private String buildSkuKey(List<ProductSku.Property> properties) {
        if (properties == null || properties.isEmpty()) {
            return "";
        }
        return properties.stream()
            .sorted(Comparator.comparing(p -> p.getValueId() != null ? p.getValueId() : 0L))
            .map(p -> String.valueOf(p.getValueId() != null ? p.getValueId() : 0L))
            .collect(Collectors.joining("_"));
    }

    /**
     * 构建SKU key (用于 ProductSkuSaveReqVO.Property)
     */
    private String buildSkuKeyFromReqVO(List<ProductSkuSaveReqVO.Property> properties) {
        if (properties == null || properties.isEmpty()) {
            return "";
        }
        return properties.stream()
            .sorted(Comparator.comparing(p -> p.getValueId() != null ? p.getValueId() : 0L))
            .map(p -> String.valueOf(p.getValueId() != null ? p.getValueId() : 0L))
            .collect(Collectors.joining("_"));
    }

    /**
     * 转换为SKU实体
     */
    private ProductSku convertToSku(Long spuId, ProductSkuSaveReqVO skuVO) {
        ProductSku sku = new ProductSku();
        sku.setSpuId(spuId);
        BeanUtils.copyProperties(skuVO, sku);
        // 转换Property类型
        if (skuVO.getProperties() != null) {
            List<ProductSku.Property> properties = skuVO.getProperties().stream()
                .map(p -> {
                    ProductSku.Property property = new ProductSku.Property();
                    property.setPropertyId(p.getPropertyId());
                    property.setPropertyName(p.getPropertyName());
                    property.setValueId(p.getValueId());
                    property.setValueName(p.getValueName());
                    return property;
                })
                .collect(Collectors.toList());
            sku.setProperties(properties);
        }
        return sku;
    }
}