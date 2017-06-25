package ua.lemekh.dao;

import ua.lemekh.model.Comments;

/**
 * Created by Ostap on 24.06.2017.
 */
public interface CommentDao {

    Comments addComment(Comments comments);
}
