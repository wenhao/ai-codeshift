package com.github.wenhao.product.comment.reply.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wenhao.product.comment.entity.po.ProductComment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductCommentReplyMapper extends BaseMapper<ProductComment> {
}