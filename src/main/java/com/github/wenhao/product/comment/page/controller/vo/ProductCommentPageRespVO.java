package com.github.wenhao.product.comment.page.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "商品评价分页响应参数")
public class ProductCommentPageRespVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "评价编号")
    private Long id;

    @Schema(description = "评价人的用户编号")
    private Long userId;

    @Schema(description = "评价人名称")
    private String userNickname;

    @Schema(description = "评价人头像")
    private String userAvatar;

    @Schema(description = "是否匿名")
    private Boolean anonymous;

    @Schema(description = "交易订单编号")
    private Long orderId;

    @Schema(description = "交易订单项编号")
    private Long orderItemId;

    @Schema(description = "商品SPU编号")
    private Long spuId;

    @Schema(description = "商品SPU名称")
    private String spuName;

    @Schema(description = "商品SKU编号")
    private Long skuId;

    @Schema(description = "商品SKU图片地址")
    private String skuPicUrl;

    @Schema(description = "是否可见（true:显示；false:隐藏）")
    private Boolean visible;

    @Schema(description = "评分星级（1-5分）")
    private Integer scores;

    @Schema(description = "描述星级（1-5星）")
    private Integer descriptionScores;

    @Schema(description = "服务星级（1-5星）")
    private Integer benefitScores;

    @Schema(description = "评论内容")
    private String content;

    @Schema(description = "评论图片地址数组")
    private List<String> picUrls;

    @Schema(description = "商家是否回复")
    private Boolean replyStatus;

    @Schema(description = "商家回复内容")
    private String replyContent;

    @Schema(description = "商家回复时间")
    private LocalDateTime replyTime;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}