package ua.lemekh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lemekh.dao.ProductDao;
import ua.lemekh.model.Comments;
import ua.lemekh.model.Products;

import java.util.List;

/**
 * Created by Ostap on 16.06.2017.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public List<Products> getProductList(Integer offset, Integer maxResult) {
        return productDao.getProductList(offset, maxResult);
    }
    @Transactional
    @Override
    public Long count() {
        return productDao.count();
    }

    @Transactional
    @Override
    public Products getProductById(int id) {
        return productDao.getProductById(id);
    }

    @Transactional
    @Override
    public Products addProduct(Products products) {

        return productDao.addProduct(products);
    }

    @Transactional
    @Override
    public List<Products> getProductListBySearch(String search){
       return this.productDao.getProductListBySearch(search);
    }

    @Transactional
    @Override
    public List<Products> getProductsByCategory(String category, Integer offset, Integer maxResult) {
        return this.productDao.getProductsByCategory(category, offset, maxResult);
    }

    @Transactional
    @Override
    public Long CountForCategory(String category) {
        return this.productDao.CountForCategory(category);
    }

    @Transactional
    @Override
    public void updateproduct(Products products) {
        this.productDao.updateProduct(products);
    }

    @Transactional
    @Override
    public void deleteProduct(int id) {
        this.productDao.deleteProduct(id);
    }


}
