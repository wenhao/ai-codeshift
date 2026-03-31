package com.github.wenhao.product.brand.update.controller;

import com.github.wenhao.product.brand.update.service.ProductBrandUpdateService;
import com.github.wenhao.product.brand.update.vo.ProductBrandUpdateReqVO;
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
 * 商品品牌更新Controller
 */
@Tag(name = "商品品牌管理", description = "商品品牌更新接口")
@RestController
@RequestMapping("/product/brand")
@RequiredArgsConstructor
@Validated
public class ProductBrandUpdateController {

    private final ProductBrandUpdateService productBrandUpdateService;

    @Operation(summary = "更新商品品牌", description = "更新商品品牌信息")
    @PutMapping
    public CommonResult<Boolean> updateBrand(@Valid @RequestBody ProductBrandUpdateReqVO updateReqVO) {
        Boolean result = productBrandUpdateService.updateBrand(updateReqVO);
        return CommonResult.success("更新成功", result);
    }
}