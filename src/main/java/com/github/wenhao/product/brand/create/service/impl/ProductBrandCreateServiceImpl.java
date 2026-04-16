package com.github.wenhao.product.brand.create.service.impl;

import com.github.wenhao.product.brand.create.mapper.ProductBrandCreateMapper;
import com.github.wenhao.product.brand.create.service.ProductBrandCreateService;
import com.github.wenhao.product.brand.create.vo.ProductBrandCreateReqVO;
import com.github.wenhao.product.brand.entity.po.ProductBrand;
import com.github.wenhao.product.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 商品品牌创建Service实现类
 */
@Service
@RequiredArgsConstructor
public class ProductBrandCreateServiceImpl implements ProductBrandCreateService {

    private final ProductBrandCreateMapper productBrandCreateMapper;

    @Override
    public Long createBrand(ProductBrandCreateReqVO createReqVO) {
        // 1. 参数校验：校验品牌名称是否重复
        ProductBrand existBrand = productBrandCreateMapper.selectByName(createReqVO.getName());
        if (existBrand != null) {
            throw new BusinessException("1_008_002_002", "品牌名称已存在");
        }

        // 2. 数据转换：将请求参数转换为数据库实体对象
        ProductBrand productBrand = new ProductBrand();
        BeanUtils.copyProperties(createReqVO, productBrand);

        // 3. 数据库操作：插入数据库
        productBrandCreateMapper.insert(productBrand);

        return productBrand.getId();
    }
}