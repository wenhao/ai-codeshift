package com.github.wenhao.product.brand.list.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "商品品牌列表请求参数")
public class ProductBrandListReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "品牌名称", required = false)
    private String name;
}