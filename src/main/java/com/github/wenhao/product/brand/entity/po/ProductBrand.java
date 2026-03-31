package com.github.wenhao.product.brand.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("product_brand")
public class ProductBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌图片
     */
    private String picUrl;

    /**
     * 品牌排序
     */
    private Integer sort;

    /**
     * 品牌描述
     */
    private String description;

    /**
     * 状态（枚举CommonStatusEnum）
     */
    private Integer status;

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