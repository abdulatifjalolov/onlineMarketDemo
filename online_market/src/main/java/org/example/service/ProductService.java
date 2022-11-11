package org.example.service;

import org.example.DataBase;
import org.example.dto.ProductDto;
import org.example.model.Product;

import java.io.IOException;
import java.util.List;

import static org.example.DataBase.productList;

public class ProductService implements BaseService<Product, Product> {

    public List<Product> getProductsOfCategory(int categoryID) {
        return productList.stream()
                .filter(item -> item.getCategoryId() == categoryID)
                .toList();
    }

    @Override
    public Product add(Product product) {
        product = ProductDto.addProduct(product);
        for (Product product2 : productList) {
            if (product2.getName().equals(product.getName())) {
                return null;
            }
        }
        productList.add(product);
        try {
            DataBase.writeProductToFile(productList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return product;

    }

    @Override
    public boolean delete(int id) {
            for (Product product : productList) {
                if (product.getId() == id) {
                    return productList.remove(product);
                }
            }
        return false;
    }

    @Override
    public Product getById(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Product update(Product product) {
            for (Product product2 : productList) {
                if (product2.getName().equals(product.getName())) {
                    product = ProductDto.update(product2);
                     productList.add(product);
                     return product;
                }
            }
        return null;
    }
}
