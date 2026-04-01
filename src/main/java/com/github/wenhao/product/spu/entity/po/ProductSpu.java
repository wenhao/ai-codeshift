package com.github.wenhao.product.spu.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName(value = "product_spu", autoResultMap = true)
public class ProductSpu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 商品简介
     */
    private String introduction;

    /**
     * 商品详情
     */
    private String description;

    /**
     * 商品分类编号，关联ProductCategoryDO.getId()
     */
    private Long categoryId;

    /**
     * 商品品牌编号，关联ProductBrandDO.getId()
     */
    private Long brandId;

    /**
     * 商品封面图
     */
    private String picUrl;

    /**
     * 商品轮播图
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> sliderPicUrls;

    /**
     * 排序字段
     */
    private Integer sort;

    /**
     * 商品状态，枚举ProductSpuStatusEnum，回收站(-1)，下架(0)，上架(1)
     */
    private Integer status;

    /**
     * 规格类型：false-单规格，true-多规格
     */
    private Boolean specType;

    /**
     * 商品价格，单位：分（取SKU最低单价）
     */
    private Integer price;

    /**
     * 市场价，单位：分（取SKU最低单价）
     */
    private Integer marketPrice;

    /**
     * 成本价，单位：分（取SKU最低单价）
     */
    private Integer costPrice;

    /**
     * 库存（SKU库存求和）
     */
    private Integer stock;

    /**
     * 配送方式数组，对应DeliveryTypeEnum枚举
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Integer> deliveryTypes;

    /**
     * 物流配置模板编号
     */
    private Long deliveryTemplateId;

    /**
     * 赠送积分
     */
    private Integer giveIntegral;

    /**
     * 分销类型：false-默认，true-自行设置
     */
    private Boolean subCommissionType;

    /**
     * 商品销量
     */
    private Integer salesCount;

    /**
     * 虚拟销量
     */
    private Integer virtualSalesCount;

    /**
     * 浏览量
     */
    private Integer browseCount;

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