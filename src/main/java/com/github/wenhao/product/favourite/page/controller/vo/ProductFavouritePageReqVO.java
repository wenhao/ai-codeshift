package com.github.wenhao.product.favourite.page.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "商品收藏分页请求参数")
public class ProductFavouritePageReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "页码，从 1 开始", required = false)
    private Integer pageNo = 1;

    @Schema(description = "每页条数，最大值 200", required = false)
    private Integer pageSize = 10;

    @Schema(description = "用户编号", required = false)
    private Long userId;
}