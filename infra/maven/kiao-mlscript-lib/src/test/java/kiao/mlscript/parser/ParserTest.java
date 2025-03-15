package kiao.mlscript.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kiao.mlscript.parser.model.Text;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParserTest {

    static ObjectMapper objectMapper = new ObjectMapper();

    static String readResource(String resource) {
        try (InputStream inputStream = ParserTest.class.getResourceAsStream("/" + resource)) {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                return reader.lines().collect(Collectors.joining("\n"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Test
    public void testParser1() throws JsonProcessingException {
        String number = "01";
        extracted(number);
    }

    @Test
    public void testParser2() throws JsonProcessingException {
        String number = "02";
        extracted(number);
    }

    private static void extracted(String number) throws JsonProcessingException {
        List<String> languages = new ArrayList<>();
        languages.add("FR");
        languages.add("EN");

        List<String> texts = new ArrayList<>();
        texts.add(readResource("text-"+ number +"-fr.md"));
        texts.add(readResource("text-"+ number +"-en.md"));

        Text result = new Parser(languages, texts).parse();

        Assertions.assertEquals(
                objectMapper.readTree(readResource("text-"+ number +".json")),
                objectMapper.readTree(objectMapper
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(result))
        );
    }
}
