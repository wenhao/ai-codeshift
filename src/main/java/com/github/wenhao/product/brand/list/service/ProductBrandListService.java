package com.github.wenhao.product.brand.list.service;

import com.github.wenhao.product.brand.list.controller.vo.ProductBrandListReqVO;
import com.github.wenhao.product.brand.list.controller.vo.ProductBrandListRespVO;

import java.util.List;

/**
 * 商品品牌列表查询Service接口
 */
public interface ProductBrandListService {

    /**
     * 查询商品品牌列表
     *
     * @param reqVO 请求参数
     * @return 商品品牌列表
     */
    List<ProductBrandListRespVO> getBrandList(ProductBrandListReqVO reqVO);
}