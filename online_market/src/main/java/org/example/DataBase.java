package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.model.Basket;
import org.example.model.Category;
import org.example.model.Product;
import org.example.telegramBot.TelegramUser;
import org.telegram.telegrambots.meta.api.objects.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    public static List<TelegramUser> telegramUsers = new ArrayList<>();
    public static List<Product> productList = new ArrayList<>();
    public static List<Category> categoryList = new ArrayList<>();
    public  static  List<Basket> basketList =new ArrayList<>();
    public static String headUrl = "C:/Users/Headshoot3464/Desktop/data/";
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static BufferedWriter bufferedWriter = null;

    public static Integer idGeneration = 1;

    public static void readAll() throws IOException {


        //CATEGORY
        File file2 = new File(headUrl + "categories/category.txt");
        file2.createNewFile();
        FileReader categoryRead = new FileReader(file2);
        categoryList = gson.fromJson(categoryRead, new TypeToken<List<Category>>() {
        }.getType());
        categoryRead.close();
        if (categoryList == null) {
            categoryList = new ArrayList<>();
        }
        //PRODUCT
        File file = new File(headUrl + "products/product.txt");
        file.createNewFile();
        FileReader productRead = new FileReader(file);
        productList = gson.fromJson(productRead, new TypeToken<List<Product>>() {
        }.getType());
        productRead.close();
        if (productList == null) {
            productList = new ArrayList<>();
        }

        //ID
        File file1 = new File(headUrl + "id.txt");
        file1.createNewFile();
        FileReader idFileReader = new FileReader(file1);
        idGeneration = gson.fromJson(idFileReader, new TypeToken<Integer>() {
        }.getType());
        idFileReader.close();
        if (idGeneration == null) {
            idGeneration = 0;
        }

        // BASKET
        File file3=new File(headUrl + "baskets/basket.txt");
        file3.createNewFile();
        FileReader basketRead=new FileReader(file3);
        basketList =gson.fromJson(basketRead,new TypeToken<List<Basket>>(){}.getType());
        basketRead.close();
        if (basketList ==null){
            basketList =new ArrayList<>();
        }

    }


    public static List<TelegramUser> readUser() throws IOException {
        File file1 = new File(headUrl + "telegramUsers/users.txt");
        file1.createNewFile();
        FileReader userRead = new FileReader(file1);
        telegramUsers = gson.fromJson(userRead, new TypeToken<List<TelegramUser>>() {
        }.getType());
        userRead.close();
        if (telegramUsers == null) {
            telegramUsers = new ArrayList<>();
        }
        return telegramUsers;
    }

    public static void writeIdToFile() throws IOException {
        File file = new File(headUrl + "id.txt");
        file.createNewFile();
        String s = gson.toJson(idGeneration);
        bufferedWriter = new BufferedWriter((new FileWriter(file)));
        bufferedWriter.write(s);
        bufferedWriter.close();
    }


    // USER
    public static void writeUsersToFile(List<TelegramUser> telegramUsers) throws IOException {
        File file = new File(headUrl + "telegramUsers/users.txt");
        file.createNewFile();
        String s = gson.toJson(telegramUsers);
        bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(s);
        bufferedWriter.close();
    }


    public static boolean addUsersToList(Contact contact) throws IOException {
        TelegramUser user = new TelegramUser();
        user.setUserId(contact.getUserId());
        user.setFirstName(contact.getFirstName());
        user.setPhoneNumber(contact.getPhoneNumber());
        user.setAdmin(false);
        if (telegramUsers.size() == 0) {
            for (TelegramUser telegramUser : telegramUsers) {
                if (telegramUser.getUserId() != user.getUserId()) {
                    telegramUsers.add(user);
                    return true;
                }
            }
        }
        telegramUsers.add(user);
        return true;
    }

    public static TelegramUser checkUser(Long userId) throws IOException {
        telegramUsers = readUser();
        for (TelegramUser telegramUser : telegramUsers) {
            Long userId1 = telegramUser.getUserId();
            if (userId1.equals(userId)) {
                return telegramUser;
            }
        }
        return null;
    }

    // CATEGORY
    public static void writeCategoryToFile(List<Category> categories) throws IOException {
        File file = new File(headUrl + "categories/category.txt");
        file.createNewFile();
        String s = gson.toJson(categories);
        bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(s);
        bufferedWriter.close();
    }

    public static Category getCategoryFromList(int id) {
        for (Category category : categoryList) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }

    public static boolean deleteCategoryFromList(int id) {
        for (Category category : categoryList) {
            if (category.getId() == id) {
                categoryList.remove(category);
                return true;
            }
        }
        return false;
    }


    //PRODUCT

    public static void writeProductToFile(List<Product> products) throws IOException {
        File file = new File(headUrl + "products/product.txt");
        file.createNewFile();
        String s = gson.toJson(products);
        bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(s);
        bufferedWriter.close();
    }

    public static Product getProductFromList(int id) throws IOException {
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public static boolean deleteProductFromList(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                productList.remove(product);
                return true;
            }
        }
        return false;
    }
    // BASKET

    public static void writeBasketListToFile(List<Basket> basket) throws IOException {
        File file3=new File(headUrl + "baskets/basket.txt");
        file3.createNewFile();
        String s = gson.toJson(basket);
        bufferedWriter=new BufferedWriter(new FileWriter(file3));
        bufferedWriter.write(s);
        bufferedWriter.close();
    }
    public static Basket addBasketToList(Basket basket){
        for (Basket basket1 : basketList) {
            if (basket.getChatId().equals(basket1.getChatId())){
                return basket;
            }
        }
        basketList.add(basket);
        return basket;
    }
}
