package ua.lemekh.service;

import ua.lemekh.model.Category;

import java.util.List;

/**
 * Created by Ostap on 01.06.2017.
 */
public interface CategoryService {
    List<Category> list();

    Category createCategory(Category category);

    void deleteCategory(int id);

    Category getCategoryById(int id);

    List<String> getCategories();
}
