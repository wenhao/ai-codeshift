package com.github.wenhao.product.category.get.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Schema(description = "商品分类查询请求参数")
public class ProductCategoryGetReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "分类编号不能为空")
    @Schema(description = "分类编号", required = true)
    private Long id;
}