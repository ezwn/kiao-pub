package kiao.knowledge.assessment;

public abstract class AttemptLogDirectory {

    public abstract AttemptLog put(String key, AttemptLog attemptLog);
    public abstract AttemptLog get(String key);

    public void addAttempt(String key, Attempt attempt) {
        assertAttemptLogKeyNotNull(key);

        getOrEmpty(key).addAttempt(attempt);
    }

    public AttemptLog getOrEmpty(String key) {
        assertAttemptLogKeyNotNull(key);

        AttemptLog attemptLog = get(key);
        if (attemptLog == null) {
            attemptLog = new AttemptLog();
            put(key, attemptLog);
        }
        return attemptLog;
    }

    private static void assertAttemptLogKeyNotNull(String key) {
        if (key ==null) {
            throw new IllegalArgumentException("AttemptLog key can't be null");
        }
    }
}
