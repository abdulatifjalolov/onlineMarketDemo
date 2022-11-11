package org.example.telegramBot;

import org.example.model.Category;
import org.example.model.Product;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.ArrayList;
import java.util.List;
public class InlineKeyboardServise {
    public InlineKeyboardMarkup getCategoryInlineKeyboardMarkup(List<Category> categoryList, int n) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(rows);

        List<InlineKeyboardButton> row = new ArrayList<>();
        for (int i = 0; i < categoryList.size(); i++) {
            Category category = categoryList.get(i);
            InlineKeyboardButton inlineKeyboardButton = null;
            inlineKeyboardButton = new InlineKeyboardButton(category.getName());
            inlineKeyboardButton.setCallbackData("C" + category.getId());
            row.add(inlineKeyboardButton);
            if (i % n == 0) {
                rows.add(row);
                row = new ArrayList<>();
            }
        }
        if (row.size() > 0) {
            rows.add(row);
        }

        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getProductInlineKeyboardMarkup(List<Product> productList, int categoryId, int n) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(rows);

        List<InlineKeyboardButton> row = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            if (product.getCategoryId()==categoryId) {
                InlineKeyboardButton inlineKeyboardButton = null;
                inlineKeyboardButton = new InlineKeyboardButton(product.getBrand()+" "+product.getName());
                inlineKeyboardButton.setCallbackData("P" + product.getId());
                row.add(inlineKeyboardButton);
                if (i % n == 0) {
                    rows.add(row);
                    row = new ArrayList<>();
                }
            }
            if (row.size() > 0) {
                rows.add(row);
            }
        }

        return inlineKeyboardMarkup;
    }

}
