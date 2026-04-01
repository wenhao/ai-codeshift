package com.github.wenhao.product.property.delete.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.wenhao.product.exception.BusinessException;
import com.github.wenhao.product.property.delete.mapper.ProductPropertyDeleteMapper;
import com.github.wenhao.product.property.delete.service.ProductPropertyDeleteService;
import com.github.wenhao.product.property.entity.po.ProductProperty;
import com.github.wenhao.product.property.update.mapper.ProductSkuPropertyUpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 商品属性项删除Service实现类
 */
@Service
@RequiredArgsConstructor
public class ProductPropertyDeleteServiceImpl implements ProductPropertyDeleteService {

    private final ProductPropertyDeleteMapper productPropertyDeleteMapper;
    private final ProductSkuPropertyUpdateMapper productSkuPropertyUpdateMapper;

    @Override
    public Boolean deleteProperty(Long id) {
        // 1. 参数校验：根据属性项编号查询数据库校验属性项是否真实存在
        ProductProperty property = productPropertyDeleteMapper.selectOne(
            new LambdaQueryWrapper<ProductProperty>()
                .eq(ProductProperty::getId, id)
        );
        if (property == null) {
            throw new BusinessException("1_008_003_000", "商品属性项不存在");
        }

        // 2. 参数校验：查询属性项下是否存在属性值
        Long count = productSkuPropertyUpdateMapper.countByPropertyId(id);
        if (count > 0) {
            throw new BusinessException("1_008_003_002", "属性项下存在属性值，无法删除");
        }

        // 3. 数据库操作：根据属性项编号删除属性项
        productPropertyDeleteMapper.deleteById(id);

        return true;
    }
}