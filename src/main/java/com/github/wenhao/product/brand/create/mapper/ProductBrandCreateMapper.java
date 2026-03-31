package com.github.wenhao.product.brand.create.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wenhao.product.brand.entity.po.ProductBrand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductBrandCreateMapper extends BaseMapper<ProductBrand> {

    /**
     * 根据品牌名称查询商品品牌
     */
    ProductBrand selectByName(@Param("name") String name);
}