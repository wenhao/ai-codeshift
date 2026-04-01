package com.github.wenhao.product.favourite.page.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.wenhao.product.favourite.page.controller.vo.ProductFavouritePageReqVO;
import com.github.wenhao.product.favourite.page.controller.vo.ProductFavouritePageRespVO;

/**
 * 商品收藏分页查询Service接口
 */
public interface ProductFavouritePageService {

    /**
     * 查询商品收藏分页
     *
     * @param reqVO 请求参数
     * @return 商品收藏分页数据
     */
    IPage<ProductFavouritePageRespVO> getFavouritePage(ProductFavouritePageReqVO reqVO);
}