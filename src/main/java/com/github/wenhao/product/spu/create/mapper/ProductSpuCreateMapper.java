package com.github.wenhao.product.spu.create.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wenhao.product.brand.entity.po.ProductBrand;
import com.github.wenhao.product.category.entity.po.ProductCategory;
import com.github.wenhao.product.property.entity.po.ProductProperty;
import com.github.wenhao.product.spu.entity.po.ProductSku;
import com.github.wenhao.product.spu.entity.po.ProductSpu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品SPU创建Mapper
 */
@Mapper
public interface ProductSpuCreateMapper extends BaseMapper<ProductSpu> {

    /**
     * 根据分类编号查询商品分类
     *
     * @param categoryId 分类编号
     * @return 商品分类
     */
    ProductCategory selectCategoryById(@Param("categoryId") Long categoryId);

    /**
     * 根据品牌编号查询商品品牌
     *
     * @param brandId 品牌编号
     * @return 商品品牌
     */
    ProductBrand selectBrandById(@Param("brandId") Long brandId);

    /**
     * 根据属性编号列表批量查询商品属性项
     *
     * @param propertyIds 属性编号列表
     * @return 商品属性项列表
     */
    List<ProductProperty> selectPropertyByIds(@Param("propertyIds") List<Long> propertyIds);

    /**
     * 根据SPU编号查询所有SKU
     *
     * @param spuId SPU编号
     * @return SKU列表
     */
    List<ProductSku> selectSkuBySpuId(@Param("spuId") Long spuId);

    /**
     * 批量插入SKU
     *
     * @param skuList SKU列表
     * @return 影响行数
     */
    int batchInsertSku(@Param("skuList") List<ProductSku> skuList);
}