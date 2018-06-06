package ua.lemekh.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ua.lemekh.model.Group;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ostap on 01.06.2017.
 */
@Repository
public class GroupDaoImp implements GroupDao {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Group> list() {
        return entityManager.createQuery("SELECT g FROM Group g").getResultList();
    }

    public Group createCategory(Group category){
        entityManager.persist(category);
        entityManager.flush();
        return category;
    }

    @Override
    public void deleteCategory(int id) {
        Group category = entityManager.find(Group.class, id);
        if (category != null) {
            entityManager.remove(category);
        }
    }

    @Override
    public Group getCategoryById(int id) {
        return entityManager.find(Group.class, id);
    }

    @Override
    public List<String> getCategories() {
        return entityManager.createQuery("SELECT g.name FROM Group g").getResultList();
    }

    @Override
    public List<Group> getGroupbByNames(List<String> names) {
        return entityManager.createQuery("SELECT g FROM Group g WHERE g.name IN :names").setParameter("names",names)
                .getResultList();
    }
}
