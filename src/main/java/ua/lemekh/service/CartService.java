package ua.lemekh.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.lemekh.model.Products;

import java.util.List;

/**
 * Created by Ostap on 19.06.2017.
 */

public interface CartService {
    boolean isExecute(int id);

    void addToCart(int id);

    List<Products> getCartList();

    Products deleteFromcart(int id);


}
