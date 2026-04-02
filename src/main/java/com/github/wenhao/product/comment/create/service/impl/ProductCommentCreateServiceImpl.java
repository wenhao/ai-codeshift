package com.github.wenhao.product.comment.create.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.wenhao.product.comment.create.mapper.ProductCommentCreateMapper;
import com.github.wenhao.product.comment.create.mapper.ProductSkuForCommentCreateMapper;
import com.github.wenhao.product.comment.create.mapper.ProductSpuForCommentCreateMapper;
import com.github.wenhao.product.comment.create.service.ProductCommentCreateService;
import com.github.wenhao.product.comment.create.vo.ProductCommentCreateReqVO;
import com.github.wenhao.product.comment.entity.po.ProductComment;
import com.github.wenhao.product.exception.BusinessException;
import com.github.wenhao.product.spu.entity.po.ProductSku;
import com.github.wenhao.product.spu.entity.po.ProductSpu;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 商品评价创建Service实现类
 */
@Service
@RequiredArgsConstructor
public class ProductCommentCreateServiceImpl implements ProductCommentCreateService {

    private final ProductSkuForCommentCreateMapper productSkuForCommentCreateMapper;
    private final ProductSpuForCommentCreateMapper productSpuForCommentCreateMapper;
    private final ProductCommentCreateMapper productCommentCreateMapper;

    @Override
    public Boolean createComment(ProductCommentCreateReqVO createReqVO) {
        // 2. 参数校验：根据商品SKU编号查询数据库校验商品SKU是否真实存在
        ProductSku productSku = productSkuForCommentCreateMapper.selectOne(
            new LambdaQueryWrapper<ProductSku>()
                .eq(ProductSku::getId, createReqVO.getSkuId())
        );
        if (productSku == null) {
            throw new BusinessException("1_008_006_000", "商品 SKU 不存在");
        }

        // 3. 参数校验：根据商品SKU编号查询商品SPU编号，再校验商品SPU是否真实存在
        ProductSpu productSpu = productSpuForCommentCreateMapper.selectOne(
            new LambdaQueryWrapper<ProductSpu>()
                .eq(ProductSpu::getId, productSku.getSpuId())
        );
        if (productSpu == null) {
            throw new BusinessException("1_008_005_000", "商品 SPU 不存在");
        }

        // 4. 数据转换：将请求参数转换为数据库实体对象
        ProductComment productComment = new ProductComment();
        BeanUtils.copyProperties(createReqVO, productComment);

        // 设置SPU相关信息
        productComment.setSpuId(productSku.getSpuId());
        productComment.setSpuName(productSpu.getName());

        // 设置SKU相关信息
        productComment.setSkuPicUrl(productSku.getPicUrl());
        productComment.setSkuProperties(productSku.getProperties());

        // 设置默认评分（使用描述星级作为总评分）
        productComment.setScores(createReqVO.getDescriptionScores());

        // 设置默认可见
        productComment.setVisible(true);

        // 5. 数据库操作：插入数据库
        productCommentCreateMapper.insert(productComment);

        // 6. 方法返回：插入成功返回true
        return true;
    }
}