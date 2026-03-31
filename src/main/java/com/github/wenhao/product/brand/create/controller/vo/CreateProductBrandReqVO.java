package com.github.wenhao.product.brand.create.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "创建商品品牌-请求参数")
@Data
public class CreateProductBrandReqVO {

    @Schema(description = "品牌名称", required = true)
    @NotBlank(message = "品牌名称不能为空")
    private String name;

    @Schema(description = "品牌图片", required = true)
    @NotBlank(message = "品牌图片不能为空")
    private String picUrl;

    @Schema(description = "品牌排序", required = true)
    @NotNull(message = "品牌排序不能为空")
    private Integer sort;

    @Schema(description = "状态", required = true)
    @NotBlank(message = "状态不能为空")
    private Integer status;

    @Schema(description = "品牌描述")
    private String description;
}