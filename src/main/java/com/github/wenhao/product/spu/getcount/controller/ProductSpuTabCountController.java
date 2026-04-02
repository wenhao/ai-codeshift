package com.github.wenhao.product.spu.getcount.controller;

import com.github.wenhao.product.common.pojo.CommonResult;
import com.github.wenhao.product.spu.getcount.service.ProductSpuTabCountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 商品SPU分页TabCount查询Controller
 */
@Tag(name = "商品SPU管理", description = "商品SPU分页TabCount查询接口")
@RestController
@RequestMapping("/product/spu/get-count")
@RequiredArgsConstructor
@Validated
public class ProductSpuTabCountController {

    private final ProductSpuTabCountService productSpuTabCountService;

    @Operation(summary = "查询商品SPU分页Tab数量统计", description = "按销售中、仓库中、售空、警戒库存、回收站5种维度统计数量")
    @GetMapping
    public CommonResult<Map<Integer, Long>> getTabCount() {
        Map<Integer, Long> tabCountMap = productSpuTabCountService.getTabCount();
        return CommonResult.success(tabCountMap);
    }
}