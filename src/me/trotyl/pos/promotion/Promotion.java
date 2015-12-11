package me.trotyl.pos.promotion;


import me.trotyl.pos.model.Item;

import java.util.List;

public abstract class Promotion {

    public abstract List<Item> apply(List<Item> items);
}
