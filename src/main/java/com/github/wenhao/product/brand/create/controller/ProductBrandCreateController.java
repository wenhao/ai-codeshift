package com.github.wenhao.product.brand.create.controller;

import com.github.wenhao.product.brand.create.controller.vo.CreateProductBrandReqVO;
import com.github.wenhao.product.brand.create.service.ProductBrandCreateService;
import com.github.wenhao.product.common.result.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;

@Tag(name = "商品品牌创建", description = "提供商品品牌的创建功能")
@RestController
@RequestMapping("/product/brand/create")
public class ProductBrandCreateController {

    @Resource
    private ProductBrandCreateService productBrandCreateService;

    @PostMapping
    @Operation(summary = "创建商品品牌")
    public CommonResult<Long> create(@RequestBody @Valid CreateProductBrandReqVO createProductBrandReqVO) {
        Long brandId = productBrandCreateService.create(createProductBrandReqVO);
        return CommonResult.success(brandId);
    }
}