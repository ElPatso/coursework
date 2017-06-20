package ua.lemekh.dao;

import org.springframework.stereotype.Repository;
import ua.lemekh.model.Role;

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
}
