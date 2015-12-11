package me.trotyl.pos.parser;


import me.trotyl.pos.model.Discount;
import me.trotyl.pos.model.Item;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class DiscountParser extends Parser<Discount> {

    private static final Pattern pattern = compile("^\\w+:\\d+(\\.\\d+)?$");

    @Override
    protected Pattern getPattern() {
        return pattern;
    }

    @Override
    protected Discount parseLine(String line) {

        String[] splitString = line.split(":");
        String barcode = splitString[0];
        double discount = Double.parseDouble(splitString[1]);

        return new Discount(barcode, discount);
    }
}
