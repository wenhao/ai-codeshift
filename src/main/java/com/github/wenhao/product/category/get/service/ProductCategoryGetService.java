package com.github.wenhao.product.category.get.service;

import com.github.wenhao.product.category.get.controller.vo.ProductCategoryGetRespVO;

/**
 * 商品分类查询Service接口
 */
public interface ProductCategoryGetService {

    /**
     * 查询商品分类
     *
     * @param id 分类编号
     * @return 商品分类信息
     */
    ProductCategoryGetRespVO getCategory(Long id);
}