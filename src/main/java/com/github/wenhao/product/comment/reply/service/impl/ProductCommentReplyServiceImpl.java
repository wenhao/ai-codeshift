package com.github.wenhao.product.comment.reply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.wenhao.product.comment.entity.po.ProductComment;
import com.github.wenhao.product.comment.reply.mapper.ProductCommentReplyMapper;
import com.github.wenhao.product.comment.reply.service.ProductCommentReplyService;
import com.github.wenhao.product.comment.reply.vo.ProductCommentReplyReqVO;
import com.github.wenhao.product.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 商品评价回复Service实现类
 */
@Service
@RequiredArgsConstructor
public class ProductCommentReplyServiceImpl implements ProductCommentReplyService {

    private final ProductCommentReplyMapper productCommentReplyMapper;

    @Override
    public Boolean replyComment(ProductCommentReplyReqVO replyReqVO) {
        // 2. 数据校验：根据请求参数中的商品评论编号，查询数据库校验评论编号是否真实存在
        ProductComment productComment = productCommentReplyMapper.selectOne(
            new LambdaQueryWrapper<ProductComment>()
                .eq(ProductComment::getId, replyReqVO.getId())
        );
        if (productComment == null) {
            throw new BusinessException("1_008_007_000", "商品评价不存在");
        }

        // 3. 数据库操作：根据商品评价设置回复内容、回复时间、回复管理员编号、商家是否回复
        productComment.setReplyContent(replyReqVO.getReplyContent());
        productComment.setReplyTime(LocalDateTime.now());
        productComment.setReplyUserId(replyReqVO.getReplyUserId());
        productComment.setReplyStatus(true);

        // 更新数据库
        productCommentReplyMapper.updateById(productComment);

        // 4. 方法返回：更新成功返回true
        return true;
    }
}