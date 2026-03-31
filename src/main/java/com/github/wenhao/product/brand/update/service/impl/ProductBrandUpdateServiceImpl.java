package com.github.wenhao.product.brand.update.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.wenhao.product.brand.entity.po.ProductBrand;
import com.github.wenhao.product.brand.update.mapper.ProductBrandUpdateMapper;
import com.github.wenhao.product.brand.update.service.ProductBrandUpdateService;
import com.github.wenhao.product.brand.update.vo.ProductBrandUpdateReqVO;
import com.github.wenhao.product.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 商品品牌更新Service实现类
 */
@Service
@RequiredArgsConstructor
public class ProductBrandUpdateServiceImpl implements ProductBrandUpdateService {

    private final ProductBrandUpdateMapper productBrandUpdateMapper;

    @Override
    public Boolean updateBrand(ProductBrandUpdateReqVO updateReqVO) {
        // 1. 参数校验：根据品牌编号查询数据库校验品牌是否真实存在
        ProductBrand brand = productBrandUpdateMapper.selectOne(
            new LambdaQueryWrapper<ProductBrand>()
                .eq(ProductBrand::getId, updateReqVO.getId())
        );
        if (brand == null) {
            throw new BusinessException("1_008_002_000", "商品品牌不存在");
        }

        // 2. 参数校验：校验品牌名称是否重复
        ProductBrand existBrand = productBrandUpdateMapper.selectOne(
            new LambdaQueryWrapper<ProductBrand>()
                .eq(ProductBrand::getName, updateReqVO.getName())
        );
        if (existBrand != null && !existBrand.getId().equals(updateReqVO.getId())) {
            throw new BusinessException("1_008_002_002", "品牌名称已存在");
        }

        // 3. 数据转换：将请求参数转换为数据库实体对象
        ProductBrand productBrand = new ProductBrand();
        BeanUtils.copyProperties(updateReqVO, productBrand);

        // 4. 数据库操作：更新数据库
        productBrandUpdateMapper.updateById(productBrand);

        return true;
    }
}