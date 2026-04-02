package com.github.wenhao.product.comment.page.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.wenhao.product.comment.page.controller.vo.ProductCommentPageReqVO;
import com.github.wenhao.product.comment.page.controller.vo.ProductCommentPageRespVO;
import com.github.wenhao.product.comment.page.service.ProductCommentPageService;
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
 * 商品评价分页查询Controller
 */
@Tag(name = "商品评价管理", description = "商品评价分页查询接口")
@RestController
@RequestMapping("/product/comment/page")
@RequiredArgsConstructor
@Validated
public class ProductCommentPageController {

    private final ProductCommentPageService productCommentPageService;

    @Operation(summary = "分页查询商品评价", description = "根据商品SPU编号、商品SKU编号、是否可见、评分星级、创建时间分页查询商品评价")
    @GetMapping
    public CommonResult<IPage<ProductCommentPageRespVO>> getCommentPage(@ModelAttribute ProductCommentPageReqVO reqVO) {
        IPage<ProductCommentPageRespVO> respVO = productCommentPageService.getCommentPage(reqVO);
        return CommonResult.success(respVO);
    }
}