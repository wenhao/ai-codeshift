package com.github.wenhao.product.spu.create.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 商品SKU创建请求参数
 */
@Data
@Schema(description = "商品SKU创建请求参数")
public class ProductSkuSaveReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "商品SKU名字不能为空")
    @Schema(description = "商品SKU名字", required = true)
    private String name;

    @NotNull(message = "销售价格，单位：分不能为空")
    @Schema(description = "销售价格，单位：分", required = true)
    private Integer price;

    @Schema(description = "市场价")
    private Integer marketPrice;

    @Schema(description = "成本价")
    private Integer costPrice;

    @Schema(description = "条形码")
    private String barCode;

    @NotBlank(message = "图片地址不能为空")
    @Schema(description = "图片地址", required = true)
    private String picUrl;

    @NotNull(message = "库存不能为空")
    @Schema(description = "库存", required = true)
    private Integer stock;

    @Schema(description = "商品重量，单位：kg千克")
    private Double weight;

    @Schema(description = "商品体积，单位：m³立方米")
    private Double volume;

    @Schema(description = "一级分销的佣金，单位：分")
    private Integer firstBrokeragePrice;

    @Schema(description = "二级分销的佣金，单位：分")
    private Integer secondBrokeragePrice;

    @Schema(description = "属性数组")
    private List<Property> properties;

    /**
     * 属性
     */
    @Data
    public static class Property implements Serializable {
        private static final long serialVersionUID = 1L;

        @Schema(description = "属性编号")
        private Long propertyId;

        @Schema(description = "属性名字")
        private String propertyName;

        @Schema(description = "属性值编号")
        private Long valueId;

        @Schema(description = "属性值名字")
        private String valueName;
    }
}