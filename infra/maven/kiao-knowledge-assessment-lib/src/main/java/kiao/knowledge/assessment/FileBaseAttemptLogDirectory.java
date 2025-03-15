package kiao.knowledge.assessment;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileBaseAttemptLogDirectory extends AttemptLogDirectory {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
    }

    private final File file;

    @Getter
    private final Map<String, AttemptLog> attemptLogMap;

    public FileBaseAttemptLogDirectory(String fileName) {
        this.file = new File(fileName);

        if (!file.exists()) {
            attemptLogMap = new HashMap<>();
        } else {
            try {
                attemptLogMap = objectMapper.readValue(
                        file,
                        new TypeReference<HashMap<String, AttemptLog>>() {}
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public AttemptLog put(String key, AttemptLog attemptLog) {
        return attemptLogMap.put(key, attemptLog);
    }

    public AttemptLog get(String key) {
        return attemptLogMap.get(key);
    }

    public void save() throws IOException {
        objectMapper.writeValue(
                file,
                attemptLogMap
        );
    }
}
