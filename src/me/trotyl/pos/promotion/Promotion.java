package me.trotyl.pos.promotion;


import me.trotyl.pos.model.CartItem;
import me.trotyl.pos.unknown.Rebate;
import org.javatuples.Pair;

import java.util.List;

public abstract class Promotion {

    public abstract Pair<List<CartItem>, List<Rebate>> apply(List<CartItem> items);
}
