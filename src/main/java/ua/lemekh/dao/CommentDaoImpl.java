package ua.lemekh.dao;

import org.springframework.stereotype.Repository;
import ua.lemekh.model.Comments;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Ostap on 24.06.2017.
 */
@Repository
public class CommentDaoImpl implements CommentDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Comments addComment(Comments comments) {
        entityManager.persist(comments);
        entityManager.flush();
        return comments;
    }
}
