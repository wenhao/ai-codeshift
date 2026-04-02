package com.github.wenhao.product.spu.getcount.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wenhao.product.spu.entity.po.ProductSpu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商品SPU分页TabCount查询Mapper
 */
@Mapper
public interface ProductSpuTabCountMapper extends BaseMapper<ProductSpu> {

    /**
     * 统计销售中商品数量
     * 销售中：status = 1（上架）
     *
     * @return 销售中商品数量
     */
    Long countByStatusOnSale();

    /**
     * 统计仓库中商品数量
     * 仓库中：status = 0（下架）
     *
     * @return 仓库中商品数量
     */
    Long countByStatusInWarehouse();

    /**
     * 统计售空商品数量
     * 售空：stock = 0
     *
     * @return 售空商品数量
     */
    Long countByStockEmpty();

    /**
     * 统计警戒库存商品数量
     * 警戒库存：stock <= 10 且 status != -1（回收站）
     *
     * @return 警戒库存商品数量
     */
    Long countByStockWarning();

    /**
     * 统计回收站商品数量
     * 回收站：status = -1
     *
     * @return 回收站商品数量
     */
    Long countByStatusInRecycleBin();
}