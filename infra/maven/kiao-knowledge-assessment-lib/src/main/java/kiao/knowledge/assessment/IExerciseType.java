package kiao.knowledge.assessment;

public interface IExerciseType {

    int getOrderNumber();

    /**
     * The minimal duration between two attempts
     */
    long getRefractoryMinutes();

    double getGrowthFactor();
    double getDegrowthFactor();
}
