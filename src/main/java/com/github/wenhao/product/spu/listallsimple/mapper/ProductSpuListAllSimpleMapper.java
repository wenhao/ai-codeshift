package com.github.wenhao.product.spu.listallsimple.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wenhao.product.spu.entity.po.ProductSpu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品SPU精简信息Mapper
 */
@Mapper
public interface ProductSpuListAllSimpleMapper extends BaseMapper<ProductSpu> {

    /**
     * 根据商品状态查询SPU列表
     *
     * @param status 商品状态
     * @return SPU列表
     */
    List<ProductSpu> selectListByStatus(@Param("status") Integer status);
}