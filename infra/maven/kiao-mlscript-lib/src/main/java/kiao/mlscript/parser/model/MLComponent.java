package kiao.mlscript.parser.model;

import kiao.mlscript.TrainingElement;

public interface MLComponent extends TrainingElement {

    boolean isSentenceEnd();
    boolean isWritten();
    boolean isWrittenExercise();

}
