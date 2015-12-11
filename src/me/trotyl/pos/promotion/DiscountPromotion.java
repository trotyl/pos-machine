package me.trotyl.pos.promotion;


import me.trotyl.pos.model.Discount;
import me.trotyl.pos.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiscountPromotion extends Promotion {

    private HashMap<String, Double> discountMap = new HashMap<>();

    public DiscountPromotion(List<Discount> discounts) {

        for (Discount discount : discounts) {
            discountMap.put(discount.getBarcode(), discount.getDiscount());
        }
    }

    @Override
    public List<Item> apply(List<Item> items) {

        List<Item> results = new ArrayList<>();

        for (Item item : items) {
            if (discountMap.containsKey(item.getBarcode())) {
                Double discount = discountMap.get(item.getBarcode());
                results.add(new Item(item.getBarcode(), item.getPrice() * discount / 100));
            } else {
                results.add(item);
            }
        }

        return results;
    }
}
