package org.example.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.Category;
import org.example.model.Product;
import org.example.service.BaseService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    static String headUrl = "src/main/java/org.example/file/";

    public static void writeProductToFile(String headUrl, List<Product> productList) throws IOException {
        List<Product> products = BaseService.getProducts();
        for (Product product : products) {
            File file = new File(headUrl + "products/" + product.getId() + ".txt");
            file.createNewFile();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(gson.toJson(product).getBytes());
            fileOutputStream.close();
        }
    }

    public static void writeCategoryToFile(String headUrl, List<Category> categoryList) throws IOException {
        for (Category category : categoryList) {
            File file = new File(headUrl + "categories/" + category.getId() + ".txt");
            file.createNewFile();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(gson.toJson(category).getBytes());
            fileOutputStream.close();
        }
    }

    public static List<Product> readProductsFromFile() throws FileNotFoundException {
        List<Product> productList = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(headUrl + "products");
        if (file.listFiles().length>0) {
            for (File file1 : file.listFiles()) {
                if (file1 != null) {
                    productList.add(gson.fromJson(new FileReader(file1), Product.class));
                }
            }
        }
        return productList;
    }
    public static List<Category> readCategoriesFromFile() throws FileNotFoundException {
        List<Category> categories=new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(headUrl + "categories");
        if (file.listFiles().length>0){
            for (File file1 : file.listFiles()) {
                if (file1 != null) {
                    categories.add(gson.fromJson(new FileReader(file1), Category.class));
                }
            }
        }
        return categories;
    }
}
