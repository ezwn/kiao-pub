package kiao.mlscript.parser.model;

public class Sentence extends MLNode<Syntagma> {

    public Sentence() {}

    public boolean isOptional() {
        return stream()
                .findFirst()
                .map(Syntagma::findFirstNonVocabulary)
                .orElseThrow(RuntimeException::new)
                .isHasOptionalPrefix();
    }

}
