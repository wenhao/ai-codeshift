package com.github.wenhao.product.comment.reply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Schema(description = "商品评价回复请求参数")
public class ProductCommentReplyReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "评论编号不能为空")
    @Schema(description = "评论编号", required = true)
    private Long id;

    @NotNull(message = "回复管理员编号不能为空")
    @Schema(description = "回复管理员编号", required = true)
    private Long replyUserId;

    @NotBlank(message = "商家回复内容不能为空")
    @Schema(description = "商家回复内容", required = true)
    private String replyContent;
}