package com.github.wenhao.product.spu.getcount.service.impl;

import com.github.wenhao.product.spu.getcount.mapper.ProductSpuTabCountMapper;
import com.github.wenhao.product.spu.getcount.service.ProductSpuTabCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 商品SPU分页TabCount查询Service实现类
 */
@Service
@RequiredArgsConstructor
public class ProductSpuTabCountServiceImpl implements ProductSpuTabCountService {

    private final ProductSpuTabCountMapper productSpuTabCountMapper;

    @Override
    public Map<Integer, Long> getTabCount() {
        Map<Integer, Long> tabCountMap = new LinkedHashMap<>(5);

        // 统计销售中(key为0)商品数量：status = 1（上架）
        Long onSaleCount = productSpuTabCountMapper.countByStatusOnSale();
        tabCountMap.put(0, onSaleCount);

        // 统计仓库中(key为1)商品数量：status = 0（下架）
        Long inWarehouseCount = productSpuTabCountMapper.countByStatusInWarehouse();
        tabCountMap.put(1, inWarehouseCount);

        // 统计售空(key为2)商品数量：stock = 0
        Long stockEmptyCount = productSpuTabCountMapper.countByStockEmpty();
        tabCountMap.put(2, stockEmptyCount);

        // 统计警戒库存(key为3)商品数量：stock <= 10 且 status != -1（回收站）
        Long stockWarningCount = productSpuTabCountMapper.countByStockWarning();
        tabCountMap.put(3, stockWarningCount);

        // 统计回收站(key为4)商品数量：status = -1
        Long recycleBinCount = productSpuTabCountMapper.countByStatusInRecycleBin();
        tabCountMap.put(4, recycleBinCount);

        // 方法返回
        return tabCountMap;
    }
}