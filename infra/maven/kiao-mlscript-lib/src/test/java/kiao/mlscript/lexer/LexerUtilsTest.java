package kiao.mlscript.lexer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LexerUtilsTest {

    @Test
    void testRemoveDisabled() {
        List<List<Token>> expected = new java.util.ArrayList<>();
        expected.add(Lexer.parse("EN", "A", "C"));
        expected.add(Lexer.parse("EN", "1", "3"));
        List<List<Token>> original = new java.util.ArrayList<>();
        original.add(Lexer.parse("EN", "A", "~~", "C"));
        original.add(Lexer.parse("EN", "1", "~~", "3"));
        Assertions.assertEquals(expected, LexerUtils.removeDisabledCols(original));
    }

    @Test
    void testOk() {
        List<List<Token>> expected = new java.util.ArrayList<>();
        expected.add(Lexer.parse("EN", "A", "1"));
        expected.add(Lexer.parse("EN", "B", "2"));
        expected.add(Lexer.parse("EN", "C", "3"));
        List<List<Token>> original = new java.util.ArrayList<>();
        original.add(Lexer.parse("EN", "A", "B", "C"));
        original.add(Lexer.parse("EN", "1", "2", "3"));
        Assertions.assertEquals(expected, LexerUtils.transpose(original));
    }

    @Test
    void testNok() {
        Assertions.assertThrows(Exception.class, () -> {
            List<List<Token>> original = new java.util.ArrayList<>();
            original.add(Lexer.parse("EN", "A", "B", "C"));
            original.add(Lexer.parse("EN", "1", "2"));
            LexerUtils.transpose(original);
        });
    }

}
