package kiao.mlscript.parser.model;

import kiao.mlscript.lexer.Token;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Setter
@Getter
public class Section implements MLComponent {

    private Syntagma title;
    private int level = 1;
    private MLNode<MLComponent> parts = new MLNode<>();

    public Section() {}

    public boolean randomized() {
        return title.percent();
    }

    @Override
    public boolean isSentenceEnd() {
        return false;
    }

    @Override
    public boolean isWritten() {
        return title.isWritten();
    }

    @Override
    public boolean isWrittenExercise() {
        return title.isWrittenExercise();
    }

    public boolean hasUid() {
        return getIdFromAttributes().isPresent();
    }

    private Optional<String> getIdFromAttributes() {
        if (title == null) {
            return Optional.empty();
        }

        MLToken mlToken = title.stream()
                .filter(mt -> !mt.isVocabulary())
                .findFirst()
                .orElseThrow(RuntimeException::new);

        List<String> langs = mlToken.values().stream()
                .map(Token::getLang)
                .collect(Collectors.toList());

        Token token = mlToken.get(langs.get(0));

        Map<String, String> attributes = token.getAttributes();

        if (attributes == null)
            return Optional.empty();

        return Optional.ofNullable(attributes.get("id"));
    }

    @Override
    public String uid() {
        return getIdFromAttributes().orElse("");
    }

    @Override
    public String toString(String lang) {
        return "";
    }
}
