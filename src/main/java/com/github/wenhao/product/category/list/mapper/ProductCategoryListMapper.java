package com.github.wenhao.product.category.list.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wenhao.product.category.entity.po.ProductCategory;
import com.github.wenhao.product.category.list.controller.vo.ProductCategoryListReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

@Mapper
public interface ProductCategoryListMapper extends BaseMapper<ProductCategory> {

    /**
     * 根据查询条件查询商品分类列表
     *
     * @param reqVO 查询条件
     * @return 商品分类列表
     */
    List<ProductCategory> selectListByReqVO(@Param("reqVO") ProductCategoryListReqVO reqVO);
}