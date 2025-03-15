package kiao.knowledge.assessment;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class AttemptLogStats {

    private long consolidationMinutes;
    private Instant earliestNextAttempt;

    public AttemptLogStats(Exercise<?, ?> exercise) {
        consolidationMinutes = exercise.consolidationMinutes();
        earliestNextAttempt = exercise.earliestNextAttempt();
    }

}
