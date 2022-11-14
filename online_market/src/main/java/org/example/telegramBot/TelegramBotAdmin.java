package org.example.telegramBot;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.DataBase.readUser;
import static org.example.DataBase.telegramUsers;
import static org.example.telegramBot.BotConstants.*;

public class TelegramBotAdmin {
 //   MyBot myBot=new MyBot();
    public ReplyKeyboardMarkup creatReplyKeyboardMarKapForAdmin(List<String> buttons) {
        ReplyKeyboardMarkup r = new ReplyKeyboardMarkup();
        r.setResizeKeyboard(true);
        r.setSelective(true);
        r.setOneTimeKeyboard(true);
        List<KeyboardRow> buttonsRow = new ArrayList<>();
        KeyboardRow keyboardButtons = new KeyboardRow();
        for (int i = 0; i < buttons.size(); i++) {
            keyboardButtons.add(new KeyboardButton(buttons.get(i)));
            if (i % 2 == 0) {
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

    public boolean checkAdmin(Long chatId) throws IOException {
        telegramUsers = readUser();
        for (TelegramUser telegramUser : telegramUsers) {
            Long userId = telegramUser.getUserId();
            if (userId.equals(chatId)){
                if (telegramUser.isAdmin()){
                    return true;
                }
            }
        }
        return false;
    }

//    public void forAdmin(Long chatId) {
//        ReplyKeyboardMarkup adminMainButton = creatReplyKeyboardMarKapForAdmin(List.of(CATEGORY_SITTING, PRODUCT_SITTING));
//        sendMessage(adminMainButton, null, "ADMIN SITTING", chatId);
//    }

//    public void forAdminInnerButton(String text,Long chatId){
//        if (text.equals(CATEGORY_SITTING)) {
//            sendMessage(creatReplyKeyboardMarKapForAdmin(List.of(ADD_CATEGORY, DELETE_CATEGORY, SHOW_CATEGORY_LIST, BACK)), null, CATEGORY_SITTING, chatId);
//        } else if (text.equals(PRODUCT_SITTING)) {
//            sendMessage(creatReplyKeyboardMarKapForAdmin(List.of(ADD_PRODUCT, DELETE_PRODUCT, SHOW_PRODUCT_LIST, UPDATE_PRODUCT, BACK)), null, PRODUCT_SITTING, chatId);
//        } else if (text.equals(BACK)) {
//            forAdmin(chatId);
//        }
//    }


//    private void sendMessage(ReplyKeyboardMarkup r, InlineKeyboardMarkup i, String text, Long chatId) {
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setParseMode(ParseMode.MARKDOWNV2);
//        sendMessage.setText(text);
//        sendMessage.setChatId(chatId);
//        sendMessage.setReplyMarkup(r);
//        if (r == null) {
//            sendMessage.setReplyMarkup(i);
//        }
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
