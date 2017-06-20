package ua.lemekh.dao;

import ua.lemekh.model.User;
import ua.lemekh.model.VerificationToken;

/**
 * Created by Ostap on 15.06.2017.
 */
public interface VerificationDao {
    void createVerificationToken(VerificationToken verificationToken);

    VerificationToken getVerificationToken(String verificationToken);

}
