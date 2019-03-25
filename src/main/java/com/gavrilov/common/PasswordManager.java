package com.gavrilov.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class PasswordManager {

    /**
     * Внутренний класс для шифровки пароле пользователей MD5
     */
    public static class MD5 {
        private static final Logger log = LoggerFactory.getLogger(MD5.class);
        private static MessageDigest digester;

        static {
            try {
                digester = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        public static String crypt(String str) {
            if (str == null || str.length() == 0) {
                log.warn("Строка, которую шифрует не должна быть null или пустой");
            }
            digester.update(str.getBytes());
            byte[] hash = digester.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte aHash : hash) {
                if ((0xff & aHash) < 0x10) {
                    hexString.append("0").append(Integer.toHexString((0xFF & aHash)));
                } else {
                    hexString.append(Integer.toHexString(0xFF & aHash));
                }
            }
            return hexString.toString();
        }

    }
}
