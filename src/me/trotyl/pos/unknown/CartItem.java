package me.trotyl.pos.unknown;


import me.trotyl.pos.model.Item;

public class CartItem extends Item {

    private final int amount;

    public CartItem(String barcode, double price, int amount) {

        super(barcode, price);

        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
