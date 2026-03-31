package com.github.wenhao.product.brand.delete.service;

/**
 * 商品品牌删除Service
 */
public interface ProductBrandDeleteService {

    /**
     * 删除商品品牌
     *
     * @param id 品牌编号
     * @return 删除结果
     */
    Boolean deleteBrand(Long id);
}