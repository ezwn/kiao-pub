package kiao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Logging interface working both on Desktop and Android
 */
public class CrossPlatformLogger {

    public static String logDirectoryPath = null;

    private static final boolean DEBUG = true;
    private static final boolean EXCEPTION_ON_RECOVERABLE_ISSUE = false;

    private static final String fileName = "log-"
            + new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()) + ".log";

    public static String keyVal(String key, Object val) {
        return " * " + key + ": [" + val + "]";
    }

    public static void debug(String message) {
        if (DEBUG) {
            System.out.println(message);
            logToFile(message);
        }
    }

    public static void error(String message) {
        System.err.println(message);
        logToFile(message);
    }

    public static void info(String message) {
        System.out.println(message);
        logToFile(message);
    }

    /**
     * For an issue that impacts use experience, but is recoverable
     */
    public static void recoverableIssue(String message) {
        if (EXCEPTION_ON_RECOVERABLE_ISSUE) {
            throw new RuntimeException(message);
        } else {
            System.err.println(message);
            logToFile(message);
        }
    }

    private static void logToFile(String message) {
        if (logDirectoryPath == null) {
            return;
        }

        String logFilePath = logDirectoryPath + "/" + fileName;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Failed to write log: " + e.getMessage());
        }
    }
}