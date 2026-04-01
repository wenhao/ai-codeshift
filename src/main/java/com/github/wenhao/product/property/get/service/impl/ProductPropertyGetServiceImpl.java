package com.github.wenhao.product.property.get.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.wenhao.product.exception.BusinessException;
import com.github.wenhao.product.property.entity.po.ProductProperty;
import com.github.wenhao.product.property.get.controller.vo.ProductPropertyGetRespVO;
import com.github.wenhao.product.property.get.mapper.ProductPropertyGetMapper;
import com.github.wenhao.product.property.get.service.ProductPropertyGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 商品属性项查询Service实现类
 */
@Service
@RequiredArgsConstructor
public class ProductPropertyGetServiceImpl implements ProductPropertyGetService {

    private final ProductPropertyGetMapper productPropertyGetMapper;

    @Override
    public ProductPropertyGetRespVO getProperty(Long id) {
        // 1. 数据库操作：根据属性项编号查询唯一的商品属性项
        LambdaQueryWrapper<ProductProperty> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProductProperty::getId, id);
        ProductProperty productProperty = productPropertyGetMapper.selectOne(wrapper);

        if (Objects.isNull(productProperty)) {
            throw new BusinessException("商品属性项不存在");
        }

        // 2. 数据转换：将entity转换为响应参数
        ProductPropertyGetRespVO respVO = new ProductPropertyGetRespVO();
        BeanUtils.copyProperties(productProperty, respVO);

        // 3. 方法返回
        return respVO;
    }
}