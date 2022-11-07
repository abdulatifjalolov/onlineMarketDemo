package org.example.service;

import org.example.file.FileUtils;
import org.example.model.Basket;
import org.example.model.Category;
import org.example.model.Product;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public interface BaseService<T, R> {
    R add(T t);

    boolean delete(int id);

    R getById(int id);

    R update(T t);

    List<Basket> baskets = new ArrayList<>();

    static List<Product> getProducts() {
        List<Product> productList;
        try {
            productList = FileUtils.readProductsFromFile();
        } catch (FileNotFoundException e) {
            productList = null;
        }
        return productList;
    }

    static List<Category> getCategories() {
        List<Category> categoryList;
        try {
            categoryList = FileUtils.readCategoriesFromFile();
        } catch (FileNotFoundException e) {
            categoryList = null;
        }
        return categoryList;
    }

}
