package ua.lemekh.service;

import ua.lemekh.model.User;

/**
 * Created by Ostap on 13.06.2017.
 */
public interface UserService {
    void save(User user);

    User findByUsername(String username);

    User findByEmail(String email);

    void updateUser(User user);

}
