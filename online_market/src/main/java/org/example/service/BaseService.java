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

    List<Product> products=new ArrayList<>();
    List<Category> categories=new ArrayList<>();

}
