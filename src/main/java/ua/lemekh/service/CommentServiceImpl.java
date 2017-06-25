package ua.lemekh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lemekh.dao.CommentDao;
import ua.lemekh.model.Comments;

/**
 * Created by Ostap on 24.06.2017.
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Transactional
    @Override
    public Comments addComment(Comments comments) {
        return this.commentDao.addComment(comments);
    }
}
