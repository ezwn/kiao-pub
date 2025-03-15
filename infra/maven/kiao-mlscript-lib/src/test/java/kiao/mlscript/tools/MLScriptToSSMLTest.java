package kiao.mlscript.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MLScriptToSSMLTest {

    @Test
    void test() {
        Assertions.assertEquals(
                Mocks.mathText.getSsml(),
                new MLScriptToSSML(new MathSymbolToSpeechFrFr())
                        .toSSLM(Mocks.mathText.getBody())
        );
    }

}
