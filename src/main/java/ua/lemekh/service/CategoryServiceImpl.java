package ua.lemekh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lemekh.dao.CategoryDao;
import ua.lemekh.model.Category;

import java.util.List;

/**
 * Created by Ostap on 01.06.2017.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Transactional
    public List<Category> list() {
        return categoryDao.list();
    }
    @Transactional
    public Category createCategory(Category category) {
        return this.categoryDao.createCategory(category);
    }
}
