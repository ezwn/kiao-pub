package kiao.mlscript.lexer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class TokenTest {

    private static Stream<Arguments> provideParameters() {

        Map<String, String> map = new HashMap<>();
        map.put("key", "val");

        return Stream.of(
                Arguments.of(
                        "Hello world",
                        new Token("EN",
                                false,
                                false,
                                false,
                                0,
                                false,
                                false,
                                false,
                                0,
                                0,
                                false,
                                null,
                                "Hello world",
                                null
                        )
                ), Arguments.of(
                        "~^ Hello world",
                        new Token("EN",
                                true,
                                false,
                                false,
                                0,
                                false,
                                true,
                                false,
                                0,
                                0,
                                false,
                                null,
                                "Hello world",
                                null
                        )
                ), Arguments.of(
                        "~~^ Hello world",
                        new Token("EN",
                                true,
                                false,
                                false,
                                0,
                                false,
                                true,
                                true,
                                0,
                                0,
                                false,
                                null,
                                "Hello world",
                                null
                        )
                ), Arguments.of(
                        "## Hello world",
                        new Token("EN",
                                false,
                                false,
                                false,
                                0,
                                false,
                                false,
                                false,
                                2,
                                0,
                                false,
                                null,
                                "Hello world",
                                null
                        )
                ), Arguments.of(
                        "3. Hello world",
                        new Token("EN",
                                false,
                                false,
                                false,
                                0,
                                false,
                                false,
                                false,
                                0,
                                0,
                                false,
                                3,
                                "Hello world",
                                null
                        )
                ), Arguments.of(
                        "1993年。",
                        new Token("EN",
                                false,
                                false,
                                false,
                                0,
                                false,
                                false,
                                false,
                                0,
                                0,
                                false,
                                null,
                                "1993年。",
                                null
                        )
                ), Arguments.of(
                        " - 1993年。",
                        new Token("EN",
                                false,
                                false,
                                false,
                                0,
                                false,
                                false,
                                false,
                                0,
                                0,
                                true,
                                null,
                                "1993年。",
                                null
                        )
                ), Arguments.of(
                        " - 1993",
                        new Token("EN",
                                false,
                                false,
                                false,
                                0,
                                false,
                                false,
                                false,
                                0,
                                0,
                                true,
                                null,
                                "1993",
                                null
                        )
                ), Arguments.of(
                        " - Hello world",
                        new Token("EN",
                                false,
                                false,
                                false,
                                0,
                                false,
                                false,
                                false,
                                0,
                                0,
                                true,
                                null,
                                "Hello world",
                                null
                        )
                ), Arguments.of(
                        " - Hello **world**",
                        new Token("EN",
                                false,
                                false,
                                false,
                                0,
                                false,
                                false,
                                false,
                                0,
                                0,
                                true,
                                null,
                                "Hello **world**",
                                null
                        )
                ), Arguments.of(
                        "@# Hello **world**",
                        new Token("EN",
                                false,
                                false,
                                false,
                                0,
                                true,
                                false,
                                false,
                                1,
                                0,
                                false,
                                null,
                                "Hello **world**",
                                null
                        )
                ), Arguments.of(
                        "@# Hello **world** // { \"key\": \"val\" }",
                        new Token("EN",
                                false,
                                false,
                                false,
                                0,
                                true,
                                false,
                                false,
                                1,
                                0,
                                false,
                                null,
                                "Hello **world**",
                                map
                        )
                ), Arguments.of(
                        "",
                        new Token("EN",
                                false,
                                false,
                                false,
                                0,
                                false,
                                false,
                                false,
                                0,
                                0,
                                false,
                                null,
                                null,
                                null
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    public void testParametersFromMethod(String line, Token profile) {
        Assertions.assertEquals(profile, new Token("EN",line));
    }

    @Test
    public void testRemoveTrailingPunctuation1() {
        Assertions.assertEquals(
                "你好，世界",
                Token.removeTrailingPunctuation("你好，世界！"));
    }

    @Test
    public void testRemoveTrailingPunctuation2() {
        Assertions.assertEquals(
                "你好，世界",
                Token.removeTrailingPunctuation("你好，世界"));
    }

}
