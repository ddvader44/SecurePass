package com.ddvader44.securepass.algos;


import org.apache.commons.codec.binary.Hex;


import java.security.NoSuchAlgorithmException;

import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PBKFD2 {

    public static String pbkfd(String message) throws NoSuchAlgorithmException, InvalidKeySpecException {

        // generate salts

        //SecureRandom random = new SecureRandom();
        //byte[] salt = new byte[128];
        //random.nextBytes(salt);

        String salt = "1234";

        KeySpec spec = new PBEKeySpec(message.toCharArray(), salt.getBytes(), 10000, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");

        byte[] hash = factory.generateSecret(spec).getEncoded();

        String hashedString = Hex.encodeHexString(hash);

        return hashedString;

    }

}
