package kiao.knowledge.assessment;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class ExerciseType implements IExerciseType {

    private final String name;
    private final int orderNumber;
    private final long refractoryMinutes;
    private final double growthFactor;
    private final double degrowthFactor;

    @Override
    public String toString() {
        return name;
    }

}
