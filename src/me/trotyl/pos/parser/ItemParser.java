package me.trotyl.pos.parser;


import me.trotyl.pos.model.Item;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.regex.Pattern.compile;

public class ItemParser {

    private static final Pattern pattern = compile("^\\w+:\\d+(\\.\\d+)?$");

    public List<Item> parse(List<String> inputs) {
        return inputs.stream()
                .filter(pattern.asPredicate())
                .map(this::parseLine)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Item parseLine(String line) {

        String[] splitString = line.split(":");
        String barcode = splitString[0];
        double price = Double.parseDouble(splitString[1]);

        return new Item(barcode, price);
    }
}
