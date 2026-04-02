package com.github.wenhao.product.comment.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.github.wenhao.product.spu.entity.po.ProductSku;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName(value = "product_comment", autoResultMap = true)
public class ProductComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 评价人的用户编号（关联MemberUserDO的id编号）
     */
    private Long userId;

    /**
     * 评价人名称
     */
    private String userNickname;

    /**
     * 评价人头像
     */
    private String userAvatar;

    /**
     * 是否匿名
     */
    private Boolean anonymous;

    /**
     * 交易订单编号（关联TradeOrderDO的id编号）
     */
    private Long orderId;

    /**
     * 交易订单项编号（关联TradeOrderItemDO的id编号）
     */
    private Long orderItemId;

    /**
     * 商品SPU编号（关联ProductSpuDO的id编号）
     */
    private Long spuId;

    /**
     * 商品SPU名称（关联ProductSpuDO的name）
     */
    private String spuName;

    /**
     * 商品SKU编号（关联ProductSkuDO的id编号）
     */
    private Long skuId;

    /**
     * 商品SKU图片地址（关联ProductSkuDO的picUrl）
     */
    private String skuPicUrl;

    /**
     * 属性数组，JSON格式（关联ProductSkuDO的properties）
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ProductSku.Property> skuProperties;

    /**
     * 是否可见（true:显示；false:隐藏）
     */
    private Boolean visible;

    /**
     * 评分星级（1-5分）
     */
    private Integer scores;

    /**
     * 描述星级（1-5星）
     */
    private Integer descriptionScores;

    /**
     * 服务星级（1-5星）
     */
    private Integer benefitScores;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论图片地址数组
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> picUrls;

    /**
     * 商家是否回复
     */
    private Boolean replyStatus;

    /**
     * 回复管理员编号（关联AdminUserDO的id编号）
     */
    private Long replyUserId;

    /**
     * 商家回复内容
     */
    private String replyContent;

    /**
     * 商家回复时间
     */
    private LocalDateTime replyTime;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT, jdbcType = org.apache.ibatis.type.JdbcType.VARCHAR)
    private String creator;

    @TableField(fill = FieldFill.INSERT_UPDATE, jdbcType = org.apache.ibatis.type.JdbcType.VARCHAR)
    private String updater;

    @TableLogic
    private Boolean deleted;
}