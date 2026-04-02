package com.github.wenhao.product.comment.page.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "商品评价分页请求参数")
public class ProductCommentPageReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "页码，从 1 开始", required = false)
    private Integer pageNo = 1;

    @Schema(description = "每页条数，最大值 200", required = false)
    private Integer pageSize = 10;

    @Schema(description = "商品SPU编号", required = false)
    private Long spuId;

    @Schema(description = "商品SKU编号", required = false)
    private Long skuId;

    @Schema(description = "是否可见（true:显示；false:隐藏）", required = false)
    private Boolean visible;

    @Schema(description = "评分星级（1-5分）", required = false)
    private Integer scores;

    @Schema(description = "创建时间范围（数组）", required = false)
    private LocalDateTime[] createTime;
}