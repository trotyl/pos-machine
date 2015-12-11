package me.trotyl.pos.promotion;

import me.trotyl.pos.model.CartItem;
import me.trotyl.pos.unknown.Rebate;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class SecondHalfPricePromotion extends Promotion {

    private HashSet<String> discountSet = new HashSet<>();

    public SecondHalfPricePromotion(List<String> barcodes) {

        for (String barcode : barcodes) {
            discountSet.add(barcode);
        }
    }

    @Override
    public Pair<List<CartItem>, List<Rebate>> apply(List<CartItem> items) {

        List<Rebate> rebates = new ArrayList<>();

        for (CartItem item : items) {
            if (discountSet.contains(item.getBarcode()) && item.getAmount() >= 2) {
                Rebate rebate = new Rebate(item.getBarcode(), item.getPrice() * (item.getAmount() / 2) / 2);
                rebates.add(rebate);
            }
        }

        return new Pair<>(items, rebates);
    }
}
