package ua.lemekh.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ua.lemekh.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Ostap on 01.06.2017.
 */
@Repository
public class CategoryImp implements CategoryDao {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Category> list() {
        Session session = entityManager.unwrap(Session.class);
        List<Category> categoryList = session.createQuery("From Category where parentCategory=null").list();
        return categoryList;
    }

    public Category createCategory(Category category){
        entityManager.persist(category);
        entityManager.flush();
        return category;
    }

    @Override
    public void deleteCategory(int id) {
        Category category = entityManager.find(Category.class, id);
        if (category != null) {
            entityManager.remove(category);
        }
    }

    @Override
    public Category getCategoryById(int id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public List<String> getCategories() {
        return entityManager.createQuery("select c.name from Category c where c.parentCategory is not null ").getResultList();
    }
}
