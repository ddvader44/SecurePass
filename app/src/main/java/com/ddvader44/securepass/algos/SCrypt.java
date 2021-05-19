package com.ddvader44.securepass.algos;

public class SCrypt {

    native byte[] nativeScrypt(byte[] password, byte[] salt, int n, int r, int p, int outLen);

    public byte[] scrypt(byte[] password, byte[] salt, int n, int r, int p, int outLen) {
        return nativeScrypt(password, salt, n, r, p, outLen);
    }

}
