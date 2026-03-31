package com.github.wenhao.product.category.get.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.wenhao.product.category.entity.po.ProductCategory;
import com.github.wenhao.product.category.get.controller.vo.ProductCategoryGetRespVO;
import com.github.wenhao.product.category.get.mapper.ProductCategoryGetMapper;
import com.github.wenhao.product.category.get.service.ProductCategoryGetService;
import com.github.wenhao.product.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 商品分类查询Service实现类
 */
@Service
@RequiredArgsConstructor
public class ProductCategoryGetServiceImpl implements ProductCategoryGetService {

    private final ProductCategoryGetMapper productCategoryGetMapper;

    @Override
    public ProductCategoryGetRespVO getCategory(Long id) {
        // 1. 数据库操作：根据分类编号查询唯一的商品分类
        LambdaQueryWrapper<ProductCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProductCategory::getId, id);
        ProductCategory productCategory = productCategoryGetMapper.selectOne(wrapper);

        if (Objects.isNull(productCategory)) {
            throw new BusinessException("商品分类不存在");
        }

        // 2. 数据转换：将entity转换为响应参数
        ProductCategoryGetRespVO respVO = new ProductCategoryGetRespVO();
        BeanUtils.copyProperties(productCategory, respVO);

        // 3. 方法返回
        return respVO;
    }
}