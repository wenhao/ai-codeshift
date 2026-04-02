package com.github.wenhao.product.comment.page.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.wenhao.product.comment.entity.po.ProductComment;
import com.github.wenhao.product.comment.page.controller.vo.ProductCommentPageReqVO;
import com.github.wenhao.product.comment.page.controller.vo.ProductCommentPageRespVO;
import com.github.wenhao.product.comment.page.mapper.ProductCommentPageMapper;
import com.github.wenhao.product.comment.page.service.ProductCommentPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品评价分页查询Service实现类
 */
@Service
@RequiredArgsConstructor
public class ProductCommentPageServiceImpl implements ProductCommentPageService {

    private final ProductCommentPageMapper productCommentPageMapper;

    @Override
    public IPage<ProductCommentPageRespVO> getCommentPage(ProductCommentPageReqVO reqVO) {
        // 1. 数据库操作：根据分页参数和查询条件查询商品评价列表
        // 构建分页对象
        int pageNo = reqVO.getPageNo() != null ? reqVO.getPageNo() : 1;
        int pageSize = reqVO.getPageSize() != null ? reqVO.getPageSize() : 10;
        Page<ProductComment> page = new Page<>(pageNo, pageSize);

        // 构建查询条件
        LambdaQueryWrapper<ProductComment> queryWrapper = new LambdaQueryWrapper<>();
        // 若查询参数`商品SPU编号`不为空，则按商品SPU编号精确查询
        if (reqVO.getSpuId() != null) {
            queryWrapper.eq(ProductComment::getSpuId, reqVO.getSpuId());
        }
        // 若查询参数`商品SKU编号`不为空，则按商品SKU编号精确查询
        if (reqVO.getSkuId() != null) {
            queryWrapper.eq(ProductComment::getSkuId, reqVO.getSkuId());
        }
        // 若查询参数`是否可见`不为空，则按是否可见精确查询
        if (reqVO.getVisible() != null) {
            queryWrapper.eq(ProductComment::getVisible, reqVO.getVisible());
        }
        // 若查询参数`评分星级`不为空，则按评分星级精确查询
        if (reqVO.getScores() != null) {
            queryWrapper.eq(ProductComment::getScores, reqVO.getScores());
        }
        // 若查询参数`创建时间范围数组`不为空，则按传入的时间范围查询
        if (reqVO.getCreateTime() != null && reqVO.getCreateTime().length == 2) {
            LocalDateTime startTime = reqVO.getCreateTime()[0];
            LocalDateTime endTime = reqVO.getCreateTime()[1];
            if (startTime != null) {
                queryWrapper.ge(ProductComment::getCreateTime, startTime);
            }
            if (endTime != null) {
                queryWrapper.le(ProductComment::getCreateTime, endTime);
            }
        }

        // 执行分页查询
        IPage<ProductComment> productCommentPage = productCommentPageMapper.selectPage(page, queryWrapper);

        // 2. 数据转换：将entity分页结果转换为响应参数分页结果
        List<ProductCommentPageRespVO> respVOList = productCommentPage.getRecords().stream()
                .map(productComment -> {
                    ProductCommentPageRespVO respVO = new ProductCommentPageRespVO();
                    BeanUtils.copyProperties(productComment, respVO);
                    return respVO;
                })
                .collect(Collectors.toList());

        // 构建返回的分页结果
        Page<ProductCommentPageRespVO> respPage = new Page<>(productCommentPage.getCurrent(), productCommentPage.getSize(), productCommentPage.getTotal());
        respPage.setRecords(respVOList);

        // 3. 方法返回
        return respPage;
    }
}