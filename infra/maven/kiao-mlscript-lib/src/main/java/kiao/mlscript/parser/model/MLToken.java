package kiao.mlscript.parser.model;

import kiao.mlscript.Uid;
import kiao.mlscript.lexer.Token;

import java.util.HashMap;
import java.util.stream.Collectors;

public class MLToken
            extends HashMap<String, Token>
            implements MLComponent {

    public MLToken() {}

    @Override
    public String uid() {
        if (isParagraphEnd()) {
            throw new RuntimeException("ParagraphEnds have no unique id.");
        }

        return Uid.forString(values().stream()
                .map(Token::uid)
                .collect(Collectors.joining("::"))
        );
    }

    @Override
    public String toString(String lang) {
        return get(lang).toString();
    }

    public int getTitleLevel() {
        return values().stream()
                .map(Token::getTitleLevel)
                .max(Integer::compareTo)
                .orElse(0);
    }

    public boolean percent() {
        return values().stream().anyMatch(tokenWithLang -> tokenWithLang.getPercentCount() > 0);
    }

    public boolean isVocabulary() {
        return values().stream().anyMatch(Token::isVocabulary);
    }

    public boolean isQuestion() {
        return values().stream().anyMatch(Token::isQuestion);
    }

    public boolean isParagraphEnd() {
        return values().stream().allMatch(Token::isParagraphEnd);
    }

    @Override
    public boolean isSentenceEnd() {
        return values().stream().anyMatch(Token::isSentenceEnd);
    }

    @Override
    public boolean isWritten() {
        return values().stream().anyMatch(Token::isWrittenSection);
    }

    @Override
    public boolean isWrittenExercise() {
        return values().stream().anyMatch(Token::isWrittenExercise);
    }

    public boolean isHasOptionalPrefix() {
        return values().stream().allMatch(Token::isHasOptionalPrefix);
    }

}
