package ua.lemekh.dao;

import ua.lemekh.model.Comments;
import ua.lemekh.model.Products;

import java.util.List;

/**
 * Created by Ostap on 16.06.2017.
 */
public interface ProductDao {
    List<Products> getProductList(Integer offset, Integer maxResult);

    Long count();

    Products getProductById(int id);

    Comments addComment(Comments comments);

    Products addProduct(Products products);

    Comments findComment(int id);

    List<Products> getProductListBySearch(String search);

}

