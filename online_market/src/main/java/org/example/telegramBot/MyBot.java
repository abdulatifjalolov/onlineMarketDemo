package org.example.telegramBot;

import org.example.DataBase;
import org.example.model.Basket;
import org.example.model.Product;

import org.example.service.BasketService;
import org.example.service.ProductService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.example.DataBase.*;

public class MyBot extends TelegramLongPollingBot implements BotConstants {
    InlineKeyboardServise inlineKeyboardServise = new InlineKeyboardServise();
    BasketService basketService = new BasketService();
    ProductService productService = new ProductService();

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            try {
                forHasMessage(update);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            System.out.println(data);
            if (data.length() >= 7 && data.charAt(0) == '/') {
                String basket = data.substring(0, 7);
                if (basket.equals("/basket")) {
                    int productId = Integer.parseInt(data.substring(7));
                    try {
                        addProductToBasket(productId, chatId);
                        DataBase.writeBasketListToFile(basketList);
                        sendMessage(null, null, "PRODUCT ADDED BASKET ", chatId);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            } else if (data.charAt(0) == 'C' && data.charAt(1) == 'L') {
                //  sendMessage(null, inlineKeyboardMarkup, "PRODUCTS LIST", chatId);
                InlineKeyboardMarkup inlineKeyboardMarkup = categoryOrProduct(update.getCallbackQuery(), chatId);
                editMethod(chatId, update.getCallbackQuery().getMessage().getMessageId(), inlineKeyboardMarkup);
            } else if (data.equals("/back")) {
                // sendMessage(null, inlineKeyboardMarkup, "ADD BASKET OR BACK  ", chatId);
                InlineKeyboardMarkup categoryInlineKeyboardMarkup = inlineKeyboardServise.getCategoryInlineKeyboardMarkup(categoryList, 2);
                editMethod(chatId, update.getCallbackQuery().getMessage().getMessageId(), categoryInlineKeyboardMarkup);
            } else if (data.charAt(0) == 'd' && data.charAt(1) == 'e') {
                String substring = data.substring(6);
                System.out.println(substring);
                Basket currentUserBasket = basketService.getById(chatId);
                int i = Integer.parseInt(substring);
                List<Integer> productIdList = currentUserBasket.getProductIdList();
                productIdList.remove((Object) i);
                currentUserBasket.setProductIdList(productIdList);
                try {
                    DataBase.writeBasketListToFile(basketList);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                sendMessage(null, null, "PRODUCT DELETED FROM BASKET ", chatId);
            } else if (data.charAt(0) == 'b' && data.charAt(1) == 'u') {
                ReplyKeyboardMarkup replyKeyboardMarkup = takeLocation();
                sendMessage(replyKeyboardMarkup, null, "IF YOU WANT TO BUY PRODUCT SHARE LOCATION", chatId);
            } else {
                // sendMessage(null, inlineKeyboardMarkup, "ADD BASKET OR BACK  ", chatId);
                InlineKeyboardMarkup inlineKeyboardMarkup = categoryOrProduct(update.getCallbackQuery(), chatId);
                editMethod(chatId, update.getCallbackQuery().getMessage().getMessageId(), inlineKeyboardMarkup);
            }

        }
    }

    private InlineKeyboardMarkup categoryOrProduct(CallbackQuery callbackQuery, Long chatId) {
        String data = callbackQuery.getData();

        try {

            if (data.charAt(0) == 'C' && data.charAt(1) == 'L') {
                int k = Integer.parseInt(data.substring(2));
                return inlineKeyboardServise.getProductInlineKeyboardMarkup(productList, k, 1);
            } else if (data.charAt(0) == 'P') {
                int j = Integer.parseInt(data.substring(1));
                Product product = getProductFromList(j);
                productInfo(product, chatId);
            } else if (data.charAt(0) == 'C') {
                int i = Integer.parseInt(data.substring(1));
                return inlineKeyboardServise.getProductInlineKeyboardMarkup(productList, i, 1);
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

    private void productInfo(Product product, Long chatId) throws TelegramApiException {
        //EditMessageReplyMarkup edit=new EditMessageReplyMarkup();

        SendPhoto productPhotoWithContent = new SendPhoto();
        productPhotoWithContent.setPhoto(new InputFile(new File(product.getUri())));
        productPhotoWithContent.setChatId(chatId);
        productPhotoWithContent.setCaption("BRAND : " + product.getBrand() + " " + "\nNAME : " + product.getName()
                + "\nMODEL : " + product.getModel() + "\nPRICE: " + product.getPrice() + " $ " + "\n DISCOUNT " + product.getDiscount() + " %");
        InlineKeyboardMarkup in = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        in.setKeyboard(rows);

        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton basket = new InlineKeyboardButton(ADD_BASKET);
        InlineKeyboardButton back = new InlineKeyboardButton(BACK);
        basket.setCallbackData("/basket" + product.getId());
        back.setCallbackData("CL" + product.getCategoryId());
        row.add(back);
        row.add(basket);
        rows.add(row);
        productPhotoWithContent.setReplyMarkup(in);
        execute(productPhotoWithContent);
    }

    private void productInfoForBasket(Product product, Long chatId) throws TelegramApiException {
        SendPhoto productPhotoWithContent = new SendPhoto();
        productPhotoWithContent.setPhoto(new InputFile(new File(product.getUri())));
        productPhotoWithContent.setChatId(chatId);
        productPhotoWithContent.setCaption("BRAND : " + product.getBrand() + " " + "\nNAME : " + product.getName()
                + "\nMODEL : " + product.getModel() + "\nPRICE: " + product.getPrice() + " $ " + "\n DISCOUNT " + product.getDiscount() + " %");
        InlineKeyboardMarkup in = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        in.setKeyboard(rows);

        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton basket = new InlineKeyboardButton(DELETE_FROM_BASKET);
        InlineKeyboardButton back = new InlineKeyboardButton(BUY);
        basket.setCallbackData("delete" + product.getId());
        back.setCallbackData("buy" + product.getId());
        row.add(back);
        row.add(basket);
        rows.add(row);
        productPhotoWithContent.setReplyMarkup(in);
        execute(productPhotoWithContent);
    }

    private void forHasMessage(Update update) throws IOException {
        Message message = update.getMessage();
        System.out.println(message.getFrom());
        Long chatId = message.getChatId();
        if (message.hasText()) {
            String text = message.getText();
            if (text.equals(START)) {
                forStart(message, chatId);
            }
            if (text.equals(MAIN_MENU)) {
                sendMessage(addReplyKeyboardMarkup(List.of(ALL_CATEGORIES, BASKET, MAIN_MENU)), null, "YOU ARE BACK MAIN MENU", chatId);
            } else if (text.equals(ALL_CATEGORIES)) {
                InlineKeyboardMarkup categoryInlineKeyboardMarkup = inlineKeyboardServise.getCategoryInlineKeyboardMarkup(categoryList, 2);
                sendMessage(null, categoryInlineKeyboardMarkup, "CHOOSE CATEGORY", chatId);
            } else if (text.equals(BASKET)) {
                for (Basket basket : basketList) {
                    if (basket.getChatId().equals(chatId)) {
                        for (Integer integer : basket.getProductIdList()) {
                            try {
                                Product product = productService.getById(integer);
                                productInfoForBasket(product, chatId);
                                System.out.println(productService.getById(integer));
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }
                }
            }
        } else if (message.hasContact()) {
            forCheckContact(message);
        } else if (message.hasLocation()) {
            addUserLocation(message);
        }
    }

    private ReplyKeyboardMarkup takeLocation() {
        KeyboardButton k = new KeyboardButton(SHARE_LOCATION);
        k.setRequestLocation(true);
        ReplyKeyboardMarkup r = new ReplyKeyboardMarkup(
                List.of(
                        new KeyboardRow(
                                List.of(
                                        k
                                )
                        )
                )
        );
        r.setResizeKeyboard(true);
        r.setSelective(true);
        r.setOneTimeKeyboard(true);
        return r;
    }

    private void addUserLocation(Message message) throws IOException {
        Long userId=  message.getFrom().getId();
        if (checkUserLocation(userId)) {
            Double longitude = message.getLocation().getLongitude();
            Double latitude = message.getLocation().getLatitude();

            for (TelegramUser telegramUser : telegramUsers) {
                if (telegramUser.getUserId().equals(userId)) {
                    telegramUser.setLongitude(longitude);
                    telegramUser.setLatitude(latitude);
                    DataBase.writeUsersToFile(telegramUsers);
                }
            }
        }
            sendMessage(addReplyKeyboardMarkup(List.of(ALL_CATEGORIES, BASKET, MAIN_MENU)), null, "OUR ADMINS CALL YOU", userId);

    }

    private void forCheckContact(Message message) {
        Contact contact = message.getContact();
        try {
            DataBase.addUsersToList(contact);
            DataBase.writeUsersToFile(telegramUsers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sendMessage(addReplyKeyboardMarkup(List.of(ALL_CATEGORIES, BASKET, MAIN_MENU)), null, "WELCOME TO OUR BOT " + contact.getFirstName().toUpperCase(), contact.getUserId());
    }


    private void forStart(Message message, Long chatId) throws IOException {
        if (DataBase.checkUser(chatId) == null) {
            KeyboardButton k = new KeyboardButton(SHARE_CONTACT);
            k.setRequestContact(true);
            ReplyKeyboardMarkup r = new ReplyKeyboardMarkup(
                    List.of(
                            new KeyboardRow(
                                    List.of(
                                            k
                                    )
                            )
                    )
            );
            r.setResizeKeyboard(true);
            r.setSelective(true);
            r.setOneTimeKeyboard(true);
            sendMessage(r, null, "PLEASE SHARE YOUR CONTACT", chatId);

        } else {
            sendMessage(addReplyKeyboardMarkup(List.of(ALL_CATEGORIES, BASKET, MAIN_MENU)), null, "WELCOME TO OUR BOT " + message.getChat().getFirstName(), chatId);
        }
    }


    private ReplyKeyboardMarkup addReplyKeyboardMarkup(List<String> mainMenu) {

        ReplyKeyboardMarkup r = new ReplyKeyboardMarkup();
        r.setResizeKeyboard(true);
        r.setOneTimeKeyboard(true);
        r.setSelective(true);
        List<KeyboardRow> buttonsRow = new ArrayList<>();
        KeyboardRow keyboardButtons = new KeyboardRow();
        for (int i = 0; i < mainMenu.size(); i++) {
            keyboardButtons.add(new KeyboardButton(mainMenu.get(i)));
            if (i % COUNT_BUTTONS == 0) {
                buttonsRow.add(keyboardButtons);
                keyboardButtons = new KeyboardRow();
            }
        }
        if (keyboardButtons.size() > 0) {
            buttonsRow.add(keyboardButtons);
        }
        r.setKeyboard(buttonsRow);
        return r;
    }


    private void sendMessage(ReplyKeyboardMarkup r, InlineKeyboardMarkup i, String text, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseMode.MARKDOWNV2);
        sendMessage.setText(text);
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(r);
        if (r == null) {
            sendMessage.setReplyMarkup(i);
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void editMethod(Long chatId, Integer messagId, InlineKeyboardMarkup markup) {
        EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
        editMessageReplyMarkup.setChatId(chatId);
        editMessageReplyMarkup.setMessageId(messagId);
        editMessageReplyMarkup.setReplyMarkup(markup);
        try {
            execute(editMessageReplyMarkup);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void addProductToBasket(int productId, Long chatId) throws IOException {
        Basket basket = basketService.getById(chatId);
        if (basket != null) {
            for (Integer integer : basket.getProductIdList()) {
                if (integer == productId) {
                    return;
                }
            }
            basket.getProductIdList().add(productId);
            return;
        }
        Basket basket1 = new Basket();
        basket1.setChatId(chatId);
        List<Integer> productIdList = basket1.getProductIdList();
        productIdList.add(productId);
        basketList.add(basket1);
        DataBase.writeBasketListToFile(basketList);
    }
}