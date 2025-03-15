package kiao.mlscript.parser.model;

import java.util.stream.Stream;

public class SectionUtil {

    public static Stream<MLToken> findAllMlToken(Section section) {

        Stream<MLToken> titleStream = section.getTitle() == null ? Stream.of() : section.getTitle().stream();

        Stream<MLToken> partsStream = section.getParts().stream()
                .flatMap(
                        mlComponent -> {
                            if (mlComponent instanceof Section) {
                                Section childSection = (Section) mlComponent;
                                return findAllMlToken(childSection);
                            } else if (mlComponent instanceof Paragraph) {
                                Paragraph paragraph = (Paragraph) mlComponent;
                                return paragraph.stream()
                                        .flatMap(Sentence::stream)
                                        .flatMap(Syntagma::stream);
                            }

                            throw new RuntimeException();
                        }
                );

        return Stream.concat(titleStream, partsStream);
    }

}
