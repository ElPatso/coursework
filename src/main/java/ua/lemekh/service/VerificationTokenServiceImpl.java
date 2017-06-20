package ua.lemekh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lemekh.dao.VerificationDao;
import ua.lemekh.model.User;
import ua.lemekh.model.VerificationToken;

/**
 * Created by Ostap on 15.06.2017.
 */
@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {
    @Autowired
    VerificationDao verificationDao;
    @Transactional
    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        verificationDao.createVerificationToken(myToken);
    }
    @Transactional
    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
        return this.verificationDao.getVerificationToken(verificationToken);
    }
}
