package com.github.wenhao.product.spu.getcount.service;

import java.util.Map;

/**
 * 商品SPU分页TabCount查询Service
 */
public interface ProductSpuTabCountService {

    /**
     * 获得商品SPU分页Tab数量统计
     * 按销售中、仓库中、售空、警戒库存、回收站5种维度统计数量
     *
     * @return 状态-数量键值对：key=状态值(0-销售中,1-仓库中,2-售空,3-警戒库存,4-回收站), value=对应数量
     */
    Map<Integer, Long> getTabCount();
}