package com.github.wenhao.product.category.list.service;

import com.github.wenhao.product.category.list.controller.vo.ProductCategoryListReqVO;
import com.github.wenhao.product.category.list.controller.vo.ProductCategoryListRespVO;

import java.util.List;

/**
 * 商品分类列表查询Service接口
 */
public interface ProductCategoryListService {

    /**
     * 查询商品分类列表
     *
     * @param reqVO 查询条件
     * @return 商品分类列表
     */
    List<ProductCategoryListRespVO> getCategoryList(ProductCategoryListReqVO reqVO);
}