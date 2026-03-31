package com.github.wenhao.product.category.list.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

@Data
@Schema(description = "商品分类列表请求参数")
public class ProductCategoryListReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "开启状态")
    private Integer status;

    @Schema(description = "父分类编号")
    private Long parentId;

    @Schema(description = "父分类编号数组")
    private Collection<Long> parentIds;
}