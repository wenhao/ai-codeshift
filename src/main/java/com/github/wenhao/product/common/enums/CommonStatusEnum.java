package com.github.wenhao.product.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonStatusEnum {

    ENABLE(1, "启用"),
    DISABLE(0, "禁用");

    private final Integer status;
    private final String desc;
}