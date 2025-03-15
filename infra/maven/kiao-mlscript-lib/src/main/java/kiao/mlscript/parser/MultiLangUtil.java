package kiao.mlscript.parser;

import kiao.mlscript.lexer.Lexer;
import kiao.mlscript.lexer.Token;
import kiao.mlscript.parser.model.MLToken;

import java.util.ArrayList;
import java.util.List;

public class MultiLangUtil {

    public static List<MLToken> toMlTokenList(List<String> languages, List<String> texts) {
        if (languages.isEmpty()) {
            throw new IllegalArgumentException("Empty languages list");
        }

        if (texts.isEmpty()) {
            throw new IllegalArgumentException("Empty texts list");
        }

        if (languages.size() != texts.size()) {
            throw new IllegalArgumentException("Languages and texts lists have different sizes.");
        }

        List<List<Token>> listOfListOfTokens = new ArrayList<>();
        for (int l = 0; l < languages.size(); l++) {
            listOfListOfTokens.add(Lexer.parse(languages.get(l), texts.get(l)));
        }

        final int numberOfTokensPerList = listOfListOfTokens.get(0).size();

        if (listOfListOfTokens.stream().anyMatch(list -> list.size() != numberOfTokensPerList)) {
            throw new IllegalArgumentException("All lists must have the same number of token");
        }

        List<MLToken> listOfMLToken = new ArrayList<>();
        for (int iT = 0; iT < numberOfTokensPerList; iT++) {
            MLToken mlToken = new MLToken();
            for (int iL = 0; iL < languages.size(); iL++) {
                String lang = languages.get(iL);
                mlToken.put(lang, listOfListOfTokens.get(iL).get(iT));
            }
            listOfMLToken.add(mlToken);
        }

        return listOfMLToken;
    }

}
