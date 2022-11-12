package org.example.telegramBot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.DataBase.readUser;
import static org.example.DataBase.telegramUsers;

public class TelegramBotAdmin {
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
}
