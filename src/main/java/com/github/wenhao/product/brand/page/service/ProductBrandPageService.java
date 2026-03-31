package com.github.wenhao.product.brand.page.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.wenhao.product.brand.page.controller.vo.ProductBrandPageReqVO;
import com.github.wenhao.product.brand.page.controller.vo.ProductBrandPageRespVO;

/**
 * 商品品牌分页查询Service接口
 */
public interface ProductBrandPageService {

    /**
     * 查询品牌分页
     *
     * @param reqVO 请求参数
     * @return 品牌分页数据
     */
    IPage<ProductBrandPageRespVO> getBrandPage(ProductBrandPageReqVO reqVO);
}