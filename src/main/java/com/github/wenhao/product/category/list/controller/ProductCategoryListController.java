package com.github.wenhao.product.category.list.controller;

import com.github.wenhao.product.category.list.controller.vo.ProductCategoryListReqVO;
import com.github.wenhao.product.category.list.controller.vo.ProductCategoryListRespVO;
import com.github.wenhao.product.category.list.service.ProductCategoryListService;
import com.github.wenhao.product.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品分类列表查询Controller
 */
@Tag(name = "商品分类管理", description = "商品分类列表查询接口")
@RestController
@RequestMapping("/product/category/list")
@RequiredArgsConstructor
@Validated
public class ProductCategoryListController {

    private final ProductCategoryListService productCategoryListService;

    @Operation(summary = "查询商品分类列表", description = "根据条件查询商品分类列表")
    @GetMapping
    public CommonResult<List<ProductCategoryListRespVO>> getCategoryList(@ModelAttribute ProductCategoryListReqVO reqVO) {
        List<ProductCategoryListRespVO> respVOList = productCategoryListService.getCategoryList(reqVO);
        return CommonResult.success(respVOList);
    }
}