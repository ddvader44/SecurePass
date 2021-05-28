package com.ddvader44.securepass.algos;

import org.signal.argon2.Argon2;
import org.signal.argon2.Argon2Exception;
import org.signal.argon2.MemoryCost;
import org.signal.argon2.Type;
import org.signal.argon2.Version;


public class Argon2Id {

     public static String argon(String message) throws Argon2Exception {

         Argon2 argon2 = new Argon2.Builder(Version.V13)
                 .type(Type.Argon2id)
                 .memoryCost(MemoryCost.MiB(32))
                 .parallelism(1)
                 .iterations(3)
                 .build();

         String salt = "YOtX2//7NoD/owm8RZ8llw==";

         Argon2.Result result = argon2.hash(message.getBytes(), salt.getBytes());

         //byte[] hash    = result.getHash();
         //String encoded = result.getEncoded();

         return result.getHashHex();

    }

}
