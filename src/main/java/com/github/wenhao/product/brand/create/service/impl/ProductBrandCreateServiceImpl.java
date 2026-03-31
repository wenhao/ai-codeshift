package com.github.wenhao.product.brand.create.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.wenhao.product.brand.create.mapper.ProductBrandCreateMapper;
import com.github.wenhao.product.brand.create.service.ProductBrandCreateService;
import com.github.wenhao.product.brand.create.vo.ProductBrandCreateReqVO;
import com.github.wenhao.product.brand.entity.po.ProductBrand;
import com.github.wenhao.product.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 商品品牌创建Service实现类
 */
@Service
@RequiredArgsConstructor
public class ProductBrandCreateServiceImpl implements ProductBrandCreateService {

    private final ProductBrandCreateMapper productBrandCreateMapper;

    @Override
    public Long createBrand(ProductBrandCreateReqVO createReqVO) {
        // 2. 参数校验：根据传入的商品品牌名称查询数据库校验品牌名称是否已存在
        ProductBrand existBrand = productBrandCreateMapper.selectOne(
            new LambdaQueryWrapper<ProductBrand>()
                .eq(ProductBrand::getName, createReqVO.getName())
        );
        if (existBrand != null) {
            throw new BusinessException("1_008_002_002", "品牌名称已存在");
        }

        // 3. 数据转换：将请求参数转换为数据库实体对象
        ProductBrand productBrand = new ProductBrand();
        BeanUtils.copyProperties(createReqVO, productBrand);

        // 4. 数据库操作：插入数据库
        productBrandCreateMapper.insert(productBrand);

        // 5. 方法返回：返回数据库自动生成的商品品牌编号
        return productBrand.getId();
    }
}