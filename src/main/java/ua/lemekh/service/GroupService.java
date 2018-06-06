package ua.lemekh.service;

import ua.lemekh.model.Group;

import java.util.List;

/**
 * Created by Ostap on 01.06.2017.
 */
public interface GroupService {
    List<Group> list();

    Group createCategory(Group category);

    void deleteCategory(int id);

    Group getCategoryById(int id);

    List<String> getCategories();
}
