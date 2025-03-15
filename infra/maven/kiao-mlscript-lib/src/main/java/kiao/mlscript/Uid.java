package kiao.mlscript;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Uid {

    public static String forString(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * hashBytes.length);
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash", e);
        }
    }

    public static String forStringMurmur(String input) {
        return Hashing.murmur3_128()
                .hashString(input, StandardCharsets.UTF_8)
                .toString();
    }
}
