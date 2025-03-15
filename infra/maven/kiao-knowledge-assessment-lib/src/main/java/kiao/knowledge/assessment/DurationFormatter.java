package kiao.knowledge.assessment;

public class DurationFormatter {
    public static String format(long totalMinutes) {

        long days = totalMinutes / (24 * 60);
        long hours = (totalMinutes % (24 * 60)) / 60;
        long min = totalMinutes % 60;

        // Format the output
        String formattedDuration;
        if (days > 0) {
            formattedDuration = String.format("%d days, %d hours", days, hours);
        } else if (hours > 0) {
            formattedDuration = String.format("%d hours, %d min", hours, min);
        } else {
            formattedDuration = String.format("%d min", min);
        }

        return formattedDuration;
    }
}
