package com.company.project.service;

import com.company.project.controller.vo.GetProductCategoryListReqVO;
import com.company.project.controller.vo.GetProductCategoryListRespVO;

import java.util.List;

/**
 * 商品分类_查询列表(service)
 * 查询商品分类列表
 */
public interface ProductCategoryListService {

    /**
     * 查询商品分类列表
     *
     * @param reqVO 请求参数
     * @return 查询商品分类列表-响应参数
     */
    List<GetProductCategoryListRespVO> getProductCategoryList(GetProductCategoryListReqVO reqVO);
}