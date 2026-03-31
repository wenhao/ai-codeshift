package com.github.wenhao.product.brand.list.service.impl;

import com.github.wenhao.product.brand.entity.po.ProductBrand;
import com.github.wenhao.product.brand.list.controller.vo.ProductBrandListReqVO;
import com.github.wenhao.product.brand.list.controller.vo.ProductBrandListRespVO;
import com.github.wenhao.product.brand.list.mapper.ProductBrandListMapper;
import com.github.wenhao.product.brand.list.service.ProductBrandListService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品品牌列表查询Service实现类
 */
@Service
@RequiredArgsConstructor
public class ProductBrandListServiceImpl implements ProductBrandListService {

    private final ProductBrandListMapper productBrandListMapper;

    @Override
    public List<ProductBrandListRespVO> getBrandList(ProductBrandListReqVO reqVO) {
        // 1. 数据库操作：根据品牌名称查询商品品牌列表
        List<ProductBrand> productBrandList = productBrandListMapper.selectListByName(reqVO.getName());

        if (productBrandList == null || productBrandList.isEmpty()) {
            return Collections.emptyList();
        }

        // 2. 数据转换：将entity列表转换为响应参数列表
        List<ProductBrandListRespVO> respVOList = productBrandList.stream()
                .map(productBrand -> {
                    ProductBrandListRespVO respVO = new ProductBrandListRespVO();
                    BeanUtils.copyProperties(productBrand, respVO);
                    return respVO;
                })
                .collect(Collectors.toList());

        // 3. 方法返回
        return respVOList;
    }
}