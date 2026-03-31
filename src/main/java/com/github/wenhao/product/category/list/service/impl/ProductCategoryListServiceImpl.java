package com.github.wenhao.product.category.list.service.impl;

import com.github.wenhao.product.category.entity.po.ProductCategory;
import com.github.wenhao.product.category.list.controller.vo.ProductCategoryListReqVO;
import com.github.wenhao.product.category.list.controller.vo.ProductCategoryListRespVO;
import com.github.wenhao.product.category.list.mapper.ProductCategoryListMapper;
import com.github.wenhao.product.category.list.service.ProductCategoryListService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品分类列表查询Service实现类
 */
@Service
@RequiredArgsConstructor
public class ProductCategoryListServiceImpl implements ProductCategoryListService {

    private final ProductCategoryListMapper productCategoryListMapper;

    @Override
    public List<ProductCategoryListRespVO> getCategoryList(ProductCategoryListReqVO reqVO) {
        // 1. 数据库操作：根据查询条件查询商品分类列表
        List<ProductCategory> productCategoryList = productCategoryListMapper.selectListByReqVO(reqVO);

        // 2. 数据转换：将entity列表转换为响应参数列表
        List<ProductCategoryListRespVO> respVOList = productCategoryList.stream()
                .map(productCategory -> {
                    ProductCategoryListRespVO respVO = new ProductCategoryListRespVO();
                    BeanUtils.copyProperties(productCategory, respVO);
                    return respVO;
                })
                .collect(Collectors.toList());

        // 3. 方法返回
        return respVOList;
    }
}