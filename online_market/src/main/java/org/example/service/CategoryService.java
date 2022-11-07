package org.example.service;

import org.example.file.FileUtils;
import org.example.model.Category;

import java.io.IOException;
import java.util.List;

public class CategoryService implements BaseService<Category, Category> {
    static String headUrl = "src/main/java/org.example/file/";
    @Override
    public Category add(Category category) {
        for (Category category1 : BaseService.categories) {
            if (category1.getName().equals(category.getName())){
                return null;
            }
        }
        BaseService.categories.add(category);
        try {
            FileUtils.writeCategoryToFile(headUrl,BaseService.categories);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
