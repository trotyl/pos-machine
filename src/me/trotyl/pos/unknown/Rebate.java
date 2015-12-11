package me.trotyl.pos.unknown;


public class Rebate {

    private String barcode;
    private double rebate;

    public Rebate(String barcode, double rebate) {

        this.barcode = barcode;
        this.rebate = rebate;
    }

    public String getBarcode() {
        return barcode;
    }

    public double getRebate() {
        return rebate;
    }
}
