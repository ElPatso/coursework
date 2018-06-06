package ua.lemekh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lemekh.dao.GroupDao;
import ua.lemekh.model.Group;

import java.util.List;

/**
 * Created by Ostap on 01.06.2017.
 */
@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupDao categoryDao;

    @Transactional
    public List<Group> list() {
        return categoryDao.list();
    }
    @Transactional
    public Group createCategory(Group category) {
        return this.categoryDao.createCategory(category);
    }

    @Transactional
    @Override
    public void deleteCategory(int id) {
        this.categoryDao.deleteCategory(id);
    }

    @Transactional
    @Override
    public Group getCategoryById(int id) {
        return this.categoryDao.getCategoryById(id);
    }

    @Transactional
    @Override
    public List<String> getCategories() {
        return categoryDao.getCategories();
    }
}
