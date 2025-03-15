package kiao.knowledge.assessment;

import lombok.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttemptLog {

    private int count;
    private List<Attempt> log = new ArrayList<>();
    private Long score;

    public AttemptLog(int count, List<Attempt> log) {
        this.count = count;
        this.log = log;
    }

    public void addAttempt(Attempt attempt) {
        count++;
        log.add(0, attempt);
        clean();
        score = null;
    }

    public boolean empty() {
        return log.isEmpty();
    }

    public Attempt lastAttempt() {
        return log.get(0);
    }

    private void clean() {
        while(log.size() > 20) {
            log.remove(log.size() - 1);
        }
    }

    public long consolidationMinutes(IExerciseType exerciseType) {
        if (score != null) {
            return score;
        }

        // if new exercise : score = 0
        if (log.isEmpty()) {
            score = 0L;
            return score;
        }

        // Browse the previous attempts to find two success
        Attempt lastSuccess = null;
        Attempt secondToLastSuccess = null;
        int failureCount = 0;
        for(Attempt attempt : log) {
            if (attempt.success()) {
                if (lastSuccess == null) {
                    lastSuccess = attempt;
                } else {
                    secondToLastSuccess = attempt;
                    break;
                }
            } else {
                failureCount++;
            }
        }

        // if never succeeded : refractory period is used as consolidation
        if (lastSuccess == null) {
            return exerciseType.getRefractoryMinutes();
        }

        // if only one success : refractory period is used as consolidation
        if (secondToLastSuccess == null) { // succeeded once
            return exerciseType.getRefractoryMinutes();
        }

        long meantime = Duration.between(secondToLastSuccess.getAt(), lastSuccess.getAt()).toMinutes();
        long theoreticBestConsolidation = getTheoreticBestConsolidation(exerciseType);

        long reftime = Math.min(
                meantime,
                theoreticBestConsolidation
        );

        long computedConsolidation;

        if (failureCount==0) {
            computedConsolidation = (long) (reftime * exerciseType.getGrowthFactor() * lastSuccess.getPoints());
        } else {
            computedConsolidation = reftime / (long) Math.pow(exerciseType.getDegrowthFactor(), failureCount);
        }

        score = Math.max(
                exerciseType.getRefractoryMinutes(),
                computedConsolidation
        );

        return score;
    }

    private long getTheoreticBestConsolidation(IExerciseType exerciseType) {
        long pointsCount = log.stream()
                .map(Attempt::getPoints)
                .reduce(Integer::sum)
                .orElse(0);

        return exerciseType.getRefractoryMinutes() * (long)Math.pow(exerciseType.getGrowthFactor(), pointsCount);
    }

}
