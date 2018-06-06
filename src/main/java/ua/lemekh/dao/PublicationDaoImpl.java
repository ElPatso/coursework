package ua.lemekh.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import ua.lemekh.model.Publication;
import ua.lemekh.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

/**
 * Created by Ostap on 16.06.2017.
 */
@Repository
public class PublicationDaoImpl implements PublicationDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Publication> getPublications(Integer offset, Integer maxResult, User user) {
        StringBuilder builder = getPublicationsQuery(user, false);

        Query query = entityManager
                .createQuery(builder.toString())
                .setFirstResult(offset != null ? offset : 0)
                .setMaxResults(maxResult != null ? maxResult : 12);
        if (Objects.nonNull(user)) {
            query.setParameter("user", user)
                    .setParameter("group", user.getGroup());
        }
        List<Publication> products = (List<Publication>) query.getResultList();
        return products;
    }

    @Override
    public Long count(User user) {
        Query query = entityManager.createQuery(getPublicationsQuery(user, true).toString());
        if (Objects.nonNull(user)) {
            query.setParameter("user", user)
                    .setParameter("group", user.getGroup());
        }
        return (Long) query.getSingleResult();
    }

    @Override
    public Publication getPublicationById(int id) {
        Publication product = entityManager.find(Publication.class, id);
        return product;
    }

    @Override
    public Publication createPublication(Publication products) {
        entityManager.persist(products);
        entityManager.flush();
        return products;
    }


    @Override
    public List<Publication> getPublicationsBySearch(String search) {
        Query query = entityManager.createQuery("select p from Products  p where p.name like ?1").setParameter(1, search + "%");
        List<Publication> products = (List<Publication>) query.getResultList();
        return products;
    }

    @Override
    public List<Publication> getPublicationsByGroup(String category, Integer offset, Integer maxResult) {
        return entityManager.createQuery("select p from Products p where p.category like ?1").setParameter(1, category).setFirstResult(offset != null ? offset : 0).setMaxResults(maxResult != null ? maxResult : 12).getResultList();
    }

    @Override
    public Long CountForCategory(String category) {
        return (Long) entityManager.createQuery("select count (p.id)from Products p where p.category like ?1").setParameter(1, category).getSingleResult();
    }

    @Override
    public void updatePublication(Publication products) {
        entityManager.merge(products);
        entityManager.flush();
    }

    @Override
    public void deletePublication(int id) {
        Publication products = entityManager.find(Publication.class, id);
        if (products != null) {
            entityManager.remove(products);
        }
    }


    private StringBuilder getPublicationsQuery(User user, boolean count) {
        StringBuilder builder = new StringBuilder();
        if (count) {
            builder.append("SELECT COUNT(DISTINCT p.id) FROM Publication  p ");
        } else {
            builder.append("SELECT DISTINCT(p) FROM Publication  p ");
        }
        if (Objects.nonNull(user)) {
            builder.append("LEFT JOIN p.groups g JOIN p.publicatedBy u ");
        }
        builder.append("WHERE p.publicPublication=TRUE ");
        if (Objects.nonNull(user)) {
            builder.append("OR g = :group OR u = :user");
        }
        return builder;
    }

}
