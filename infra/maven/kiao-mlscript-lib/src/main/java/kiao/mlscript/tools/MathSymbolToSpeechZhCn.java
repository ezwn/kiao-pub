package kiao.mlscript.tools;

import lombok.Getter;

/**
 * 用于将数学表达式转换为文本转语音的词汇。
 */
@Getter
public class MathSymbolToSpeechZhCn implements MathSymbolToSpeech {
    private final String equals = "等于";
    private final String plus = "加";
    private final String minus = "减";
    private final String times = "乘";
    private final String toThePowerOf = "的幂";

    private final String divides = "除以";

    private final String thereExists = "存在";
    private final String forAll = "对于所有";

    private final String in = "属于";

    private final String suchAs = "使得";

    private final String setOfNaturalNumbers = "<say-as interpret-as=\"characters\">N</say-as>";
    private final String setOfIntegers = "<say-as interpret-as=\"characters\">Z</say-as>";
    private final String setOfRealNumbers = "<say-as interpret-as=\"characters\">R</say-as>";
    private final String setOfRationalNumbers = "<say-as interpret-as=\"characters\">Q</say-as>";

    private final String gcd = "<say-as interpret-as=\"characters\">GCD</say-as>";
    private final String lcm = "<say-as interpret-as=\"characters\">LCM</say-as>";

    private final String congruentTo = "全等于";
    private final String choose = "组合";
    private final String prime = "质数";
}
