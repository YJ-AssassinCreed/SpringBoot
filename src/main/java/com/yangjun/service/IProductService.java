package com.yangjun.service;

import com.github.pagehelper.PageInfo;
import com.yangjun.common.ServerResponse;
import com.yangjun.pojo.Product;
import com.yangjun.vo.ProductDetailVo;

public interface IProductService {
    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse<String> setSaleStatus(Integer productId,Integer status);

    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    ServerResponse getProductList(int pageNum,int pageSize);

    ServerResponse<PageInfo> searchProduct(String productName, Integer productId, int pageNum, int pageSize);
}
