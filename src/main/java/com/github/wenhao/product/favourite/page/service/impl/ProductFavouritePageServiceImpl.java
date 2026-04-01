package com.github.wenhao.product.favourite.page.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.wenhao.product.favourite.entity.po.ProductFavourite;
import com.github.wenhao.product.favourite.page.controller.vo.ProductFavouritePageReqVO;
import com.github.wenhao.product.favourite.page.controller.vo.ProductFavouritePageRespVO;
import com.github.wenhao.product.favourite.page.mapper.ProductFavouritePageMapper;
import com.github.wenhao.product.favourite.page.mapper.ProductSpuBatchListMapper;
import com.github.wenhao.product.favourite.page.service.ProductFavouritePageService;
import com.github.wenhao.product.spu.entity.po.ProductSpu;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品收藏分页查询Service实现类
 */
@Service
@RequiredArgsConstructor
public class ProductFavouritePageServiceImpl implements ProductFavouritePageService {

    private final ProductFavouritePageMapper productFavouritePageMapper;
    private final ProductSpuBatchListMapper productSpuBatchListMapper;

    @Override
    public IPage<ProductFavouritePageRespVO> getFavouritePage(ProductFavouritePageReqVO reqVO) {
        // 1. 数据库操作：根据分页参数和查询条件查询商品收藏列表
        // 构建分页对象
        int pageNo = reqVO.getPageNo() != null ? reqVO.getPageNo() : 1;
        int pageSize = reqVO.getPageSize() != null ? reqVO.getPageSize() : 10;
        Page<ProductFavourite> page = new Page<>(pageNo, pageSize);

        // 构建查询条件
        LambdaQueryWrapper<ProductFavourite> queryWrapper = new LambdaQueryWrapper<>();
        // 若查询参数`用户编号`不为空，则按用户编号精确查询
        if (reqVO.getUserId() != null) {
            queryWrapper.eq(ProductFavourite::getUserId, reqVO.getUserId());
        }

        // 执行分页查询
        IPage<ProductFavourite> productFavouritePage = productFavouritePageMapper.selectPage(page, queryWrapper);

        // 2. 数据库操作：根据商品收藏列表中的商品SPU编号批量查询商品SPU数据
        List<Long> spuIds = productFavouritePage.getRecords().stream()
                .map(ProductFavourite::getSpuId)
                .collect(Collectors.toList());

        List<ProductSpu> productSpuList = productSpuBatchListMapper.selectBySpuIds(spuIds);
        Map<Long, ProductSpu> spuMap = productSpuList.stream()
                .collect(Collectors.toMap(ProductSpu::getId, spu -> spu));

        // 3. 数据转换：将entity分页结果转换为响应参数分页结果
        List<ProductFavouritePageRespVO> respVOList = productFavouritePage.getRecords().stream()
                .map(favourite -> {
                    ProductFavouritePageRespVO respVO = new ProductFavouritePageRespVO();
                    // 设置用户编号和SPU编号
                    respVO.setUserId(favourite.getUserId());
                    respVO.setSpuId(favourite.getSpuId());

                    // 如果SPU存在，则复制属性
                    ProductSpu spu = spuMap.get(favourite.getSpuId());
                    if (spu != null) {
                        BeanUtils.copyProperties(spu, respVO);
                    }
                    return respVO;
                })
                .collect(Collectors.toList());

        // 构建返回的分页结果
        Page<ProductFavouritePageRespVO> respPage = new Page<>(productFavouritePage.getCurrent(), productFavouritePage.getSize(), productFavouritePage.getTotal());
        respPage.setRecords(respVOList);

        // 4. 方法返回
        return respPage;
    }
}