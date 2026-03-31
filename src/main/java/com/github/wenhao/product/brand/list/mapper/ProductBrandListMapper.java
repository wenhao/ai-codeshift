package com.github.wenhao.product.brand.list.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wenhao.product.brand.entity.po.ProductBrand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductBrandListMapper extends BaseMapper<ProductBrand> {

    /**
     * 根据品牌名称查询品牌列表
     *
     * @param name 品牌名称
     * @return 品牌列表
     */
    List<ProductBrand> selectListByName(@Param("name") String name);
}