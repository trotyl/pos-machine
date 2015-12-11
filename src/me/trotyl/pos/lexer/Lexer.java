package me.trotyl.pos.lexer;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lexer {

    public List<String> parse(InputStream in) {

        List<String> list = new ArrayList<String>();
        Scanner scanner = new Scanner(in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                list.add(line);
            }
        }

        return list;
    }
}
