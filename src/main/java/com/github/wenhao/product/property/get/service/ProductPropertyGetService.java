package com.github.wenhao.product.property.get.service;

import com.github.wenhao.product.property.get.controller.vo.ProductPropertyGetRespVO;

/**
 * 商品属性项查询Service接口
 */
public interface ProductPropertyGetService {

    /**
     * 查询商品属性项
     *
     * @param id 属性项编号
     * @return 商品属性项信息
     */
    ProductPropertyGetRespVO getProperty(Long id);
}