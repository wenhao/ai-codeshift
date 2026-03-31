package com.github.wenhao.product.brand.create.service;

import com.github.wenhao.product.brand.create.controller.vo.CreateProductBrandReqVO;
import com.github.wenhao.product.brand.create.mapper.ProductBrandCreateMapper;
import com.github.wenhao.product.brand.entity.po.ProductBrand;
import com.github.wenhao.product.common.enums.CommonStatusEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class ProductBrandCreateService {

    @Resource
    private ProductBrandCreateMapper productBrandCreateMapper;

    public Long create(CreateProductBrandReqVO createProductBrandReqVO) {
        // 校验品牌名称是否已存在
        ProductBrand existingBrand = productBrandCreateMapper.selectByName(createProductBrandReqVO.getName());
        if (existingBrand != null) {
            throw new RuntimeException("品牌名称已存在"); // 错误码：1_008_002_002
        }

        // 验证状态值是否有效
        if (!isValidStatus(createProductBrandReqVO.getStatus())) {
            throw new IllegalArgumentException("无效的品牌状态");
        }

        // 将请求参数转换为实体对象
        ProductBrand productBrand = new ProductBrand();
        productBrand.setName(createProductBrandReqVO.getName());
        productBrand.setPicUrl(createProductBrandReqVO.getPicUrl());
        productBrand.setSort(createProductBrandReqVO.getSort());
        productBrand.setStatus(createProductBrandReqVO.getStatus());
        productBrand.setDescription(createProductBrandReqVO.getDescription());

        // 插入数据库
        productBrandCreateMapper.insert(productBrand);

        // 返回品牌编号
        return productBrand.getId();
    }

    /**
     * 验证状态值是否有效
     *
     * @param status 状态值
     * @return 是否有效
     */
    private boolean isValidStatus(Integer status) {
        if (status == null) {
            return false;
        }
        for (CommonStatusEnum statusEnum : CommonStatusEnum.values()) {
            if (statusEnum.getStatus().equals(status)) {
                return true;
            }
        }
        return false;
    }
}