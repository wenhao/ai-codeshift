package com.github.wenhao.product.brand.create.service;

import com.github.wenhao.product.brand.create.vo.ProductBrandCreateReqVO;

/**
 * 商品品牌创建服务接口
 */
public interface ProductBrandCreateService {

    /**
     * 创建商品品牌
     *
     * @param createReqVO 创建请求参数
     * @return 品牌编号
     */
    Long createBrand(ProductBrandCreateReqVO createReqVO);
}