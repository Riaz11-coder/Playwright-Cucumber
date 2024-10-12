package utilities;

import java.util.Base64;
import java.util.Properties;

public class PasswordUtils {

    // Base64 encoding: This is used to encrypt the password and save the encrypted value in config.properties file
    public static String encrypt(String password) {
        try {
            byte[] encodedBytes = Base64.getEncoder().encode(password.getBytes("UTF-8"));
            return new String(encodedBytes);
        } catch (Exception e) {
            System.out.println("Password was not encrypted.");
            return null;
        }
    }

    // Base64 decoding: This is used to decrypt the password from the encrypted value in config.properties file
    public static String decrypt(String encryptedPassword) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
            return new String(decodedBytes);
        } catch (Exception e) {
            System.out.println("Password was not decrypted.");
            return null;
        }
    }
}
