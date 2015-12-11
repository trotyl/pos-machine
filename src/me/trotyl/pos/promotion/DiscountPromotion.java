package me.trotyl.pos.promotion;


import me.trotyl.pos.unknown.CartItem;
import me.trotyl.pos.model.Discount;
import me.trotyl.pos.unknown.Rebate;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Collections;
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
    public Pair<List<CartItem>, List<Rebate>> apply(List<CartItem> items) {

        List<CartItem> results = new ArrayList<>();

        for (CartItem item : items) {
            if (discountMap.containsKey(item.getBarcode())) {
                Double discount = discountMap.get(item.getBarcode());
                results.add(new CartItem(item.getBarcode(), item.getPrice() * discount / 100, item.getAmount()));
            } else {
                results.add(item);
            }
        }

        return new Pair<>(results, Collections.emptyList());
    }
}
