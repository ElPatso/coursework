package ua.lemekh.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import ua.lemekh.model.Comments;
import ua.lemekh.model.Products;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Ostap on 16.06.2017.
 */
@Repository
public class ProductDaoImpl implements ProductDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Products> getProductList(Integer offset, Integer maxResult) {
        Query query = entityManager.createQuery("select p from Products  p ").setFirstResult(offset!=null?offset:0).setMaxResults(maxResult!=null?maxResult:12);
        List<Products> products = (List<Products>) query.getResultList();
        return products;
    }

    @Override
    public Long count() {
        Session session  = entityManager.unwrap(Session.class);
        return (Long)session
                .createCriteria(Products.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    @Override
    public Products getProductById(int id) {
        Products product = entityManager.find(Products.class, id);
        return product;
    }
    @Override
    public Comments addComment(Comments comments) {
        entityManager.persist(comments);
        entityManager.flush();
        return comments;
    }

    @Override
    public Products addProduct(Products products) {
        entityManager.persist(products);
        entityManager.flush();
        return products;
    }

    @Override
    public Comments findComment(int id) {
        return entityManager.find(Comments.class, id);
    }

    @Override
    public List<Products> getProductListBySearch(String search) {
        Query query = entityManager.createQuery("select p from Products  p where p.name like ?1").setParameter(1,search+"%");
        List<Products> products = (List<Products>) query.getResultList();
        return products;
    }


}
