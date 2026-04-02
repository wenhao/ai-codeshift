package com.github.wenhao.product.spu.listallsimple.service;

import com.github.wenhao.product.spu.listallsimple.controller.vo.ProductSpuListAllSimpleRespVO;

import java.util.List;

/**
 * 商品SPU精简信息查询Service接口
 */
public interface ProductSpuListAllSimpleService {

    /**
     * 查询SPU精简信息列表
     *
     * @return SPU精简信息列表
     */
    List<ProductSpuListAllSimpleRespVO> getSpuSimpleList();
}