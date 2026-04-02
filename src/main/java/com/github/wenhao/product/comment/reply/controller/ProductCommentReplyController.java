package com.github.wenhao.product.comment.reply.controller;

import com.github.wenhao.product.comment.reply.service.ProductCommentReplyService;
import com.github.wenhao.product.comment.reply.vo.ProductCommentReplyReqVO;
import com.github.wenhao.product.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 商品评价回复Controller
 */
@Tag(name = "商品评价管理", description = "商品评价回复接口")
@RestController
@RequestMapping("/product/comment/reply")
@RequiredArgsConstructor
@Validated
public class ProductCommentReplyController {

    private final ProductCommentReplyService productCommentReplyService;

    @Operation(summary = "商家回复商品评价", description = "商家回复商品评价")
    @PutMapping
    public CommonResult<Boolean> replyComment(@Valid @RequestBody ProductCommentReplyReqVO replyReqVO) {
        Boolean result = productCommentReplyService.replyComment(replyReqVO);
        return CommonResult.success("回复成功", result);
    }
}