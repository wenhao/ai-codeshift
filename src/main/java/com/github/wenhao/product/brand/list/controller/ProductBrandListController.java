package com.github.wenhao.product.brand.list.controller;

import com.github.wenhao.product.brand.list.controller.vo.ProductBrandListReqVO;
import com.github.wenhao.product.brand.list.controller.vo.ProductBrandListRespVO;
import com.github.wenhao.product.brand.list.service.ProductBrandListService;
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
 * 商品品牌列表查询Controller
 */
@Tag(name = "商品品牌管理", description = "商品品牌列表查询接口")
@RestController
@RequestMapping("/product/brand/list")
@RequiredArgsConstructor
@Validated
public class ProductBrandListController {

    private final ProductBrandListService productBrandListService;

    @Operation(summary = "查询商品品牌列表", description = "根据品牌名称查询商品品牌列表")
    @GetMapping
    public CommonResult<List<ProductBrandListRespVO>> getBrandList(ProductBrandListReqVO reqVO) {
        List<ProductBrandListRespVO> respVOList = productBrandListService.getBrandList(reqVO);
        return CommonResult.success(respVOList);
    }
}