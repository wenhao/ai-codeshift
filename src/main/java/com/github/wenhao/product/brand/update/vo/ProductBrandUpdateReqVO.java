package com.github.wenhao.product.brand.update.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Schema(description = "商品品牌更新请求参数")
public class ProductBrandUpdateReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "品牌编号不能为空")
    @Schema(description = "品牌编号（更新必填）", required = true)
    private Long id;

    @NotBlank(message = "品牌名称不能为空")
    @Schema(description = "品牌名称", required = true)
    private String name;

    @NotBlank(message = "品牌图片不能为空")
    @Schema(description = "品牌图片", required = true)
    private String picUrl;

    @NotNull(message = "品牌排序不能为空")
    @Schema(description = "品牌排序", required = true)
    private Integer sort;

    @NotNull(message = "品牌状态不能为空")
    @Schema(description = "品牌状态", required = true)
    private Integer status;

    @Schema(description = "品牌描述")
    private String description;
}