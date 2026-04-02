package com.github.wenhao.product.spu.create.service;

import com.github.wenhao.product.spu.create.vo.ProductSpuCreateReqVO;

/**
 * 商品SPU创建Service接口
 */
public interface ProductSpuCreateService {

    /**
     * 创建商品SPU
     *
     * @param createReqVO 创建请求参数
     * @return 创建成功的商品SPU编号
     */
    Long createSpu(ProductSpuCreateReqVO createReqVO);
}