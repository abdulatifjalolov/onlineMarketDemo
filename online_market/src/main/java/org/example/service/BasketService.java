package org.example.service;

import org.example.model.Basket;

public class BasketService implements BaseService<Basket, Basket> {
    @Override
    public Basket add(Basket basket) {
        for (Basket basket1 : baskets) {
            if (basket1.getChatId() == basket.getChatId()) {
                return null;
            }
        }
        baskets.add(basket);
        return basket;
    }

    @Override
    public boolean delete(int id) {
        for (Basket basket1 : baskets) {
            if (basket1.getChatId() == id) {
                baskets.remove(basket1);
                return true;
            }
        }
        return false;
    }

    @Override
    public Basket getById(int id) {
        for (Basket basket1 : baskets) {
            if (basket1.getChatId() == id) {
                return basket1;
            }
        }
        return null;
    }

    @Override
    public Basket update(Basket basket) {
        return null;
    }
}
