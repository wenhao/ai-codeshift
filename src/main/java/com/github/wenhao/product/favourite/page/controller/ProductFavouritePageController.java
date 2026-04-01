package com.github.wenhao.product.favourite.page.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.wenhao.product.favourite.page.controller.vo.ProductFavouritePageReqVO;
import com.github.wenhao.product.favourite.page.controller.vo.ProductFavouritePageRespVO;
import com.github.wenhao.product.favourite.page.service.ProductFavouritePageService;
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
 * 商品收藏分页查询Controller
 */
@Tag(name = "商品收藏管理", description = "商品收藏分页查询接口")
@RestController
@RequestMapping("/product/favorite/page")
@RequiredArgsConstructor
@Validated
public class ProductFavouritePageController {

    private final ProductFavouritePageService productFavouritePageService;

    @Operation(summary = "分页查询商品收藏", description = "根据用户编号分页查询商品收藏")
    @GetMapping
    public CommonResult<IPage<ProductFavouritePageRespVO>> getFavouritePage(@ModelAttribute ProductFavouritePageReqVO reqVO) {
        IPage<ProductFavouritePageRespVO> respVO = productFavouritePageService.getFavouritePage(reqVO);
        return CommonResult.success(respVO);
    }
}