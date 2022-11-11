//package org.example.telegramBot;
//
//import org.example.DataBase;
//import org.example.model.Category;
//import org.example.model.Product;
//
//import java.io.*;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//
//import static org.example.DataBase.categoryList;
//import static org.example.DataBase.productList;
//import static org.example.telegramBot.BotConstants.*;
//import static org.example.telegramBot.BotConstants.TELEPHONE_ACCESSORY;
//
//public class CreatProductForTest {
//    public static void addedProductsForTest() throws MalformedURLException {
//        Category notebook = new Category(NOTEBOOK, 0);
//        Category telephone = new Category(TELEPHONE, 0);
//        Category houseAppliances = new Category(HOUSE_APPLIANCES, 0);
//        Category tv = new Category(TV, 0);
//        Category computerAccessory = new Category(COMPUTER_ACCESSORY, 0);
//        Category telephoneAccessory = new Category(TELEPHONE_ACCESSORY, 0);
//        Product notebookP = new Product("Nitro", "Acer", 700, "i5 10300h", 5, 2, 1, new URL("https://api.telegram.org/file/bot5419150140:AAGDeNrHa_xZz8vZ__40z2XPYdBBXKOhT9o/documents/file_19.jpg"));
//        Product telephoneP = new Product("Telephone ", " Samsung", 1000, "S22 ultra", 5, 1, 2, new URL("https://api.telegram.org/file/bot5419150140:AAGDeNrHa_xZz8vZ__40z2XPYdBBXKOhT9o/documents/file_20.jpg"));
//        Product houseApplianceP = new Product("Washing machine", "Samsung", 500, "ASDAC12", 2, 2, 3, new URL("https://api.telegram.org/file/bot5419150140:AAGDeNrHa_xZz8vZ__40z2XPYdBBXKOhT9o/documents/file_24.jpg"));
//        Product tvP = new Product("4K ", "LG", 500, "FAEC12", 2, 2, 4, new URL("https://api.telegram.org/file/bot5419150140:AAGDeNrHa_xZz8vZ__40z2XPYdBBXKOhT9o/documents/file_21.jpg"));
//        Product computerAccessoryP = new Product("Processor", "Intel", 300, "i7 ", 2, 1, 5, new URL("https://api.telegram.org/file/bot5419150140:AAGDeNrHa_xZz8vZ__40z2XPYdBBXKOhT9o/documents/file_18.jpg"));
//        Product telephoneAccessoryP = new Product("Power adapter ", "MI", 15, "35W", 10, 1, 6, new URL("https://api.telegram.org/file/bot5419150140:AAGDeNrHa_xZz8vZ__40z2XPYdBBXKOhT9o/documents/file_17.jpg"));
//        productList.add(notebookP);
//        productList.add(telephoneP);
//        productList.add(tvP);
//        productList.add(houseApplianceP);
//        productList.add(computerAccessoryP);
//        productList.add(telephoneAccessoryP);
//        categoryList.add(notebook);
//        categoryList.add(telephone);
//        categoryList.add(houseAppliances);
//        categoryList.add(tv);
//        categoryList.add(computerAccessory);
//        categoryList.add(telephoneAccessory);
//
//        try {
//            DataBase.writeCategoryToFile(categoryList);
//            DataBase.writeProductToFile(productList);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
//
//}
///*
//private void sendPhoto(String txt, String callBackData, URL url, Long chatId) {
//        InputStream inputStream;
//        List<List<InlineKeyboardButton>> keys = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline = new ArrayList<>();
//
//        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        inlineKeyboardButton.setText(txt);
//        inlineKeyboardButton.setCallbackData(callBackData);
//        rowInline.add(inlineKeyboardButton);
//        keys.add(rowInline);
//        inlineKeyboardMarkup.setKeyboard(keys);
//        try {
//            inputStream = url.openStream();
//            InputFile inputFile = new InputFile(inputStream, String.valueOf(url));
//            SendPhoto sendPhoto = new SendPhoto();
//            sendPhoto.setPhoto(inputFile);
//            sendPhoto.setChatId(chatId.toString());
//            sendPhoto.setReplyMarkup(inlineKeyboardMarkup);
//            execute(sendPhoto);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//    }
// */