package com.github.wenhao.product.comment.create.service;

import com.github.wenhao.product.comment.create.vo.ProductCommentCreateReqVO;

/**
 * 商品评价创建Service接口
 */
public interface ProductCommentCreateService {

    /**
     * 创建商品评价
     *
     * @param createReqVO 创建请求参数
     * @return 创建成功返回true
     */
    Boolean createComment(ProductCommentCreateReqVO createReqVO);
}