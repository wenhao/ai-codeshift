package com.github.wenhao.product.property.update.service;

import com.github.wenhao.product.property.update.vo.ProductPropertyUpdateReqVO;

/**
 * 商品属性项更新Service接口
 */
public interface ProductPropertyUpdateService {

    /**
     * 更新商品属性项
     *
     * @param updateReqVO 更新请求参数
     * @return 更新结果
     */
    Boolean updateProperty(ProductPropertyUpdateReqVO updateReqVO);
}