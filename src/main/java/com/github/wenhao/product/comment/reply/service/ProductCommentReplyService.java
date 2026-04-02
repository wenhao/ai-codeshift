package com.github.wenhao.product.comment.reply.service;

import com.github.wenhao.product.comment.reply.vo.ProductCommentReplyReqVO;

/**
 * 商品评价回复Service接口
 */
public interface ProductCommentReplyService {

    /**
     * 商家回复商品评价
     *
     * @param replyReqVO 回复请求参数
     * @return 回复成功返回true
     */
    Boolean replyComment(ProductCommentReplyReqVO replyReqVO);
}