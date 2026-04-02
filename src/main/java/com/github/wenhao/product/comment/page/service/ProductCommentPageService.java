package com.github.wenhao.product.comment.page.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.wenhao.product.comment.page.controller.vo.ProductCommentPageReqVO;
import com.github.wenhao.product.comment.page.controller.vo.ProductCommentPageRespVO;

/**
 * 商品评价分页查询Service接口
 */
public interface ProductCommentPageService {

    /**
     * 查询评价分页
     *
     * @param reqVO 请求参数
     * @return 评价分页数据
     */
    IPage<ProductCommentPageRespVO> getCommentPage(ProductCommentPageReqVO reqVO);
}