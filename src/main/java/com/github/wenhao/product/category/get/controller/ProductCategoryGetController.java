package com.github.wenhao.product.category.get.controller;

import com.github.wenhao.product.category.get.controller.vo.ProductCategoryGetReqVO;
import com.github.wenhao.product.category.get.controller.vo.ProductCategoryGetRespVO;
import com.github.wenhao.product.category.get.service.ProductCategoryGetService;
import com.github.wenhao.product.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 商品分类查询Controller
 */
@Tag(name = "商品分类管理", description = "商品分类查询接口")
@RestController
@RequestMapping("/product/category/get")
@RequiredArgsConstructor
@Validated
public class ProductCategoryGetController {

    private final ProductCategoryGetService productCategoryGetService;

    @Operation(summary = "查询商品分类", description = "根据分类编号查询商品分类")
    @GetMapping
    public CommonResult<ProductCategoryGetRespVO> getCategory(@Valid ProductCategoryGetReqVO reqVO) {
        ProductCategoryGetRespVO respVO = productCategoryGetService.getCategory(reqVO.getId());
        return CommonResult.success(respVO);
    }
}