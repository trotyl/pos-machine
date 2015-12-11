package me.trotyl.pos.promotion;


import me.trotyl.pos.unknown.CartItem;
import me.trotyl.pos.unknown.Rebate;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ComsitePromotion extends Promotion {

    private List<Promotion> promotions;

    public ComsitePromotion(Promotion... promotions) {

        this.promotions = Arrays.stream(promotions).collect(Collectors.toList());
    }

    @Override
    public Pair<List<CartItem>, List<Rebate>> apply(List<CartItem> items) {

        List<CartItem> appliedItems = items;
        List<Rebate> rebates = new ArrayList<>();

        for (Promotion promotion : promotions) {
            Pair<List<CartItem>, List<Rebate>> pair = promotion.apply(appliedItems);
            appliedItems = pair.getValue0();
            rebates.addAll(pair.getValue1());
        }

        return new Pair<>(appliedItems, rebates);
    }
}
