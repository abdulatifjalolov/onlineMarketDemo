package org.example.service;

import org.example.DataBase;
import org.example.dto.ProductDto;
import org.example.model.Product;

import java.io.IOException;
import java.util.List;

import static org.example.DataBase.productList;
import static org.example.DataBase.writeProductToFile;

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
            writeProductToFile(productList);
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
    public Product addProductForAdmin(String text) throws IOException {
        String uri = null; double newPrice = 0;
        String name=null; int newCount = 0,newDiscount = 0;
        String brand=null; int categoryId = 0;
        String model=null;

        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i) == '/' && text.charAt(i + 1) == 'N') {
                String id = text.substring(0, i);
                categoryId = Integer.parseInt(id);
                text = text.substring(i + 2);
                break;
            }
        }
        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i) == '/' && text.charAt(i + 1) == 'B') {
                name = text.substring(0, i);
                text = text.substring(i + 2);
                break;
            }
        }
        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i) == '/' && text.charAt(i + 1) == 'M') {
                brand = text.substring(0, i);
                text = text.substring(i + 2);
                break;
            }
        }
        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i) == '/' && text.charAt(i + 1) == 'P') {
                model = text.substring(0, i);
                text = text.substring(i + 2);
                break;
            }
        }
        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i) == '/' && text.charAt(i + 1) == 'C') {
                String price = text.substring(0, i);
                newPrice = Double.parseDouble(price);
                text = text.substring(i + 2);
                break;
            }
        }
        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i) == '/' && text.charAt(i + 1) == 'D') {
                String count = text.substring(0, i);
                newCount = Integer.parseInt(count);
                text = text.substring(i + 2);
                break;
            }
        }
        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i) == '/' && text.charAt(i + 1) == 'U') {
                String discount = text.substring(0,i);
                newDiscount = Integer.parseInt(discount);
                uri=text.substring(i+2);
                break;
            }
        }
        Product product = new Product();
        product.setCategoryId(categoryId);
        product.setName(name);
        product.setBrand(brand);
        product.setModel(model);
        product.setPrice(newPrice);
        product.setCount(newCount);
        product.setDiscount(newDiscount);
        product.setUri(uri);
        productList.add(product);

        return product;
      //  writeProductToFile(productList);
    }
}
