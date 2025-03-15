package kiao.mlscript.tools;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Mocks {

    @Getter
    @RequiredArgsConstructor
    public static class TextMock {
        private final String language;
        private final String body;
        private final String ssml;
    }

    public static TextMock mathText = new TextMock(
            "FR",
            "$$n$$ un entier supérieur ou égal à 2,\n" +
                    "ainsi que $$a$$ et $$b$$ deux entiers relatifs.\n\n" +
                    "$$a$$ est congru à $$b$$ modulo $$n$$ si et seulement si :\n" +
                    "- $$n | b - a$$\n" +
                    "- $$∃ k ∈ ℤ : a = b + k \\times n$$\n\n" +
                    "Soit $$a = b \\times q + r$$\n" +
                    "$$\\gcd(a, b)=\\gcd(b, r)$$\n" +
                    "$$a ^ k ≡ b ^ k$$",
            "<say-as interpret-as=\"characters\">n</say-as> un entier supérieur ou égal à 2,\n" +
                    "ainsi que <say-as interpret-as=\"characters\">a</say-as> et <say-as interpret-as=\"characters\">b</say-as> deux entiers relatifs.\n\n" +
                    "<say-as interpret-as=\"characters\">a</say-as> est congru à <say-as interpret-as=\"characters\">b</say-as> modulo <say-as interpret-as=\"characters\">n</say-as> si et seulement si :\n" +
                    "- <say-as interpret-as=\"characters\">n</say-as> divise <say-as interpret-as=\"characters\">b</say-as> moins <say-as interpret-as=\"characters\">a</say-as>\n" +
                    "- il existe <say-as interpret-as=\"characters\">k</say-as> dans <say-as interpret-as=\"characters\">Z</say-as> tel que <say-as interpret-as=\"characters\">a</say-as> égal <say-as interpret-as=\"characters\">b</say-as> plus <say-as interpret-as=\"characters\">k</say-as> fois <say-as interpret-as=\"characters\">n</say-as>\n\n" +
                    "Soit <say-as interpret-as=\"characters\">a</say-as> égal <say-as interpret-as=\"characters\">b</say-as> fois <say-as interpret-as=\"characters\">q</say-as> plus <say-as interpret-as=\"characters\">r</say-as>\n" +
                    "<say-as interpret-as=\"characters\">PGCD</say-as> <say-as interpret-as=\"characters\">a</say-as> <say-as interpret-as=\"characters\">b</say-as> égal <say-as interpret-as=\"characters\">PGCD</say-as> <say-as interpret-as=\"characters\">b</say-as> <say-as interpret-as=\"characters\">r</say-as>\n" +
                    "<say-as interpret-as=\"characters\">a</say-as> puissance <say-as interpret-as=\"characters\">k</say-as> congruent à <say-as interpret-as=\"characters\">b</say-as> puissance <say-as interpret-as=\"characters\">k</say-as>"
    );

}
