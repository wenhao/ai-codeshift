package com.github.wenhao.product.spu.listallsimple.service.impl;

import com.github.wenhao.product.spu.entity.po.ProductSpu;
import com.github.wenhao.product.spu.listallsimple.controller.vo.ProductSpuListAllSimpleRespVO;
import com.github.wenhao.product.spu.listallsimple.mapper.ProductSpuListAllSimpleMapper;
import com.github.wenhao.product.spu.listallsimple.service.ProductSpuListAllSimpleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品SPU精简信息查询Service实现类
 */
@Service
@RequiredArgsConstructor
public class ProductSpuListAllSimpleServiceImpl implements ProductSpuListAllSimpleService {

    private static final Integer SPU_STATUS_ENABLED = 1;

    private final ProductSpuListAllSimpleMapper productSpuListAllSimpleMapper;

    @Override
    public List<ProductSpuListAllSimpleRespVO> getSpuSimpleList() {
        // 1. 数据库操作：查询状态为上架的所有商品SPU列表
        List<ProductSpu> productSpuList = productSpuListAllSimpleMapper.selectListByStatus(SPU_STATUS_ENABLED);

        if (productSpuList == null || productSpuList.isEmpty()) {
            return Collections.emptyList();
        }

        // 2. 数据转换：将entity列表转换为响应参数列表
        List<ProductSpuListAllSimpleRespVO> respVOList = productSpuList.stream()
                .map(productSpu -> {
                    ProductSpuListAllSimpleRespVO respVO = new ProductSpuListAllSimpleRespVO();
                    BeanUtils.copyProperties(productSpu, respVO);
                    return respVO;
                })
                .collect(Collectors.toList());

        // 3. 方法返回
        return respVOList;
    }
}