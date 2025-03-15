package kiao.mlscript.lexer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lexer {

    public static List<Token> parse(String lang, String text) {
        String[] lines = text.trim().split("\n");
        String[] newArray = Arrays.copyOf(lines, lines.length + 1);
        newArray[newArray.length - 1] = "";
        return parse(lang, newArray);
    }

    public static List<Token> parse(String lang, String... bodyText) {
        return Arrays.stream(bodyText)
                .map(String::trim)
                .map(text -> new Token(lang, text))
                .collect(Collectors.toList());
    }

}
