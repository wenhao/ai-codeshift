package com.github.wenhao.product.spu.update.controller;

import com.github.wenhao.product.common.pojo.CommonResult;
import com.github.wenhao.product.spu.update.service.ProductSpuUpdateService;
import com.github.wenhao.product.spu.update.vo.ProductSpuUpdateReqVO;
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
 * 商品SPU更新Controller
 */
@Tag(name = "商品SPU管理", description = "商品SPU更新接口")
@RestController
@RequestMapping("/product/spu/update")
@RequiredArgsConstructor
@Validated
public class ProductSpuUpdateController {

    private final ProductSpuUpdateService productSpuUpdateService;

    @Operation(summary = "更新商品SPU", description = "更新商品SPU")
    @PutMapping
    public CommonResult<Boolean> updateSpu(@Valid @RequestBody ProductSpuUpdateReqVO updateReqVO) {
        Boolean result = productSpuUpdateService.updateSpu(updateReqVO);
        return CommonResult.success("更新成功", result);
    }
}