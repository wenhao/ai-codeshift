package com.github.wenhao.product.property.update.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wenhao.product.spu.entity.po.ProductSku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductSkuPropertyUpdateMapper extends BaseMapper<ProductSku> {

    /**
     * 批量更新商品SKU
     *
     * @param skuList 商品SKU列表
     * @return 更新数量
     */
    int batchUpdate(@Param("list") List<ProductSku> skuList);

    /**
     * 根据属性项编号查询使用的SKU数量
     *
     * @param propertyId 属性项编号
     * @return 使用数量
     */
    Long countByPropertyId(@Param("propertyId") Long propertyId);
}