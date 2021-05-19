package com.ddvader44.securepass.algos;

public class BCrypt {

    public String bcrypt(String message){

        return at.favre.lib.crypto.bcrypt.BCrypt.withDefaults().hashToString(10,message.toCharArray());

    }

}
