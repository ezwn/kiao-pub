package kiao.mlscript.tools;

public class MLScriptToText {

    public static String toText(String source) {
        return source
                .replaceAll("\\$\\$", "")
                .replaceAll("\\\\times", "×")
                .replaceAll("\\\\gcd", "gcd")
                .replaceAll("\\\\lcm", "lcm");
    }

}
