package kiao.mlscript.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MLScriptToTextTest {

    @Test
    public void testPlayer() {
        Assertions.assertEquals(
                "M × M' = I",
                MLScriptToText.toText("$$M \\times M' = I$$")
        );
    }

}
