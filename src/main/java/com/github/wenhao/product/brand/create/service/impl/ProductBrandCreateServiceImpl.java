package com.github.wenhao.product.brand.create.service.impl;

import com.github.wenhao.product.brand.create.mapper.ProductBrandCreateMapper;
import com.github.wenhao.product.brand.create.service.ProductBrandCreateService;
import com.github.wenhao.product.brand.create.vo.ProductBrandCreateReqVO;
import com.github.wenhao.product.brand.entity.po.ProductBrand;
import com.github.wenhao.product.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 商品品牌创建服务实现类
 */
@Service
@RequiredArgsConstructor
public class ProductBrandCreateServiceImpl implements ProductBrandCreateService {

    private static final String ERROR_CODE_BRAND_NAME_EXISTS = "1_008_002_002";

    private final ProductBrandCreateMapper productBrandCreateMapper;

    @Override
    public Long createBrand(ProductBrandCreateReqVO createReqVO) {
        // 参数校验：根据品牌名称查询是否已存在
        ProductBrand existBrand = productBrandCreateMapper.selectByName(createReqVO.getName());
        if (existBrand != null) {
            throw new BusinessException(ERROR_CODE_BRAND_NAME_EXISTS, "品牌名称已存在");
        }

        // 数据转换：将请求参数转换为数据库实体对象
        ProductBrand productBrand = new ProductBrand();
        productBrand.setName(createReqVO.getName());
        productBrand.setPicUrl(createReqVO.getPicUrl());
        productBrand.setSort(createReqVO.getSort());
        productBrand.setStatus(createReqVO.getStatus());
        productBrand.setDescription(createReqVO.getDescription());

        // 数据库操作：插入品牌数据
        productBrandCreateMapper.insert(productBrand);

        // 返回自动生成的品牌编号
        return productBrand.getId();
    }
}