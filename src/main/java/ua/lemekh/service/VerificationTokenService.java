package ua.lemekh.service;

import ua.lemekh.model.User;
import ua.lemekh.model.VerificationToken;

/**
 * Created by Ostap on 15.06.2017.
 */
public interface VerificationTokenService {
    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String verificationToken);

}
