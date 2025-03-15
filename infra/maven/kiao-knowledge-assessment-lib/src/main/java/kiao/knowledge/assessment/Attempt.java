package kiao.knowledge.assessment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Attempt {

    private static final int FAILURE = 0;
    private static final int SUCCESS = 1;
    private static final int TOO_EASY = 2;

    private Instant at;
    private int points;

    public Attempt(int points) {
        this(Instant.now(), points);
    }

    public Attempt(Instant instant, int points) {
        this.at = instant;
        this.points = points;
    }

    public Attempt(String instant, int points) {
        this(Instant.parse(instant), points);
    }

    public boolean success() {
        return points > 0;
    }

    public String toString() {
        return formatInstant(getAt()) + " | " + (points != 0 ? "SUCCESS" : "FAILURE");
    }
    
    public String toString(Attempt previous) {
        if (previous == null)
            return toString();

        return this + " | +" + Duration.between(previous.getAt(), this.at).toHours() + " H";
    }

    private String formatInstant(Instant now) {
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm | yyyy-MM-dd");
        return zonedDateTime.format(formatter);
    }
}
