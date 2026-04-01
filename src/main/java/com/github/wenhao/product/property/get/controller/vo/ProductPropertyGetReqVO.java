package com.github.wenhao.product.property.get.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Schema(description = "商品属性项查询请求参数")
public class ProductPropertyGetReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "属性项编号不能为空")
    @Schema(description = "属性项编号", required = true)
    private Long id;
}