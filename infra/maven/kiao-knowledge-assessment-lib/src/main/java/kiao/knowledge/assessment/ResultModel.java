package kiao.knowledge.assessment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultModel {

    private String exerciseId;
    private String label;
    private boolean success;
    private boolean tooEasy;
    private boolean saveResult;
    private AttemptLog attemptLog;
    private AttemptLogStats attemptLogStats;

    public ResultModel(
            String exerciseId,
            String label,
            boolean saveResult,
            Exercise<?, ?> exercise
    ) {
        this.exerciseId = exerciseId;
        this.label = label;
        this.success = true;
        this.tooEasy = false;
        this.saveResult = saveResult;
        this.attemptLog = exercise.getAttemptLog();
        if (attemptLog != null && exercise.exerciseType != null) {
            this.attemptLogStats = new AttemptLogStats(exercise);
        }
    }

    public void saveTo(AttemptLogDirectory repository) {
        if (saveResult) {
            repository.addAttempt(exerciseId,
                    new Attempt(!success ? 0 : !tooEasy ? 1 : 3));
        }
    }

    public ResultModel switchSuccessAndTooEasy() {
        if (!success && !tooEasy) {
            success = true;
            tooEasy = false;
        } else if (success) {
            success = false;
            tooEasy = true;
        } else {
            success = false;
            tooEasy = false;
        }
        return this;
    }

    public ResultModel switchSuccess() {
        this.success = !this.success;
        return this;
    }

    public ResultModel switchTooEasy() {
        this.tooEasy = !this.tooEasy;
        return this;
    }

}
