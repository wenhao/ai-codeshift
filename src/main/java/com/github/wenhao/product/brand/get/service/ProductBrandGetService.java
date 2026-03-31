package com.github.wenhao.product.brand.get.service;

import com.github.wenhao.product.brand.get.controller.vo.ProductBrandGetRespVO;

/**
 * 商品品牌查询Service接口
 */
public interface ProductBrandGetService {

    /**
     * 查询商品品牌
     *
     * @param id 品牌编号
     * @return 商品品牌信息
     */
    ProductBrandGetRespVO getBrand(Long id);
}