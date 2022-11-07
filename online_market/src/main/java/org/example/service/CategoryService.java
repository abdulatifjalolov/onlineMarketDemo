package org.example.service;

import org.example.model.Category;

import java.util.List;

public class CategoryService implements BaseService<Category, Category> {
    @Override
    public Category add(Category category) {
        for (Category category1 : BaseService.categories) {
            if (category1.getName().equals(category.getName())){
                return null;
            }
        }
        BaseService.categories.add(category);
        return category;
    }

    @Override
    public boolean delete(int id) {
        for (Category category : BaseService.categories) {
            if (category.getId() == id){
                BaseService.categories.remove(category);
                return true;
            }
        }
        return false;
    }

    @Override
    public Category getById(int id) {
        for (Category category : BaseService.categories) {
            if (category.getId() == id){
                return category;
            }
        }
        return null;
    }

    public List<Category> getInnerCategories(int parentId) {
        List<Category> list = BaseService.categories.stream().filter(item -> item.getParentId() == parentId).toList();
        return list.size() != 0 ? list : null;
    }

    @Override
    public Category update(Category category) {
        return null;
    }
}
