package me.trotyl.pos;


import me.trotyl.pos.model.Item;
import me.trotyl.pos.promotion.Promotion;
import me.trotyl.pos.unknown.CartItem;
import me.trotyl.pos.unknown.Entry;
import me.trotyl.pos.unknown.Rebate;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PosMachine {

    private final HashMap<String,Item> items;
    private final Promotion promotion;

    public PosMachine(List<Item> items, Promotion promotion) {

        this.items = new HashMap<>();
        for (Item item : items) {
            this.items.put(item.getBarcode(), item);
        }

        this.promotion = promotion;
    }

    public double settle(List<Entry> entries) {

        List<CartItem> cartItems = new ArrayList<>();

        for (Entry entry : entries) {
            if (!items.containsKey(entry.getBarcode())) {
                throw new IllegalArgumentException("The barcode does not exist: " + entry.getBarcode());
            }

            Item item = items.get(entry.getBarcode());
            cartItems.add(new CartItem(item.getBarcode(), item.getPrice(), entry.getAmount()));
        }

        Pair<List<CartItem>, List<Rebate>> pair = promotion.apply(cartItems);

        double sum = pair.getValue0().stream().mapToDouble(item -> item.getPrice() * item.getAmount()).sum();
        double decrement = pair.getValue1().stream().mapToDouble(Rebate::getRebate).sum();

        return sum - decrement;
    }
}
