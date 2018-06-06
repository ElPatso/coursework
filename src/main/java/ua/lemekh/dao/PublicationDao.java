package ua.lemekh.dao;

import ua.lemekh.model.Publication;
import ua.lemekh.model.User;

import java.util.List;

/**
 * Created by Ostap on 16.06.2017.
 */
public interface PublicationDao {
    List<Publication> getPublications(Integer offset, Integer maxResult, User user);

    Long count(User user);

    Publication getPublicationById(int id);

    Publication createPublication(Publication products);

    List<Publication> getPublicationsBySearch(String search);

    List<Publication> getPublicationsByGroup(String category, Integer offset, Integer maxResult);

    Long CountForCategory(String category);

    void updatePublication(Publication products);

    void deletePublication(int id);

}

