package kiao.mlscript.parser.model;

import kiao.mlscript.Uid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MLNode<T extends MLComponent>
        extends ArrayList<T>
        implements MLComponent {

    public MLNode() {}

    public MLNode(T[] components) {
        addAll(Arrays.asList(components));
    }

    @Override
    public boolean isSentenceEnd() {
        return stream().anyMatch(MLComponent::isSentenceEnd);
    }

    @Override
    public boolean isWritten() {
        return stream().anyMatch(MLComponent::isWritten);
    }

    @Override
    public boolean isWrittenExercise() {
        return stream().anyMatch(MLComponent::isWrittenExercise);
    }

    @Override
    public String uid() {
        return Uid.forString(stream()
                .map(MLComponent::uid)
                .collect(Collectors.joining("::"))
        );
    }

    @Override
    public String toString(String lang) {
        return stream()
                .map(c -> c.toString(lang))
                .collect(Collectors.joining(" "));
    }
}
