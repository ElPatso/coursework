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

    Products addProduct(Products products);

    List<Products> getProductListBySearch(String search);

    List<Products> getProductsByCategory(String category, Integer offset, Integer maxResult);

    Long CountForCategory(String category);

}

