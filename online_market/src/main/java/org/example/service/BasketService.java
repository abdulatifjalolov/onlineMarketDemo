package org.example.service;
import org.example.model.Basket;
import static org.example.DataBase.basketList;

public class BasketService implements BaseService<Basket, Basket> {
    @Override
    public Basket add(Basket basket) {
        for (Basket basket1 : basketList) {
            if (basket.getChatId().equals(basket1.getChatId())){
                return basket;
            }
        }
        basketList.add(basket);
        return basket;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Basket getById(int id) {
        return null;
    }
    public Basket getById(Long chatId) {
        for (Basket basket : basketList) {
            if (basket.getChatId().equals(chatId)){
                System.out.println(basket);
                return basket;
            }
        }
        return null;
    }

    @Override
    public Basket update(Basket basket) {
        return null;
    }
}
