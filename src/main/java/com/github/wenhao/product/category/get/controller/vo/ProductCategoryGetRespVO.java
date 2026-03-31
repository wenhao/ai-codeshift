package com.github.wenhao.product.category.get.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "商品分类查询响应参数")
public class ProductCategoryGetRespVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "分类编号")
    private Long id;

    @Schema(description = "父分类编号")
    private Long parentId;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "移动端分类图")
    private String picUrl;

    @Schema(description = "分类排序")
    private Integer sort;

    @Schema(description = "开启状态")
    private Integer status;

    @Schema(description = "分类描述")
    private String description;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}