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
@TableName(value = "product_sku", autoResultMap = true)
public class ProductSku implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * SPU编号
     */
    private Long spuId;

    /**
     * 属性数组
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Property> properties;

    /**
     * 商品价格，单位：分
     */
    private Integer price;

    /**
     * 市场价，单位：分
     */
    private Integer marketPrice;

    /**
     * 成本价，单位：分
     */
    private Integer costPrice;

    /**
     * 商品条码
     */
    private String barCode;

    /**
     * 图片地址
     */
    private String picUrl;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 商品重量，单位：kg 千克
     */
    private Double weight;

    /**
     * 商品体积，单位：m³ 立方米
     */
    private Double volume;

    /**
     * 一级分销的佣金，单位：分
     */
    private Integer firstBrokeragePrice;

    /**
     * 二级分销的佣金，单位：分
     */
    private Integer secondBrokeragePrice;

    /**
     * 商品销量
     */
    private Integer salesCount;

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

    /**
     * 商品属性
     */
    @Data
    public static class Property implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * 属性编号
         */
        private Long propertyId;

        /**
         * 属性名字
         */
        private String propertyName;

        /**
         * 属性值编号
         */
        private Long valueId;

        /**
         * 属性值名字
         */
        private String valueName;
    }
}