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
    public Comments addComment(Comments comments){
        return   productDao.addComment(comments);
    }

    @Transactional
    @Override
    public Products addProduct(Products products) {

        return productDao.addProduct(products);
    }
    @Transactional
    @Override
    public Comments findComment(int id) {
        return productDao.findComment(id);
    }
    @Transactional
    @Override
    public List<Products> getProductListBySearch(String search){
       return this.productDao.getProductListBySearch(search);
    }


}
