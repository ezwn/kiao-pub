package kiao.mlscript.tools;

import lombok.Getter;

/**
 * Vocabulary used to prepare mathematical expressions for text-to-speech.
 */
@Getter
public class MathSymbolToSpeechEnGb implements MathSymbolToSpeech {
    private final String equals = "equals";
    private final String plus = "plus";
    private final String minus = "minus";
    private final String times = "times";
    private final String toThePowerOf = "raised to";

    private final String divides = "divides";

    private final String thereExists = "there exists";
    private final String forAll = "for all";

    private final String in = "in";

    private final String suchAs = "such that";

    private final String setOfNaturalNumbers = "<say-as interpret-as=\"characters\">N</say-as>";
    private final String setOfIntegers = "<say-as interpret-as=\"characters\">Z</say-as>";
    private final String setOfRealNumbers = "<say-as interpret-as=\"characters\">R</say-as>";
    private final String setOfRationalNumbers = "<say-as interpret-as=\"characters\">Q</say-as>";

    private final String gcd = "<say-as interpret-as=\"characters\">GCD</say-as>";
    private final String lcm = "<say-as interpret-as=\"characters\">LCM</say-as>";

    private final String congruentTo = "congruent to";
    private final String choose = "choose";
    private final String prime = "prime";
}
