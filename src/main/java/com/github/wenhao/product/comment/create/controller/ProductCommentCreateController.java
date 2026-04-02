package com.github.wenhao.product.comment.create.controller;

import com.github.wenhao.product.comment.create.service.ProductCommentCreateService;
import com.github.wenhao.product.comment.create.vo.ProductCommentCreateReqVO;
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
 * 商品评价创建Controller
 */
@Tag(name = "商品评价管理", description = "商品评价创建接口")
@RestController
@RequestMapping("/product/comment/create")
@RequiredArgsConstructor
@Validated
public class ProductCommentCreateController {

    private final ProductCommentCreateService productCommentCreateService;

    @Operation(summary = "创建商品评价", description = "创建新的商品评价")
    @PostMapping
    public CommonResult<Boolean> createComment(@Valid @RequestBody ProductCommentCreateReqVO createReqVO) {
        Boolean result = productCommentCreateService.createComment(createReqVO);
        return CommonResult.success("创建成功", result);
    }
}