package com.github.wenhao.product.brand.create.controller;

import com.github.wenhao.product.brand.create.service.ProductBrandCreateService;
import com.github.wenhao.product.brand.create.vo.ProductBrandCreateReqVO;
import com.github.wenhao.product.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品品牌创建控制器
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/product/brand/create")
@Tag(name = "商品品牌创建", description = "创建商品品牌")
public class ProductBrandCreateController {

    private final ProductBrandCreateService productBrandCreateService;

    @PostMapping
    @Operation(summary = "创建商品品牌", description = "创建新的商品品牌")
    public CommonResult<Long> createBrand(@Valid @RequestBody ProductBrandCreateReqVO createReqVO) {
        Long brandId = productBrandCreateService.createBrand(createReqVO);
        return CommonResult.success(brandId);
    }
}