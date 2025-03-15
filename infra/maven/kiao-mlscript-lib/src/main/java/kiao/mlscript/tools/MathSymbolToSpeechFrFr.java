package kiao.mlscript.tools;

import lombok.Getter;

/**
 * Vocabulary used to prepare mathematical expressions for text to speech.
 */
@Getter
public class MathSymbolToSpeechFrFr implements MathSymbolToSpeech {
    private final String equals = "égal";
    private final String plus = "plus";
    private final String minus = "moins";
    private final String times = "fois";
    private final String toThePowerOf = "puissance";

    private final String divides = "divise";

    private final String thereExists = "il existe";
    private final String forAll = "pour tout";

    private final String in = "dans";

    private final String suchAs = "tel que";

    private final String setOfNaturalNumbers = "<say-as interpret-as=\"characters\">N</say-as>";
    private final String setOfIntegers = "<say-as interpret-as=\"characters\">Z</say-as>";
    private final String setOfRealNumbers = "<say-as interpret-as=\"characters\">R</say-as>";
    private final String setOfRationalNumbers = "<say-as interpret-as=\"characters\">Q</say-as>";

    private final String gcd = "<say-as interpret-as=\"characters\">PGCD</say-as>";
    private final String lcm = "<say-as interpret-as=\"characters\">PPCM</say-as>";

    private final String congruentTo = "congruent à";
    private final String choose = "parmi";
    private final String prime = "prime";
}
