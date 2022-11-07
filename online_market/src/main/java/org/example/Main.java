package org.example;

import org.example.file.CategoryFile.CategoryToFile;
import org.example.model.Category;
import org.example.model.Product;
import org.example.service.BaseService;
import org.example.service.CategoryService;
import org.example.service.ProductService;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static String headUrl = "MyFile/";
    static ProductService productService = new ProductService();
    static CategoryService categoryService = new CategoryService();

    public static void main(String[] args) throws TelegramApiException {
//        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
//        telegramBotsApi.registerBot(new MyBot());
        while (true) {
            System.out.println(" 1.CATEGORY  2.PRODUCT 0.BACK");
            int var = new Scanner(System.in).nextInt();
            if (var == 0) break;

            switch (var) {
                case 1 -> {
                    forCategory();

                }
                case 2 -> {
                    forProduct();
                }
            }
        }
    }

    private static void forProduct() {

        while (true) {
            System.out.println("1.ADD 2.DELETE 3.UPDATE 4.LIST 0.BACK");
            int var1 = new Scanner(System.in).nextInt();
            if (var1 == 0) break;

            switch (var1) {
                case 1 -> {
                    Product product = new Product();
                    System.out.println(productService.add(product));

                }
                case 2 -> {
                    System.out.println("ENTER ID");
                    System.out.println(productService.delete(new Scanner(System.in).nextInt()));
                }
                case 3 -> {
                    System.out.println("ENTER ID");
                    Product product = productService.getById(new Scanner(System.in).nextInt());
                    System.out.println(productService.update(product));
                }
                case 4 -> {
                    System.out.println(BaseService.products);
                }

            }
        }

    }

    private static void forCategory()  {
        while (true) {
            System.out.println("1.ADD 2.DELETE 3.LIST 0.BACK");
            int var = new Scanner(System.in).nextInt();
            if (var == 0) break;

            switch (var) {
                case 1 -> {
                    Category category = new Category();
                    System.out.println("ENTER NAME");
                    category.setName(new Scanner(System.in).nextLine());
                    System.out.println("ENTER PARENT ID");
                    category.setParentId(new Scanner(System.in).nextInt());
                    try {
                        System.out.println(CategoryToFile.addCategory(category,headUrl));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 2 -> {
                    System.out.println("ENTER ID");
                    System.out.println(categoryService.delete(new Scanner(System.in).nextInt()));
                }
                case 3 -> {
                    System.out.println(BaseService.categories);
                }

            }
        }
    }
}