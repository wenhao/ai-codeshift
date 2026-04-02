package com.github.wenhao.product.comment.create.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@Schema(description = "商品评价创建请求参数")
public class ProductCommentCreateReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "评价人编号不能为空")
    @Schema(description = "评价人编号", required = true)
    private Long userId;

    @NotNull(message = "评价订单项编号不能为空")
    @Schema(description = "评价订单项编号", required = true)
    private Long orderItemId;

    @NotBlank(message = "评价人名称不能为空")
    @Schema(description = "评价人名称", required = true)
    private String userNickname;

    @NotBlank(message = "评价人头像不能为空")
    @Schema(description = "评价人头像", required = true)
    private String userAvatar;

    @NotNull(message = "商品SKU编号不能为空")
    @Schema(description = "商品SKU编号", required = true)
    private Long skuId;

    @NotNull(message = "描述星级不能为空")
    @Schema(description = "描述星级 1-5分", required = true)
    private Integer descriptionScores;

    @NotNull(message = "服务星级不能为空")
    @Schema(description = "服务星级 1-5分", required = true)
    private Integer benefitScores;

    @NotBlank(message = "评论内容不能为空")
    @Schema(description = "评论内容", required = true)
    private String content;

    @Size(max = 9, message = "评论图片地址数组长度不能超过9张")
    @Schema(description = "评论图片地址数组，最多上传9张")
    private List<String> picUrls;
}