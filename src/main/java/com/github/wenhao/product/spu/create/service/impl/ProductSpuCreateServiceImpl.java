package com.github.wenhao.product.spu.create.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.wenhao.product.brand.entity.po.ProductBrand;
import com.github.wenhao.product.category.entity.po.ProductCategory;
import com.github.wenhao.product.exception.BusinessException;
import com.github.wenhao.product.property.entity.po.ProductProperty;
import com.github.wenhao.product.spu.create.mapper.ProductSpuCreateMapper;
import com.github.wenhao.product.spu.create.service.ProductSpuCreateService;
import com.github.wenhao.product.spu.create.vo.ProductSkuSaveReqVO;
import com.github.wenhao.product.spu.create.vo.ProductSpuCreateReqVO;
import com.github.wenhao.product.spu.entity.po.ProductSku;
import com.github.wenhao.product.spu.entity.po.ProductSpu;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 商品SPU创建Service实现类
 */
@Service
@RequiredArgsConstructor
public class ProductSpuCreateServiceImpl implements ProductSpuCreateService {

    private static final Long PARENT_ID_ROOT = 0L;
    private static final Long PROPERTY_ID_DEFAULT = 0L;
    private static final String PROPERTY_NAME_DEFAULT = "默认";
    private static final Long VALUE_ID_DEFAULT = 0L;
    private static final String VALUE_NAME_DEFAULT = "默认";

    private final ProductSpuCreateMapper productSpuCreateMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createSpu(ProductSpuCreateReqVO createReqVO) {
        // 2. 参数校验：根据请求参数中的商品分类编号进行校验
        ProductCategory category = validateCategory(createReqVO.getCategoryId());

        // 3. 参数校验：根据请求参数中的商品品牌编号进行校验
        validateBrand(createReqVO.getBrandId());

        // 4. 参数校验：对请求参数中的SKU列表进行完整性、规范性校验
        validateSkus(createReqVO.getSkus(), createReqVO.getSpecType());

        // 5. 数据转换：将请求参数转换为数据库实体对象
        ProductSpu productSpu = convertToSpu(createReqVO);

        // 6. 数据初始化：初始化数据转换后的商品SPU数据对象
        initSpuData(productSpu, createReqVO.getSkus(), createReqVO.getStatus());

        // 7. 数据库操作：将商品SPU数据对象插入数据库
        productSpuCreateMapper.insert(productSpu);

        // 8. 数据库操作：将SKU列表转换为商品SKU entity列表并批量插入数据库
        List<ProductSku> skuList = convertToSkuList(productSpu.getId(), createReqVO.getSkus());
        productSpuCreateMapper.batchInsertSku(skuList);

        // 9. 方法返回：返回数据库自动生成的商品SPU entity-SPU编号
        return productSpu.getId();
    }

    /**
     * 校验商品分类
     */
    private ProductCategory validateCategory(Long categoryId) {
        ProductCategory category = productSpuCreateMapper.selectCategoryById(categoryId);
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

        return category;
    }

    /**
     * 计算分类层级
     */
    private int calculateCategoryLevel(Long categoryId) {
        int level = 0;
        Long currentId = categoryId;
        while (currentId != null && !PARENT_ID_ROOT.equals(currentId)) {
            ProductCategory category = productSpuCreateMapper.selectCategoryById(currentId);
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
        ProductBrand brand = productSpuCreateMapper.selectBrandById(brandId);
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
                List<ProductProperty> existProperties = productSpuCreateMapper.selectPropertyByIds(propertyIdList);
                if (existProperties == null || existProperties.size() != propertyIdList.size()) {
                    throw new BusinessException("1_008_003_000", "属性项不存在");
                }
            }
        }
    }

    /**
     * 转换为SPU实体
     */
    private ProductSpu convertToSpu(ProductSpuCreateReqVO createReqVO) {
        ProductSpu productSpu = new ProductSpu();
        BeanUtils.copyProperties(createReqVO, productSpu);
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
     * 转换为SKU列表
     */
    private List<ProductSku> convertToSkuList(Long spuId, List<ProductSkuSaveReqVO> skus) {
        return skus.stream()
            .map(skuVO -> {
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
            })
            .collect(Collectors.toList());
    }
}