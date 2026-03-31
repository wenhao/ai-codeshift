package com.github.wenhao.product.brand.get.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.wenhao.product.brand.entity.po.ProductBrand;
import com.github.wenhao.product.brand.get.controller.vo.ProductBrandGetRespVO;
import com.github.wenhao.product.brand.get.mapper.ProductBrandGetMapper;
import com.github.wenhao.product.brand.get.service.ProductBrandGetService;
import com.github.wenhao.product.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 商品品牌查询Service实现类
 */
@Service
@RequiredArgsConstructor
public class ProductBrandGetServiceImpl implements ProductBrandGetService {

    private final ProductBrandGetMapper productBrandGetMapper;

    @Override
    public ProductBrandGetRespVO getBrand(Long id) {
        // 1. 数据库操作：根据品牌编号查询唯一的商品品牌
        LambdaQueryWrapper<ProductBrand> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProductBrand::getId, id);
        ProductBrand productBrand = productBrandGetMapper.selectOne(wrapper);

        if (Objects.isNull(productBrand)) {
            throw new BusinessException("商品品牌不存在");
        }

        // 2. 数据转换：将entity转换为响应参数
        ProductBrandGetRespVO respVO = new ProductBrandGetRespVO();
        BeanUtils.copyProperties(productBrand, respVO);

        // 3. 方法返回
        return respVO;
    }
}