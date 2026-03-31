package com.github.wenhao.product.brand.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("product_brand")
public class ProductBrand {
    @TableId
    private Long id;

    private String name;

    private String picUrl;

    private Integer sort;

    private String description;

    private Integer status; // 状态（枚举CommonStatusEnum）

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private String creator;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updater;

    @TableLogic
    private Boolean deleted;
}