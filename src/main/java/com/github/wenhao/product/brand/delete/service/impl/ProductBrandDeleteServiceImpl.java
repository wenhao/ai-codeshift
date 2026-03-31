package com.github.wenhao.product.brand.delete.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.wenhao.product.brand.delete.mapper.ProductBrandDeleteMapper;
import com.github.wenhao.product.brand.delete.service.ProductBrandDeleteService;
import com.github.wenhao.product.brand.entity.po.ProductBrand;
import com.github.wenhao.product.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 商品品牌删除Service实现类
 */
@Service
@RequiredArgsConstructor
public class ProductBrandDeleteServiceImpl implements ProductBrandDeleteService {

    private final ProductBrandDeleteMapper productBrandDeleteMapper;

    @Override
    public Boolean deleteBrand(Long id) {
        // 1. 参数校验：根据品牌编号查询数据库校验品牌是否真实存在
        ProductBrand brand = productBrandDeleteMapper.selectOne(
            new LambdaQueryWrapper<ProductBrand>()
                .eq(ProductBrand::getId, id)
        );
        if (brand == null) {
            throw new BusinessException("1_008_002_000", "商品品牌不存在");
        }

        // 2. 数据库操作：将品牌编号数据对象从数据库删除
        productBrandDeleteMapper.deleteById(id);

        return true;
    }
}