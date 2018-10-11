package ru.clientserverapps.mirea.serverbackend.domain;

import java.util.ArrayList;
import java.util.List;

public class Tray {
    public static List<Product> shoppingList = new ArrayList<Product>();

    static public void addToCart(Product product)
    {
        if (product.count > 0)
            shoppingList.add(product);
        product.count--;
    }

    // @param int id is a internal shoppingList id
    static public void deleteFromCart(int id)
    {
        if (id < 0 || id > shoppingList.size())
            return;
        Product p = shoppingList.get(id);
        p.count++;
        shoppingList.remove(id);
    }
}
