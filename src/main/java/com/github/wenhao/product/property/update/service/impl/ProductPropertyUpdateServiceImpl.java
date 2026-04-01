package com.github.wenhao.product.property.update.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.wenhao.product.exception.BusinessException;
import com.github.wenhao.product.property.entity.po.ProductProperty;
import com.github.wenhao.product.property.update.mapper.ProductPropertyUpdateMapper;
import com.github.wenhao.product.property.update.mapper.ProductSkuPropertyUpdateMapper;
import com.github.wenhao.product.property.update.service.ProductPropertyUpdateService;
import com.github.wenhao.product.property.update.vo.ProductPropertyUpdateReqVO;
import com.github.wenhao.product.spu.entity.po.ProductSku;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品属性项更新Service实现类
 */
@Service
@RequiredArgsConstructor
public class ProductPropertyUpdateServiceImpl implements ProductPropertyUpdateService {

    private final ProductPropertyUpdateMapper productPropertyUpdateMapper;
    private final ProductSkuPropertyUpdateMapper productSkuPropertyUpdateMapper;

    @Override
    public Boolean updateProperty(ProductPropertyUpdateReqVO updateReqVO) {
        // 1. 参数校验：根据属性项编号查询数据库校验属性项是否真实存在
        ProductProperty property = productPropertyUpdateMapper.selectOne(
            new LambdaQueryWrapper<ProductProperty>()
                .eq(ProductProperty::getId, updateReqVO.getId())
        );
        if (property == null) {
            throw new BusinessException("1_008_003_000", "属性项不存在");
        }

        // 2. 参数校验：校验属性项名称是否重复
        ProductProperty existProperty = productPropertyUpdateMapper.selectOne(
            new LambdaQueryWrapper<ProductProperty>()
                .eq(ProductProperty::getName, updateReqVO.getName())
        );
        if (existProperty != null && !existProperty.getId().equals(updateReqVO.getId())) {
            throw new BusinessException("1_008_003_001", "属性项的名称已存在");
        }

        // 3. 数据转换：将请求参数转换为数据库实体对象
        ProductProperty productProperty = new ProductProperty();
        BeanUtils.copyProperties(updateReqVO, productProperty);

        // 4. 数据库操作：更新数据库
        productPropertyUpdateMapper.updateById(productProperty);

        // 5. 数据库操作：按需更新所有商品SKU的属性
        List<ProductSku> skuList = productSkuPropertyUpdateMapper.selectList(
            new LambdaQueryWrapper<ProductSku>()
        );
        if (skuList != null && !skuList.isEmpty()) {
            for (ProductSku sku : skuList) {
                List<ProductSku.Property> properties = sku.getProperties();
                if (properties != null && !properties.isEmpty()) {
                    for (ProductSku.Property p : properties) {
                        if (p.getPropertyId() != null && p.getPropertyId().equals(updateReqVO.getId())) {
                            p.setPropertyName(updateReqVO.getName());
                        }
                    }
                }
            }
            productSkuPropertyUpdateMapper.batchUpdate(skuList);
        }

        return true;
    }
}