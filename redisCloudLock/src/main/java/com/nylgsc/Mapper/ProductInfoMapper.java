package com.nylgsc.Mapper;

import com.nylgsc.entity.ProductInfo;

public interface ProductInfoMapper {
    public ProductInfo selectByPrimaryKey(Long productId);

    void updateByPrimaryKey(ProductInfo productInfo);
}
