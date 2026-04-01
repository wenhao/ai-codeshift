package com.github.wenhao.product.favourite.page.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wenhao.product.favourite.entity.po.ProductFavourite;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品收藏分页查询Mapper
 */
@Mapper
public interface ProductFavouritePageMapper extends BaseMapper<ProductFavourite> {

}