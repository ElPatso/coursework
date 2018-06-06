package ua.lemekh.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.lemekh.model.Role;
import ua.lemekh.model.RoleEnum;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Ostap on 13.06.2017.
 */
@Repository
public class RoleDaoImpl implements RoleDao{
    @PersistenceContext
    private EntityManager entityManager;

    public Role getOne(int id) {
        Role role = entityManager.find(Role.class, new Integer(id));
        return role;
    }

    @Override
    @Transactional
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    @Transactional
    public Role getRole(RoleEnum roleEnum) {
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.name =:role", Role.class)
                .setParameter("role",roleEnum)
                .getSingleResult();
    }


}
