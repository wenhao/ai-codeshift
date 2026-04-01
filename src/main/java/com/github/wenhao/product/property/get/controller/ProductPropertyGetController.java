package com.github.wenhao.product.property.get.controller;

import com.github.wenhao.product.common.pojo.CommonResult;
import com.github.wenhao.product.property.get.controller.vo.ProductPropertyGetReqVO;
import com.github.wenhao.product.property.get.controller.vo.ProductPropertyGetRespVO;
import com.github.wenhao.product.property.get.service.ProductPropertyGetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 商品属性项查询Controller
 */
@Tag(name = "商品属性项管理", description = "商品属性项查询接口")
@RestController
@RequestMapping("/product/property/get")
@RequiredArgsConstructor
@Validated
public class ProductPropertyGetController {

    private final ProductPropertyGetService productPropertyGetService;

    @Operation(summary = "查询商品属性项", description = "根据属性项编号查询商品属性项")
    @GetMapping
    public CommonResult<ProductPropertyGetRespVO> getProperty(@Valid ProductPropertyGetReqVO reqVO) {
        ProductPropertyGetRespVO respVO = productPropertyGetService.getProperty(reqVO.getId());
        return CommonResult.success(respVO);
    }
}