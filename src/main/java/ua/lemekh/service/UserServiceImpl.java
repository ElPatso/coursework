package ua.lemekh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.lemekh.dao.RoleDao;
import ua.lemekh.dao.UserDao;
import ua.lemekh.model.Role;
import ua.lemekh.model.User;

import org.springframework.transaction.annotation.Transactional;
import javax.xml.ws.ServiceMode;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Ostap on 13.06.2017.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<Role>();
        roles.add(roleDao.getOne(1));
        user.setRoles(roles);

        userDao.save(user);
    }
    @Transactional
    public User findByUsername(String username) {
        return this.userDao.findByUsername(username);
    }
    @Transactional
    public User findByEmail(String email) {
        return this.userDao.findByEmail(email);
    }

    @Transactional
    public User updateUser(User user){
        return userDao.updateUser(user);
    }

    @Transactional
    @Override
    public List<User> getUsers() {

        return userDao.getUsers();
    }

    @Override
    @Transactional
    public void deleteUser(int id){
        this.userDao.deleteUser(id);
    }

    @Override
    @Transactional
     public User getUserById(int id){
        return this.userDao.getUserById(id);
     }
}
