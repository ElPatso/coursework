package ua.lemekh.dao;

import ua.lemekh.model.Role;
import ua.lemekh.model.RoleEnum;

/**
 * Created by Ostap on 13.06.2017.
 */
public interface RoleDao {
    Role getOne(int id);

    void save(Role role);

    Role getRole(RoleEnum roleEnum);
}
