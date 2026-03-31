package com.github.wenhao.product.brand.page.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.wenhao.product.brand.entity.po.ProductBrand;
import com.github.wenhao.product.brand.page.controller.vo.ProductBrandPageReqVO;
import com.github.wenhao.product.brand.page.controller.vo.ProductBrandPageRespVO;
import com.github.wenhao.product.brand.page.mapper.ProductBrandPageMapper;
import com.github.wenhao.product.brand.page.service.ProductBrandPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品品牌分页查询Service实现类
 */
@Service
@RequiredArgsConstructor
public class ProductBrandPageServiceImpl implements ProductBrandPageService {

    private final ProductBrandPageMapper productBrandPageMapper;

    @Override
    public IPage<ProductBrandPageRespVO> getBrandPage(ProductBrandPageReqVO reqVO) {
        // 1. 数据库操作：根据分页参数和查询条件查询商品品牌列表
        // 构建分页对象
        int pageNo = reqVO.getPageNo() != null ? reqVO.getPageNo() : 1;
        int pageSize = reqVO.getPageSize() != null ? reqVO.getPageSize() : 10;
        Page<ProductBrand> page = new Page<>(pageNo, pageSize);

        // 构建查询条件
        LambdaQueryWrapper<ProductBrand> queryWrapper = new LambdaQueryWrapper<>();
        // 若查询参数`品牌名称`不为空，则按品牌名称精确查询
        if (StringUtils.hasText(reqVO.getName())) {
            queryWrapper.eq(ProductBrand::getName, reqVO.getName());
        }
        // 若查询参数`状态`不为空，则按状态精确查询
        if (reqVO.getStatus() != null) {
            queryWrapper.eq(ProductBrand::getStatus, reqVO.getStatus());
        }
        // 若查询参数`创建时间范围数组`不为空，则按传入的时间范围查询
        if (reqVO.getCreateTime() != null && reqVO.getCreateTime().length == 2) {
            LocalDateTime startTime = reqVO.getCreateTime()[0];
            LocalDateTime endTime = reqVO.getCreateTime()[1];
            if (startTime != null) {
                queryWrapper.ge(ProductBrand::getCreateTime, startTime);
            }
            if (endTime != null) {
                queryWrapper.le(ProductBrand::getCreateTime, endTime);
            }
        }

        // 执行分页查询
        IPage<ProductBrand> productBrandPage = productBrandPageMapper.selectPage(page, queryWrapper);

        // 2. 数据转换：将entity分页结果转换为响应参数分页结果
        List<ProductBrandPageRespVO> respVOList = productBrandPage.getRecords().stream()
                .map(productBrand -> {
                    ProductBrandPageRespVO respVO = new ProductBrandPageRespVO();
                    BeanUtils.copyProperties(productBrand, respVO);
                    return respVO;
                })
                .collect(Collectors.toList());

        // 构建返回的分页结果
        Page<ProductBrandPageRespVO> respPage = new Page<>(productBrandPage.getCurrent(), productBrandPage.getSize(), productBrandPage.getTotal());
        respPage.setRecords(respVOList);

        // 3. 方法返回
        return respPage;
    }
}