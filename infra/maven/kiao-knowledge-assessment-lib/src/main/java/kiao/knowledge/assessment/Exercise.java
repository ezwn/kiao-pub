package kiao.knowledge.assessment;

import lombok.Getter;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Getter
public class Exercise<ET extends IExerciseType, E>
        implements Comparable<Exercise<ET, E>> {

    protected final String name;

    protected final ET exerciseType;

    protected final AttemptLog attemptLog;

    protected final E object;

    public Exercise(String name, ET exerciseType, AttemptLog attemptLog, E object) {
        if (attemptLog == null) {
            throw new IllegalArgumentException("Attempt log was empty for " + name);
        }

        this.object = object;
        this.exerciseType = exerciseType;
        this.name = name;
        this.attemptLog = attemptLog;
    }

    @Override
    public int compareTo(Exercise exercise) {
        int manualOrder = this.exerciseType != null ? this.exerciseType.getOrderNumber() - exercise.exerciseType.getOrderNumber() : 0;
        if (manualOrder != 0)
            return manualOrder;

        long consolidationOrder = consolidationMinutes() - exercise.consolidationMinutes();

        if (consolidationOrder != 0)
            return (int) consolidationOrder;

        return name.compareTo(exercise.name);
    }

    public long consolidationMinutes() {
        return this.getAttemptLog().consolidationMinutes(exerciseType);
    }

    public boolean toDo() {
        Instant nextOccurrenceInstant = earliestNextAttempt();
        return nextOccurrenceInstant.isBefore(Instant.now())
                || nextOccurrenceInstant.equals(Instant.now());
    }

    public Instant earliestNextAttempt() {
        if (attemptLog.empty()) {
            return Instant.parse("2025-01-01T00:00:00Z");
        }

        return attemptLog.lastAttempt().getAt().plus(
                attemptLog.consolidationMinutes(exerciseType),
                ChronoUnit.MINUTES
        );
    }

}
