package ua.lemekh.dao;

import org.springframework.stereotype.Repository;
import ua.lemekh.model.Group;
import ua.lemekh.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Ostap on 13.06.2017.
 */
@Repository
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;

    public void save(User user) {
        entityManager.persist(user);
    }

    public User findByUsername(String username) {
        Query query = entityManager.createQuery("select u From User u where u.username = ?1").setParameter(1, username);
        User user = null;
        try {
            user = (User) query.getSingleResult();
        } catch (NoResultException e) {}

        return user;
    }

    public User findByEmail(String email) {
        Query query = entityManager.createQuery("select u From User u where u.email = ?1").setParameter(1, email);
        User user = null;
        try {
            user = (User) query.getSingleResult();
        } catch (NoResultException e) {}

        return user;
    }

    public User updateUser(User user){
       return entityManager.merge(user);
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("select u From User u").getResultList();

    }

    @Override
    public void deleteUser(int id){
        User user = entityManager.find(User.class, id);
        if (user != null){
            entityManager.remove(user);
        }
    }

    @Override
    public User getUserById(int id){
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getUsersByGroup(List<Group> group) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.group  IN :group AND u.enabled=TRUE",User.class)
                .setParameter("group",group)
                .getResultList();
    }
}
