package kiao.mlscript.parser.model;

public class Paragraph extends MLNode<Sentence> {

    public Paragraph() {}

    public boolean isOptional() {
        return stream()
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .isOptional();
    }

}
