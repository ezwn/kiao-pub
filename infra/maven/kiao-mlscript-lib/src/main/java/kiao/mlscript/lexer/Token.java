package kiao.mlscript.lexer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kiao.mlscript.TrainingElement;
import kiao.mlscript.Uid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Token implements TrainingElement {

    private final String lang;
    private boolean isVocabulary;
    private boolean isQuestion;
    private boolean isWrittenExercise;
    private int percentCount = 0;
    private boolean isWrittenSection;
    private boolean hasOptionalPrefix;
    private boolean isDisabled;
    private int titleLevel = 0;
    private int exclamationCount = 0;
    private boolean isUnorderedListItem;
    private Integer orderedListNumber;
    private String rest;
    private Map<String, String> attributes;

    private final static char PREFIX_TILDA = '~';
    private final static char PREFIX_CARET = '^';
    private final static char PREFIX_QUESTION = '?';
    private final static char PREFIX_PERCENT = '%';
    private final static char PREFIX_AT_SIGN = '@';
    private final static char PREFIX_EXCLAMATION = '!';
    private final static char PREFIX_SHARP = '#';
    private final static char PREFIX_HYPHEN = '-';
    private final static char PREFIX_EURO = '€';

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private boolean followedByDot(String cleanString, int s) {
        for(int i = s + 1; i < cleanString.length(); i++) {
            char c = cleanString.charAt(i);
            if (!Character.isDigit(c)) {
                if (c == '.') {
                    return true;
                } else {
                    return false;
                }
            }
        }

        return false;
    }

    public Token(String lang, String string) {
        this.lang = lang;

        StringBuilder number = new StringBuilder();
        for(int i=0; i<string.length(); i++) {
            char c = string.charAt(i);

            if (Character.isDigit(c) && followedByDot(string, i)) {
                number.append(c);
                continue;
            }

            switch (c) {
                case PREFIX_CARET:
                    isVocabulary = true;
                    break;
                case PREFIX_QUESTION:
                    isQuestion = true;
                    break;
                case PREFIX_AT_SIGN:
                    isWrittenSection = true;
                    break;
                case PREFIX_PERCENT:
                    percentCount++;
                    break;
                case PREFIX_TILDA:
                    if (!hasOptionalPrefix) {
                        hasOptionalPrefix = true;
                    } else {
                        isDisabled = true;
                    }
                    break;
                case PREFIX_SHARP:
                    titleLevel++;
                    break;
                case PREFIX_EXCLAMATION:
                    exclamationCount++;
                    break;
                case PREFIX_HYPHEN:
                    isUnorderedListItem = true;
                    break;
                case PREFIX_EURO:
                    isWrittenExercise = true;
                    break;
                case '.':
                    if (number.length() > 0) {
                        orderedListNumber = Integer.parseInt(number.toString());
                    }
                    break;
                case ' ':
                    break;
                default:
                    String[] parts = string.substring(i).split(" // ");
                    rest = parts[0].trim();
                    if (parts.length > 1) {
                        try {
                            attributes = OBJECT_MAPPER.readValue(parts[1], HashMap.class);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return;
            }
        }
    }

    @Override
    public String uid() {
        if (isParagraphEnd()) {
            throw new RuntimeException("ParagraphEnds have no unique id.");
        }
        return Uid.forString(rest);
    }

    @Override
    public String toString(String lang) {
        return rest;
    }

    public String toMarkDown() {
        return rest;
    }

    @Override
    public String toString() {
        return rest;
    }

    public boolean isParagraphEnd() {
        return rest == null;
    }

    public static String removeTrailingPunctuation(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        int i = input.length() - 1;
        char last = input.charAt(i);
        while (
                last == '.'
                || last == '。'
                || last == '．'
                || last == '｡'
                || last == '!'
                || last == '！'
                || last == '?'
                || last == '？'
                || last == '…'
                || last == '।'
                || last == ','
                || last == '，'
                || last == ':'
                || last == '：'
                || last == ';'
                || last == '；'
        ) {
            i--;
            last = input.charAt(i);
        }
        return input.substring(0, i + 1).trim();
    }


    public String restWithoutPunctuation() {
        return removeTrailingPunctuation(rest);
    }

    public boolean isSentenceEnd() {
        if (rest == null || rest.isEmpty()) {
            return false;
        }
        char last = rest.charAt(rest.length() - 1);
        switch (last) {
            case '.':
            case '。':
            case '．': // fullwidth period
            case '｡':  // halfwidth ideographic full stop
            case '!':
            case '！':
            case '?':
            case '？':
            case '…':
            case '।':
                return true;
            default:
                return false;
        }
    }

    public boolean isPropositionEnd() {
        if (rest == null || rest.isEmpty()) {
            return false;
        }

        char last = rest.charAt(rest.length() - 1);
        switch (last) {
            case ',':
            case '，':
            case ':':
            case '：':
            case ';':
            case '；':
            case '、':
                return true;
            default:
                return false;
        }
    }
}
