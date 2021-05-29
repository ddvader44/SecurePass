package com.ddvader44.securepass.algos;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ddvader44.securepass.algos.lthash.LtHash32;

public class LtHashAlgo {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String ltalgo(String message) {

        LtHash32 ltHash = new LtHash32();
        ltHash.add(message.getBytes());
        byte[] checksum = ltHash.getChecksum();

        return checksum.toString();

    }
}
