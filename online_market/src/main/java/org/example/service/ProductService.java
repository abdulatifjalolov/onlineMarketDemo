package org.example.service;

import org.example.dto.ProductDto;
import org.example.file.FileUtils;
import org.example.model.Product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService implements BaseService<Product, Product> {
    static String headUrl = "C:/Users/abdulatif/forJAVA/online_market/";

    public List<Product> getProductsOfCategory(int categoryID) throws IOException {
        return FileUtils.getProductList(headUrl).stream()
                .filter(item -> item.getCategoryId() == categoryID)
                .toList();
    }

    @Override
    public Product add(Product product) {
        product = ProductDto.addProduct(product);
        try {
            for (Product product2 : FileUtils.getProductList(headUrl)) {
                if (product2.getName().equals(product.getName())) {
                    return null;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        products.add(product);
        try {
            return FileUtils.writeProductToFile(product, headUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            for (Product product : FileUtils.getProductList(headUrl)) {
                if (product.getId() == id) {
                    //                products.remove(product);
                    return FileUtils.deleteProductFile(id, headUrl);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public Product getById(int id) {
        try {
            for (Product product : FileUtils.getProductList(headUrl)) {
                if (product.getId() == id) {
                    return product;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Product update(Product product) {
        try {
            for (Product product2 : FileUtils.getProductList(headUrl)) {
                if (product2.getName().equals(product.getName())) {
                    product = ProductDto.update(product2);
                    return FileUtils.writeProductToFile(product, headUrl);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
