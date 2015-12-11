package me.trotyl.pos.unknown;


public class Entry {

    private final String barcode;
    private final int amount;

    public Entry(String barcode, int amount) {

        this.barcode = barcode;
        this.amount = amount;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getAmount() {
        return amount;
    }
}
