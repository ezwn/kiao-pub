package kiao.mlscript.parser.model;

import java.util.stream.Collectors;

public class Syntagma extends MLNode<MLToken> {

    public Syntagma() {}

    public Syntagma(MLToken[] components) {
        super(components);
    }

    public int getTitleLevel() {
        return stream()
                .map(MLToken::getTitleLevel)
                .max(Integer::compareTo)
                .orElse(0);
    }

    public MLToken findFirstNonVocabulary() {
        return stream()
                .filter(mlToken -> !mlToken.isVocabulary())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Syntagma can't contain only vocabulary"));
    }

    public boolean isQuestion() {
        return stream().anyMatch(MLToken::isQuestion);
    }

    public boolean percent() {
        return stream().anyMatch(MLToken::percent);
    }

    @Override
    public String toString(String lang) {
        return stream()
                .filter(mlToken -> !mlToken.isVocabulary())
                .map(c -> c.toString(lang))
                .collect(Collectors.joining(" "));
    }
}
