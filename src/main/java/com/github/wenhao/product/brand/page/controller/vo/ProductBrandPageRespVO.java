package com.github.wenhao.product.brand.page.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "商品品牌分页响应参数")
public class ProductBrandPageRespVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "品牌编号")
    private Long id;

    @Schema(description = "品牌名称")
    private String name;

    @Schema(description = "品牌图片地址")
    private String picUrl;

    @Schema(description = "品牌排序")
    private Integer sort;

    @Schema(description = "品牌描述")
    private String description;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}