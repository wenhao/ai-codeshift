package com.github.wenhao.product.comment.create.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wenhao.product.comment.entity.po.ProductComment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductCommentCreateMapper extends BaseMapper<ProductComment> {
}