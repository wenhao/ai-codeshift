package com.github.wenhao.product.brand.create.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wenhao.product.brand.entity.po.ProductBrand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商品品牌创建Mapper
 */
@Mapper
public interface ProductBrandCreateMapper extends BaseMapper<ProductBrand> {

    /**
     * 根据品牌名称查询品牌
     *
     * @param name 品牌名称
     * @return 品牌信息
     */
    ProductBrand selectByName(@Param("name") String name);
}