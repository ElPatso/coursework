package ua.lemekh.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.lemekh.model.User;
import ua.lemekh.model.VerificationToken;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by Ostap on 15.06.2017.
 */
@Repository
public class VerificationDaoImpl implements VerificationDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void createVerificationToken(VerificationToken verificationToken) {
        entityManager.persist(verificationToken);
    }

    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
        Query query = entityManager.createQuery("select v from VerificationToken  v where v.token = ?1").setParameter(1, verificationToken);
        VerificationToken verification = null;
        try{
            verification = (VerificationToken) query.getSingleResult();
        }catch (NoResultException e){}
        return verification;
    }
}
