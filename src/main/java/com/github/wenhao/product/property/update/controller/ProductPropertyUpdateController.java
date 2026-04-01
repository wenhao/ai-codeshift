package com.github.wenhao.product.property.update.controller;

import com.github.wenhao.product.property.update.service.ProductPropertyUpdateService;
import com.github.wenhao.product.property.update.vo.ProductPropertyUpdateReqVO;
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
 * 商品属性项更新Controller
 */
@Tag(name = "商品属性项管理", description = "商品属性项更新接口")
@RestController
@RequestMapping("/product/property")
@RequiredArgsConstructor
@Validated
public class ProductPropertyUpdateController {

    private final ProductPropertyUpdateService productPropertyUpdateService;

    @Operation(summary = "更新商品属性项", description = "更新商品属性项信息")
    @PutMapping("/update")
    public CommonResult<Boolean> updateProperty(@Valid @RequestBody ProductPropertyUpdateReqVO updateReqVO) {
        Boolean result = productPropertyUpdateService.updateProperty(updateReqVO);
        return CommonResult.success("更新成功", result);
    }
}