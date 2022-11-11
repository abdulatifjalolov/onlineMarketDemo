package org.example.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.Basket;
import org.example.model.Category;
import org.example.model.Product;
import org.example.telegramBot.BotService;
import org.example.telegramBot.TelegramUser;
import org.telegram.telegrambots.meta.api.objects.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils implements BotService {
    static String headUrl = "C:/Users/Headshoot3464/Desktop/data/";}
//             //USER
//    public static TelegramUser writeUsersToFile(Contact contact) throws IOException {
//        TelegramUser user =new TelegramUser();
//        user.setUserId(contact.getUserId());
//        user.setFirstName(contact.getFirstName());
//        user.setPhoneNumber(contact.getPhoneNumber());
//        File file = new File(headUrl + "telegramUsers/" + user.getUserId());
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        FileWriter fileWriter =new FileWriter(file);
//        fileWriter.write(gson.toJson(user));
//        fileWriter.close();
//        return user;
//    }
//    public static TelegramUser readUserFromFile(Long userId) throws FileNotFoundException {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        File file = new File(headUrl + "telegramUsers");
//        for (File listFile : file.listFiles()) {
//            if (listFile.getName().equals(String.valueOf(userId))) {
//                FileReader fileReader = new FileReader(listFile);
//                BufferedReader bufferedReader =new BufferedReader(fileReader);
//                TelegramUser telegramUser = gson.fromJson(bufferedReader, TelegramUser.class);
//                return telegramUser;
//            }
//        }
//        return null;
//    }
//
//    // ID
//        public static int readIdFromFile() throws IOException {
//        File file = new File(headUrl + "id.txt");
//        BufferedReader reader = new BufferedReader(new FileReader(file));
//        int i = Integer.parseInt(reader.readLine());
//        reader.close();
//        return i;
//    }
//
//    public static void writeIdToFile(int id) throws IOException {
//        File file = new File(headUrl + "id.txt");
//        FileWriter fileWriter = new FileWriter(file);
//        fileWriter.write(String.valueOf(id));
//        fileWriter.close();
//    }
//
//    //  CATEGORY
//        public static void addCategoryList(List<Category> categories) throws IOException {
//        for (Category category : categoryList) {
//            if (category!=null) {
//                writeCategoryToFile(category);
//            }
//        }
//    }
//    public static Category writeCategoryToFile(Category category) throws IOException {
//        File file = new File(headUrl + "categories/" + category.getId());
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        fileOutputStream.write(gson.toJson(category).getBytes());
//        fileOutputStream.close();
//        return category;
//    }
//    public static List<Category> readCategoryListFromFile() throws IOException {
//        List<Category> categories = new ArrayList<>();
//        File file = new File(headUrl + "categories");
//        for (String s : file.list()) {
//            categories.add(readCategoryFromFile(Integer.parseInt(s)));
//        }
//        return categories;
//    }
//    public static Category readCategoryFromFile(int id) throws IOException {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        File file1 = new File(headUrl + "categories");
//        for (File file : file1.listFiles()) {
//            if (file.getName().equals(String.valueOf(id))) {
//                FileReader fileReader = new FileReader(file);
//                return gson.fromJson(fileReader, Category.class);
//            }
//        }
//        return null;
//    }

//    public static boolean deleteCategoryFromList(int id) {
//        File file1 = new File(headUrl + "categories");
//        for (File file : file1.listFiles()) {
//            if (file.getName().equals(String.valueOf(id))) {
//                file.delete();
//                return true;
//            }
//        }
//        return false;
//    }

    // PRODUCT
//    public static List<Product> getProductList() throws IOException {
//        List<Product> products = new ArrayList<>();
//        File file = new File(headUrl + "products");
//        for (String s : file.list()) {
//            products.add(getProductFromList(Integer.parseInt(s)));
//        }
//        return products;
//    }
//    public static Product getProductFromList(int id) throws IOException {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        File file1 = new File(headUrl + "products");
//        for (File file : file1.listFiles()) {
//            if (file.getName().equals(String.valueOf(id))) {
//                FileReader fileReader = new FileReader(file);
//                return gson.fromJson(fileReader, Product.class);
//            }
//        }
//        return null;
//    }
//    public static void addProductsList(List<Product> products) throws IOException {
//        for (Product product : productList) {
//            if (product!=null) {
//                writeProductToFile(product);
//            }
//        }
//    }
//    public static Product writeProductToFile(Product product) throws IOException {
//        File file = new File(headUrl + "products/" + product.getId());
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        fileOutputStream.write(gson.toJson(product).getBytes());
//        fileOutputStream.close();
//        return product;
//    }
//    public static boolean deleteProductFile(int id) {
//        File file1 = new File(headUrl + "products");
//        for (File file : file1.listFiles()) {
//            if (file.getName().equals(String.valueOf(id))) {
//                file.delete();
//                return true;
//            }
//        }
//        return false;
//    }
  // BASKET
//    public static Basket readBasketFromFile(int chatId) throws IOException {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        File file1 = new File(headUrl + "baskets");
//        for (File file : file1.listFiles()) {
//            if (file.getName().equals(String.valueOf(chatId))) {
//                FileReader fileReader = new FileReader(file);
//                return gson.fromJson(fileReader, Basket.class);
//            }
//        }
//        return null;
//    }
//
//    public static Basket writeBasketToFile(Basket basket) throws IOException {
//        File file = new File(headUrl + "baskets/" + basket.getChatId());
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        fileOutputStream.write(gson.toJson(basket).getBytes());
//        fileOutputStream.close();
//        return basket;
//    }
//
//    public static List<Basket> getBasketList() throws IOException {
//        List<Basket> baskets = new ArrayList<>();
//        File file = new File(headUrl + "baskets");
//        for (String s : file.list()) {
//            baskets.add(readBasketFromFile(Integer.parseInt(s)));
//        }
//        return baskets;
//    }
//}
