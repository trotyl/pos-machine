package me.trotyl.pos.parser;

import me.trotyl.pos.unknown.Entry;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;


public class EntryParser extends Parser<Entry> {

    private static final Pattern pattern = compile("^\\w+(-\\d+(\\.\\d+)?)?$");

    @Override
    protected Pattern getPattern() {
        return pattern;
    }

    @Override
    protected Entry parseLine(String line) {

        String[] splitString = line.split("-");
        String barcode = splitString[0];
        int amount = splitString.length > 1? Integer.parseInt(splitString[1]): 1;

        return new Entry(barcode, amount);
    }
}
