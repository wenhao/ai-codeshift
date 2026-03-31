package com.github.wenhao.product.brand.delete.controller;

import com.github.wenhao.product.brand.delete.service.ProductBrandDeleteService;
import com.github.wenhao.product.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品品牌删除Controller
 */
@Tag(name = "商品品牌管理", description = "商品品牌删除接口")
@RestController
@RequestMapping("/product/brand/delete")
@RequiredArgsConstructor
@Validated
public class ProductBrandDeleteController {

    private final ProductBrandDeleteService productBrandDeleteService;

    @Operation(summary = "删除商品品牌", description = "删除商品品牌")
    @DeleteMapping
    public CommonResult<Boolean> deleteBrand(
        @Parameter(name = "id", description = "品牌编号", required = true) 
        @RequestParam("id") Long id) {
        Boolean result = productBrandDeleteService.deleteBrand(id);
        return CommonResult.success("删除成功", result);
    }
}