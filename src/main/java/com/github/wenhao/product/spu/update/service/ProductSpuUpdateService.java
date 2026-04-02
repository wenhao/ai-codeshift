package com.github.wenhao.product.spu.update.service;

import com.github.wenhao.product.spu.update.vo.ProductSpuUpdateReqVO;

/**
 * 商品SPU更新Service
 */
public interface ProductSpuUpdateService {

    /**
     * 更新商品SPU
     *
     * @param updateReqVO 更新请求参数
     * @return 更新是否成功
     */
    Boolean updateSpu(ProductSpuUpdateReqVO updateReqVO);
}