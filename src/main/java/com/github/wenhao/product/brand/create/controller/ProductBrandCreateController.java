package com.github.wenhao.product.brand.create.controller;

import com.github.wenhao.product.brand.create.service.ProductBrandCreateService;
import com.github.wenhao.product.brand.create.vo.ProductBrandCreateReqVO;
import com.github.wenhao.product.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 商品品牌创建Controller
 */
@Tag(name = "商品品牌管理", description = "商品品牌创建接口")
@RestController
@RequestMapping("/product/brand")
@RequiredArgsConstructor
@Validated
public class ProductBrandCreateController {

    private final ProductBrandCreateService productBrandCreateService;

    @Operation(summary = "创建商品品牌", description = "创建新的商品品牌")
    @PostMapping
    public CommonResult<Long> createBrand(@Valid @RequestBody ProductBrandCreateReqVO createReqVO) {
        Long brandId = productBrandCreateService.createBrand(createReqVO);
        return CommonResult.success("创建成功", brandId);
    }
}