package com.github.wenhao.product.brand.get.controller;

import com.github.wenhao.product.brand.get.controller.vo.ProductBrandGetReqVO;
import com.github.wenhao.product.brand.get.controller.vo.ProductBrandGetRespVO;
import com.github.wenhao.product.brand.get.service.ProductBrandGetService;
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
 * 商品品牌查询Controller
 */
@Tag(name = "商品品牌管理", description = "商品品牌查询接口")
@RestController
@RequestMapping("/product/brand/get")
@RequiredArgsConstructor
@Validated
public class ProductBrandGetController {

    private final ProductBrandGetService productBrandGetService;

    @Operation(summary = "查询商品品牌", description = "根据品牌编号查询商品品牌")
    @GetMapping
    public CommonResult<ProductBrandGetRespVO> getBrand(@Valid ProductBrandGetReqVO reqVO) {
        ProductBrandGetRespVO respVO = productBrandGetService.getBrand(reqVO.getId());
        return CommonResult.success(respVO);
    }
}