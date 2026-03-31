package com.github.wenhao.product.brand.update.service;

import com.github.wenhao.product.brand.update.vo.ProductBrandUpdateReqVO;

/**
 * 商品品牌更新Service接口
 */
public interface ProductBrandUpdateService {

    /**
     * 更新商品品牌
     *
     * @param updateReqVO 更新请求参数
     * @return 更新结果
     */
    Boolean updateBrand(ProductBrandUpdateReqVO updateReqVO);
}