package com.github.wenhao.product.brand.get.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Schema(description = "商品品牌查询请求参数")
public class ProductBrandGetReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "品牌编号不能为空")
    @Schema(description = "品牌编号", required = true)
    private Long id;
}