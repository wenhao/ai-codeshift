package com.github.wenhao.product.property.delete.controller;

import com.github.wenhao.product.common.pojo.CommonResult;
import com.github.wenhao.product.property.delete.service.ProductPropertyDeleteService;
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
 * 商品属性项删除Controller
 */
@Tag(name = "商品属性项管理", description = "商品属性项删除接口")
@RestController
@RequestMapping("/product/property/delete")
@RequiredArgsConstructor
@Validated
public class ProductPropertyDeleteController {

    private final ProductPropertyDeleteService productPropertyDeleteService;

    @Operation(summary = "删除商品属性项", description = "删除商品属性项")
    @DeleteMapping
    public CommonResult<Boolean> deleteProperty(
        @Parameter(name = "id", description = "属性项编号", required = true) 
        @RequestParam("id") Long id) {
        Boolean result = productPropertyDeleteService.deleteProperty(id);
        return CommonResult.success("删除成功", result);
    }
}