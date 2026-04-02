package com.github.wenhao.product.spu.listallsimple.controller;

import com.github.wenhao.product.spu.listallsimple.controller.vo.ProductSpuListAllSimpleRespVO;
import com.github.wenhao.product.spu.listallsimple.service.ProductSpuListAllSimpleService;
import com.github.wenhao.product.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品SPU精简信息查询Controller
 */
@Tag(name = "商品SPU管理", description = "商品SPU精简信息查询接口")
@RestController
@RequestMapping("/product/spu/list-all-simple")
@RequiredArgsConstructor
@Validated
public class ProductSpuListAllSimpleController {

    private final ProductSpuListAllSimpleService productSpuListAllSimpleService;

    @Operation(summary = "查询SPU精简信息列表", description = "查询SPU精简信息列表，主要用于前端的下拉选项")
    @GetMapping
    public CommonResult<List<ProductSpuListAllSimpleRespVO>> getSpuSimpleList() {
        List<ProductSpuListAllSimpleRespVO> respVOList = productSpuListAllSimpleService.getSpuSimpleList();
        return CommonResult.success(respVOList);
    }
}