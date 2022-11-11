package org.example.service;

import org.example.DataBase;
import org.example.model.Category;
import java.io.IOException;
import java.util.List;

import static org.example.DataBase.categoryList;

public class CategoryService implements BaseService<Category, Category> {

    @Override
    public Category add(Category category) {

            for (Category category1 :categoryList) {
                if (category1.getName().equals(category.getName())) {
                    return null;
                }
            }
        categoryList.add(category);
        try {
            DataBase.writeCategoryToFile(categoryList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return category;
    }

    @Override
    public boolean delete(int id) {
        for (Category category : categoryList) {
            if (category.getId() == id) {
                categoryList.remove(category);
                return true;
            }
        }
        return false;
    }

    @Override
    public Category getById(int id) {
        for (Category category : categoryList) {
            if (category!=null){
                if (category.getId()==id){
                    return category;
                }
            }
        }
        return null;
    }

    public List<Category> getInnerCategories(int parentId) throws IOException {
        List<Category> list = categoryList.stream().filter(item -> item.getParentId() == parentId).toList();
        return list.size() != 0 ? list : null;
    }

    @Override
    public Category update(Category category) {
        return null;
    }
}
