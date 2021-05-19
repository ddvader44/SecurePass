package com.ddvader44.securepass.algos;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class Argon2Id {

     public String argon(String message){

        Argon2 argon2 = Argon2Factory.create();
        char[] password = message.toCharArray();
        return argon2.hash(10, 65536, 1, password);

    }

}
