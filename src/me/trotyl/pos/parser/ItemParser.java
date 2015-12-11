package me.trotyl.pos.parser;


import me.trotyl.pos.model.Item;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class ItemParser extends Parser<Item> {

    private static final Pattern pattern = compile("^\\w+:\\d+(\\.\\d+)?$");

    @Override
    protected Pattern getPattern() {
        return pattern;
    }

    @Override
    public Item parseLine(String line) {

        String[] splitString = line.split(":");
        String barcode = splitString[0];
        double price = Double.parseDouble(splitString[1]);

        return new Item(barcode, price);
    }
}
