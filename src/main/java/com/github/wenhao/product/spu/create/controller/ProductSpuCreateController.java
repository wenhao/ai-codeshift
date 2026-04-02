package com.github.wenhao.product.spu.create.controller;

import com.github.wenhao.product.common.pojo.CommonResult;
import com.github.wenhao.product.spu.create.service.ProductSpuCreateService;
import com.github.wenhao.product.spu.create.vo.ProductSpuCreateReqVO;
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
 * 商品SPU创建Controller
 */
@Tag(name = "商品SPU管理", description = "商品SPU创建接口")
@RestController
@RequestMapping("/product/spu/create")
@RequiredArgsConstructor
@Validated
public class ProductSpuCreateController {

    private final ProductSpuCreateService productSpuCreateService;

    @Operation(summary = "创建商品SPU", description = "创建新的商品SPU")
    @PostMapping
    public CommonResult<Long> createSpu(@Valid @RequestBody ProductSpuCreateReqVO createReqVO) {
        Long spuId = productSpuCreateService.createSpu(createReqVO);
        return CommonResult.success("创建成功", spuId);
    }
}