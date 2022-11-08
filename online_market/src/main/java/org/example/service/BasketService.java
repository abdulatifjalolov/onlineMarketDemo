package org.example.service;

import org.example.file.FileUtils;
import org.example.model.Basket;

import java.io.IOException;

public class BasketService implements BaseService<Basket, Basket> {
    @Override
    public Basket add(Basket basket) {
        try {
            for (Basket basket1 : FileUtils.getBasketList()) {
                if (basket1.getChatId() == basket.getChatId()) {
                    return null;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        baskets.add(basket);
        try {
            FileUtils.writeBasketToFile(basket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Basket getById(int id) {
        try {
            for (Basket basket1 : FileUtils.getBasketList()) {
                if (basket1.getChatId() == id) {
                    return basket1;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Basket update(Basket basket) {
        return null;
    }
}
