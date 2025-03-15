package kiao.mlscript.tools;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MLScriptToSSML {

    private final MathSymbolToSpeech mathSymbolToSpeech;

    public MLScriptToSSML(String langCode) {
        switch (langCode) {
            case "fr-FR":
                mathSymbolToSpeech = new MathSymbolToSpeechFrFr();
                break;
            case "de-DE":
                mathSymbolToSpeech = new MathSymbolToSpeechDeDe();
                break;
            case "cmn-CN":
                mathSymbolToSpeech = new MathSymbolToSpeechZhCn();
                break;
            default:
                mathSymbolToSpeech = new MathSymbolToSpeechEnGb();
                break;
        }
    }

    public String toSSLM(String text) {
        String[] tokens = text.split("\\$\\$");
        List<String> output = new ArrayList<>();

        boolean math = false;
        for (String token : tokens) {
            if (math) {
                output.add(mathContentToSSML(token));
            } else {
                output.add(token);
            }
            math = !math;
        }

        return String.join("", output);
    }

    private final List<String> excluded = Arrays.asList(new String[] { "", "(", ")", "," });

    private String mathContentToSSML(String math) {

        String regex = "\\\\[^\\s(]*|.";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(math);

        ArrayList<String> result = new ArrayList<>();
        while (matcher.find()) {
            //System.out.println("[" + matcher.group() + "]");
            result.add(matcher.group());
        }

        return result.stream()
                .filter(c -> !excluded.contains(c.trim()))
                .map(this::mathCharacterToSSML)
                .collect(Collectors.joining(" "));
    }

    private String mathCharacterToSSML(String mathChar) {
        switch (mathChar) {
            case "+":
                return mathSymbolToSpeech.getPlus();
            case "-":
                return mathSymbolToSpeech.getMinus();
            case "\\times":
            case "×":
                return mathSymbolToSpeech.getTimes();
            case "\\gcd":
                return mathSymbolToSpeech.getGcd();
            case "\\lcm":
                return mathSymbolToSpeech.getLcm();
            case "=":
                return mathSymbolToSpeech.getEquals();
//            case "≠":
//                return mathSymbolToSpeech.getEquals();
            case "|":
                return mathSymbolToSpeech.getDivides();
            case "∃":
                return mathSymbolToSpeech.getThereExists();
            case "∈":
                return mathSymbolToSpeech.getIn();
            case "ℤ":
                return mathSymbolToSpeech.getSetOfIntegers();
            case "ℝ":
                return mathSymbolToSpeech.getSetOfRealNumbers();
            case "ℕ":
                return mathSymbolToSpeech.getSetOfNaturalNumbers();
            case ":":
                return mathSymbolToSpeech.getSuchAs();
            case "^":
                return mathSymbolToSpeech.getToThePowerOf();
            case "≡":
                return mathSymbolToSpeech.getCongruentTo();
            case "'":
                return mathSymbolToSpeech.getPrime();
            case "\\choose":
                return mathSymbolToSpeech.getChoose();
        }

        if (Character.isLetter(mathChar.charAt(0))) {
            return String.format("<say-as interpret-as=\"characters\">%s</say-as>", mathChar);
        }

        throw new RuntimeException(String.format("%s could not be interpreted in mathematical context", mathChar));
    }

}
