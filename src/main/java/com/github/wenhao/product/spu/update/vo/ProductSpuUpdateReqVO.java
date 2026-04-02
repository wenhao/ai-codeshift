package com.github.wenhao.product.spu.update.vo;

import com.github.wenhao.product.spu.create.vo.ProductSkuSaveReqVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 商品SPU更新请求参数
 */
@Data
@Schema(description = "商品SPU更新请求参数")
public class ProductSpuUpdateReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "商品编号不能为空")
    @Schema(description = "商品编号", required = true)
    private Long id;

    @NotBlank(message = "商品名称不能为空")
    @Schema(description = "商品名称", required = true)
    private String name;

    @NotBlank(message = "商品关键字不能为空")
    @Schema(description = "关键字", required = true)
    private String keyword;

    @NotBlank(message = "商品简介不能为空")
    @Schema(description = "商品简介", required = true)
    private String introduction;

    @NotBlank(message = "商品详情不能为空")
    @Schema(description = "商品详情", required = true)
    private String description;

    @NotNull(message = "商品分类不能为空")
    @Schema(description = "商品分类编号", required = true)
    private Long categoryId;

    @NotNull(message = "商品品牌不能为空")
    @Schema(description = "商品品牌编号", required = true)
    private Long brandId;

    @NotBlank(message = "商品封面图不能为空")
    @Schema(description = "商品封面图", required = true)
    private String picUrl;

    @NotEmpty(message = "商品轮播图不能为空")
    @Schema(description = "商品轮播图", required = true)
    private List<String> sliderPicUrls;

    @NotNull(message = "商品排序字段不能为空")
    @Schema(description = "排序字段", required = true)
    private Integer sort;

    @NotNull(message = "商品规格类型不能为空")
    @Schema(description = "规格类型：false-单规格，true-多规格", required = true)
    private Boolean specType;

    @NotEmpty(message = "配送方式不能为空")
    @Schema(description = "配送方式数组", required = true)
    private List<Integer> deliveryTypes;

    @Schema(description = "物流配置模板编号")
    private Long deliveryTemplateId;

    @NotNull(message = "商品赠送积分不能为空")
    @Schema(description = "赠送积分", required = true)
    private Integer giveIntegral;

    @NotNull(message = "商品分销类型不能为空")
    @Schema(description = "分销类型", required = true)
    private Boolean subCommissionType;

    @Schema(description = "虚拟销量")
    private Integer virtualSalesCount;

    @Schema(description = "商品销量")
    private Integer salesCount;

    @Schema(description = "浏览量")
    private Integer browseCount;

    @Schema(description = "商品状态")
    private Integer status;

    @Valid
    @Schema(description = "SKU列表")
    private List<ProductSkuSaveReqVO> skus;
}