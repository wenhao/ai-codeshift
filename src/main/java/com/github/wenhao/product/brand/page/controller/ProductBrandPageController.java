package com.github.wenhao.product.brand.page.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.wenhao.product.brand.page.controller.vo.ProductBrandPageReqVO;
import com.github.wenhao.product.brand.page.controller.vo.ProductBrandPageRespVO;
import com.github.wenhao.product.brand.page.service.ProductBrandPageService;
import com.github.wenhao.product.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品品牌分页查询Controller
 */
@Tag(name = "商品品牌管理", description = "商品品牌分页查询接口")
@RestController
@RequestMapping("/product/brand/page")
@RequiredArgsConstructor
@Validated
public class ProductBrandPageController {

    private final ProductBrandPageService productBrandPageService;

    @Operation(summary = "分页查询商品品牌", description = "根据品牌名称、状态、创建时间分页查询商品品牌")
    @GetMapping
    public CommonResult<IPage<ProductBrandPageRespVO>> getBrandPage(@ModelAttribute ProductBrandPageReqVO reqVO) {
        IPage<ProductBrandPageRespVO> respVO = productBrandPageService.getBrandPage(reqVO);
        return CommonResult.success(respVO);
    }
}