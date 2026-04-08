package com.github.wenhao.product.brand.create.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "创建商品品牌请求参数")
public class ProductBrandCreateReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "品牌名称")
    @NotBlank(message = "品牌名称不能为空")
    private String name;

    @Schema(description = "品牌图片")
    @NotBlank(message = "品牌图片不能为空")
    private String picUrl;

    @Schema(description = "品牌排序")
    @NotNull(message = "品牌排序不能为空")
    private Integer sort;

    @Schema(description = "状态")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @Schema(description = "品牌描述")
    private String description;
}