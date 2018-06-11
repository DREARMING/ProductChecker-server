package com.mvcoder.dao;

import com.mvcoder.bean.Product;

import java.util.List;

public interface IProductDao {

    Product addProduct(Product product);

    boolean delProduct(int productType);

    Product getProduct(int productType);

    List<Product> getProductList(int userId);

    List<Product> getProductList(int userId, int pageSize, int pageNum, String searchStr);

    boolean updateProduct(Product product);
}
