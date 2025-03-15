package kiao.mlscript.parser;

import kiao.mlscript.parser.model.*;

import java.util.List;

public class Parser {

    private final List<MLToken> mlTokenList;
    private int offset;

    public Parser(List<String> languages, List<String> texts) {
        this.mlTokenList = MultiLangUtil.toMlTokenList(languages, texts);
        offset = 0;
    }

    public Text parse() {
        Text text = new Text();
        while (!endOfTokenList()) {
            text.add(buildSectionOrParagraph(1));
        }
        return text;
    }

    private MLComponent buildSectionOrParagraph(int level) {
        int offsetBackup = offset;

        Syntagma syntagma = buildSyntagma();
        if (syntagma.getTitleLevel() == level) {
            Section section = new Section();

            section.setTitle(syntagma);
            section.setLevel(level);

            if (endOfParagraph()) {
                offset++;
            }

            while (!endOfTokenList() && !endOfSection(level)) {
                section.getParts().add(buildSectionOrParagraph(level + 1));
            }

            return section;
        } else {
            offset = offsetBackup;
            return buildParagraph();
        }
    }

    private boolean endOfSection(int level) {
        return currentToken().getTitleLevel() > 0 && currentToken().getTitleLevel() <= level;
    }

    private Paragraph buildParagraph() {
        Paragraph paragraph = new Paragraph();
        while (!endOfTokenList() && !endOfParagraph()) {
            paragraph.add(buildSentence());
        }
        if (!endOfTokenList() && endOfParagraph()) {
            offset++;
        }
        return paragraph;
    }

    private Sentence buildSentence() {
        Sentence sentence = new Sentence();
        do {
            sentence.add(buildSyntagma());
        } while (!endOfTokenList() && !endOfParagraph() && !sentence.isSentenceEnd());
        return sentence;
    }

    private Syntagma buildSyntagma() {
        Syntagma syntagma = new Syntagma();

        while (currentToken().isVocabulary()) {
            syntagma.add(currentToken());
            offset++;
        }

        syntagma.add(currentToken());
        offset++;

        return syntagma;
    }

    private MLToken currentToken() {
        return mlTokenList.get(offset);
    }

    private boolean endOfParagraph() {
        return currentToken().isParagraphEnd();
    }

    private boolean endOfTokenList() {
        return offset >= mlTokenList.size();
    }

}
