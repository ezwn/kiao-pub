package kiao.knowledge.assessment;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ResultList {

    private List<ResultModel> results;

    public ResultList(List<ResultModel> results) {
        this.results = results;
    }

    public void saveTo(AttemptLogDirectory repository) {
        results.forEach(n -> n.saveTo(repository));
    }

    public ResultModel get(String id) {
        return results.stream().filter(node -> node.getExerciseId().equals(id)).findFirst().get();
    }

    public ResultList switchSuccessAndTooEasy(String id) {
        get(id).switchSuccessAndTooEasy();
        return this;
    }

    public ResultList switchSuccess(String id) {
        get(id).switchSuccess();
        return this;
    }

    public ResultList switchTooEasy(String id) {
        get(id).switchTooEasy();
        return this;
    }
}
