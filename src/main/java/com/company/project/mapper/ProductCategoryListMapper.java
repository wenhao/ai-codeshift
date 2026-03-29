package com.company.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.project.entity.po.ProductCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品分类_查询列表(mapper)
 * 查询商品分类列表
 */
@Mapper
public interface ProductCategoryListMapper extends BaseMapper<ProductCategory> {
}