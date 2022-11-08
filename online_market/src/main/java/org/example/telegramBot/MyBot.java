package org.example.telegramBot;

import org.example.file.FileUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyBot extends TelegramLongPollingBot implements BotConstants {

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
            forHasMessage(update);
        } else if (update.hasCallbackQuery()) {

        }
    }

    private void forHasMessage(Update update) {
        Message message = update.getMessage();
        System.out.println(message.getFrom());
        Long chatId = message.getChatId();
        if (message.hasText()) {
            String text = message.getText();
            if (text.equals(START)) {
                forStart(message, chatId);
            }
            if (text.equals(MAIN_MENU)) {
                sendMessage(addReplyKeyboardMarkup(List.of(ALL_CATEGORIES, BASKET, MAIN_MENU)), null,WELCOME, chatId);
            } else if (text.equals(BASKET)) {
                /////
            }
        } else if (message.hasContact()) {
            forCheckContact(message);
        }
    }

    private void forCheckContact(Message message) {
        Contact contact = message.getContact();
        try {
            FileUtils.writeUsersToFile(contact);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sendMessage(addReplyKeyboardMarkup(List.of(ALL_CATEGORIES, BASKET, MAIN_MENU)), null, "WELCOME TO OUR BOT " + contact.getFirstName().toUpperCase(), contact.getUserId());
    }

    private void forStart(Message message, Long chatId) {
        try {
            if (FileUtils.readUserFromFile(chatId)==null) {
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

            }else {
                sendMessage(addReplyKeyboardMarkup(List.of(ALL_CATEGORIES, BASKET, MAIN_MENU)), null, "WELCOME TO OUR BOT " + message.getChat().getFirstName(), chatId);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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

}