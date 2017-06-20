package ua.lemekh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ua.lemekh.dao.ProductDao;
import ua.lemekh.model.Products;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Ostap on 19.06.2017.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements CartService {
    @Autowired
    ProductDao productDao;

    private List<Products> listCart = new ArrayList<Products>();

    @Override
    public boolean isExecute(int id) {
        for (Products p : listCart)
            if (p.getId() == id) return true;
        return false;
    }

    @Override
    public void addToCart(int id) {
        listCart.add(productDao.getProductById(id));
    }

    @Override
    public List<Products> getCartList() {
        return listCart;
    }

    @Override
    public Products deleteFromcart(int id) {
        Iterator<Products> iterator = this.listCart.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) iterator.remove();
        }
        return null;
    }
}