package ua.lemekh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.lemekh.dao.RoleDao;
import ua.lemekh.dao.UserDao;
import ua.lemekh.model.*;

import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.ServiceMode;
import java.util.Calendar;
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

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Transactional
    public void save(User user) {
        User currentUser = getCurrentUser();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<Role>();
        if (currentUser.getRoles().stream().anyMatch(role -> role.getName().equals(RoleEnum.ROLE_ADMIN))) {
            roles.add(roleDao.getRole(RoleEnum.ROLE_LECTURER));
        } else {
            roles.add(roleDao.getRole(RoleEnum.ROLE_STUDENT));
        }
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
    @Transactional
    public void updateUserPassword(User user){
        User existingUser = getUserById(user.getId());
        existingUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        updateUser(existingUser);
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
    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Transactional
    @Override
    public List<User> getUsers() {

        return userDao.getUsers();
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        this.userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return this.userDao.getUserById(id);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = "";
        Object object = authentication.getPrincipal();
        if (object instanceof UserDetails) {
            username = ((UserDetails) object).getUsername();
        }
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional
    public List<User> getUsersByGroup(List<Group> group) {
        return userDao.getUsersByGroup(group);
    }

    @Transactional
    @Override
    public String confirmVerification(String token) {
        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
        if (verificationToken == null) {
            return "redirect:/";
        }

        User user = verificationToken.getUser();

        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "redirect:/";
        }
        user.setEnabled(true);
        updateUser(user);
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals(RoleEnum.ROLE_LECTURER))) {
            return "redirect:/registerLecturer/" + user.getId();
        }
        return "redirect:/login";
    }
}
