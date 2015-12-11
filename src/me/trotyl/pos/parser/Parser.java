package me.trotyl.pos.parser;


import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class Parser<T> {

    public List<T> parse(List<String> inputs) {
        return inputs.stream()
                .filter(getPattern().asPredicate())
                .map(this::parseLine)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    protected abstract Pattern getPattern();

    public abstract T parseLine(String line);
}
