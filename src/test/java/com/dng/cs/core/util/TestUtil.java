package com.dng.cs.core.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class TestUtil {
    static public String randomStringNumber(int length) {
        Random rand = new Random();
        long random = rand.nextLong();
        return String.valueOf(random).substring(1, length+1);
    }
}
