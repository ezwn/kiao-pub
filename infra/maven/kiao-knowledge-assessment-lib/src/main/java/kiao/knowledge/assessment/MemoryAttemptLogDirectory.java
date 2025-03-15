package kiao.knowledge.assessment;

import java.util.HashMap;
import java.util.Map;

public class MemoryAttemptLogDirectory extends AttemptLogDirectory {

    private final Map<String, AttemptLog> attemptLogMap = new HashMap<>();

    public AttemptLog put(String key, AttemptLog attemptLog) {
        return attemptLogMap.put(key, attemptLog);
    }

    public AttemptLog get(String key) {
        return attemptLogMap.get(key);
    }
}
