package kiao.mlscript.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LexerUtils {

    public static List<List<Token>> removeDisabledCols(List<List<Token>> original) {
        return transpose(
                transpose(original).stream()
                        .filter(r -> !r.stream().allMatch(Token::isDisabled))
                        .collect(Collectors.toList())
        );
    }

    static List<List<Token>> transpose(List<List<Token>> original) {
        if (original == null || original.isEmpty()) {
            return new ArrayList<>();
        }

        int maxColumns = original.stream()
                .mapToInt(List::size)
                .max()
                .orElse(0);

        List<List<Token>> transposed = new ArrayList<>();
        for (int i = 0; i < maxColumns; i++) {
            transposed.add(new ArrayList<>());
        }

        for (List<Token> row : original) {
            for (int col = 0; col < maxColumns; col++) {
                if (col < row.size()) {
                    transposed.get(col).add(row.get(col));
                } else {
                    throw new IllegalArgumentException();
                }
            }
        }

        return transposed;
    }

}
