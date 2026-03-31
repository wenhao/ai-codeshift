package com.github.wenhao.product.brand.list.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "商品品牌列表响应参数")
public class ProductBrandListRespVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "品牌编号", required = true)
    private Long id;

    @Schema(description = "品牌名称", required = true)
    private String name;

    @Schema(description = "品牌图片", required = false)
    private String picUrl;

    @Schema(description = "品牌排序", required = false)
    private Integer sort;

    @Schema(description = "品牌描述", required = false)
    private String description;

    @Schema(description = "状态", required = false)
    private Integer status;

    @Schema(description = "创建时间", required = false)
    private LocalDateTime createTime;
}