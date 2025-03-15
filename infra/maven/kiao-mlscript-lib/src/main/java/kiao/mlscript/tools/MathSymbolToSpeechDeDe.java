package kiao.mlscript.tools;

import lombok.Getter;

/**
 * Vokabular zur Vorbereitung mathematischer Ausdrücke für Text-to-Speech.
 */
@Getter
public class MathSymbolToSpeechDeDe implements MathSymbolToSpeech {
    private final String equals = "gleich";
    private final String plus = "plus";
    private final String minus = "minus";
    private final String times = "mal";
    private final String toThePowerOf = "hoch";

    private final String divides = "teilt";

    private final String thereExists = "es existiert";
    private final String forAll = "für alle";

    private final String in = "in";

    private final String suchAs = "sodass";

    private final String setOfNaturalNumbers = "<say-as interpret-as=\"characters\">N</say-as>";
    private final String setOfIntegers = "<say-as interpret-as=\"characters\">Z</say-as>";
    private final String setOfRealNumbers = "<say-as interpret-as=\"characters\">R</say-as>";
    private final String setOfRationalNumbers = "<say-as interpret-as=\"characters\">Q</say-as>";

    private final String gcd = "<say-as interpret-as=\"characters\">GGT</say-as>";
    private final String lcm = "<say-as interpret-as=\"characters\">KGV</say-as>";

    private final String congruentTo = "kongruent zu";
    private final String choose = "über";
    private final String prime = "Strich";
}

