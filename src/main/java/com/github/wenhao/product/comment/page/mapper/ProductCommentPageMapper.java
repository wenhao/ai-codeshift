package com.github.wenhao.product.comment.page.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wenhao.product.comment.entity.po.ProductComment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价分页Mapper
 */
@Mapper
public interface ProductCommentPageMapper extends BaseMapper<ProductComment> {

}