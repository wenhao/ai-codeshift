package com.github.wenhao.product.property.delete.service;

/**
 * 商品属性项删除Service
 */
public interface ProductPropertyDeleteService {

    /**
     * 删除商品属性项
     *
     * @param id 属性项编号
     * @return 删除结果
     */
    Boolean deleteProperty(Long id);
}