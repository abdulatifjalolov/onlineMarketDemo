package org.example.service;

import org.example.dto.ProductDto;
import org.example.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService implements BaseService<Product, Product> {

    public List<Product> getProductsOfCategory(int categoryID){
        return BaseService.products.stream()
                .filter(item -> item.getCategoryId()==categoryID)
                .toList();
    }
    @Override
    public Product add(Product product) {
        product=ProductDto.addProduct(product);
        for (Product product2 : products) {
            if (product2.getName().equals(product.getName())) {
                return null;
            }
        }
        products.add(product);
        return product;
    }

    @Override
    public boolean delete(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                products.remove(product);
                return true;
            }
        }

        return false;
    }

    @Override
    public Product getById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Product update(Product product) {
        for (Product product2 : products) {
            if (product2.getName().equals(product.getName())) {
                product = ProductDto.update(product2);
                return product;
            }
        }
        return null;
    }
}
