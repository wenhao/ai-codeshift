package com.github.wenhao.product.spu.listallsimple.controller.vo;

import lombok.Data;

/**
 * 商品SPU精简信息响应VO
 */
@Data
public class ProductSpuListAllSimpleRespVO {

    /**
     * 商品SPU编号
     */
    private Long id;

    /**
     * 商品名称
     */
    private String name;
}