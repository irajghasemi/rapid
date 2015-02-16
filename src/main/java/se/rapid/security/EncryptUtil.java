package se.rapid.security;

import java.math.BigInteger;
import java.security.SecureRandom;

public class EncryptUtil {
        private static SecureRandom random = new SecureRandom();
       
        public static String generateRandomString() {
                return new BigInteger(130, random).toString(32);
        }
        public static String makePassword(String password) {
                return BCrypt.hashpw(password, BCrypt.gensalt());
        }
       
        public static Boolean verifyPassowrd(String password, String hashed_password) {
                return BCrypt.checkpw(password, hashed_password);
        }
       
} 