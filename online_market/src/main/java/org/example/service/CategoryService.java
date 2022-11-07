package org.example.service;

import org.example.file.FileUtils;
import org.example.model.Category;

import java.io.IOException;
import java.util.List;

public class CategoryService implements BaseService<Category, Category> {
    static String headUrl = "C:/Users/abdulatif/forJAVA/online_market/";

    @Override
    public Category add(Category category) {
        try {
            for (Category category1 : FileUtils.getCategoryList(headUrl)) {
                if (category1.getName().equals(category.getName())) {
                    return null;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            FileUtils.writeCategoryToFile(category, headUrl);
            return category;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        BaseService.categories.add(category);
    }

    @Override
    public boolean delete(int id) {
        try {
            for (Category category : FileUtils.getCategoryList(headUrl)) {
                if (category.getId() == id) {
                    //                BaseService.categories.remove(category);
                    System.out.println(FileUtils.deleteCategoryFile(id, headUrl));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public Category getById(int id) {
        try {
            for (Category category : FileUtils.getCategoryList(headUrl)) {
                if (category.getId() == id) {
                    return category;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Category> getInnerCategories(int parentId) throws IOException {
        List<Category> list = FileUtils.getCategoryList(headUrl).stream().filter(item -> item.getParentId() == parentId).toList();
        return list.size() != 0 ? list : null;
    }

    @Override
    public Category update(Category category) {
        return null;
    }
}
