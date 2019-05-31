/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.utilities;

import java.util.Random;

public class RandomStringGenerator {

    private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUM = "0123456789";
    private static final String SPL_CHARS = "";
    private static final int MIN_LEN = 6;
    private static final int MAX_LEN = 6;
    private static final int NO_CAPS_ALPHA = 0;
    private static final int NO_DIGITS = 2;
    private static final int NO_SPL_CHARS = 0;

    public static char[] generate( ) {
        if (MIN_LEN > MAX_LEN) {
            throw new IllegalArgumentException("Min. Length > Max. Length!");
        }
        if ((NO_CAPS_ALPHA + NO_DIGITS + NO_SPL_CHARS) > MIN_LEN) {
            throw new IllegalArgumentException("Min. Length should be atleast sum of (CAPS, DIGITS, SPL CHARS) Length!");
        }
        Random rnd = new Random();
        int len = rnd.nextInt(MIN_LEN - MIN_LEN + 1) + MIN_LEN;
        char[] pswd = new char[len];
        int index = 0;
        for (int i = 0; i < NO_CAPS_ALPHA; i++) {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = ALPHA_CAPS.charAt(rnd.nextInt(ALPHA_CAPS.length()));
        }
        for (int i = 0; i < NO_DIGITS; i++) {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = NUM.charAt(rnd.nextInt(NUM.length()));
        }
        for (int i = 0; i < NO_SPL_CHARS; i++) {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = SPL_CHARS.charAt(rnd.nextInt(SPL_CHARS.length()));
        }
        for (int i = 0; i < len; i++) {
            if (pswd[i] == 0) {
                pswd[i] = ALPHA.charAt(rnd.nextInt(ALPHA.length()));
            }
        }
        return pswd;
    }

    @SuppressWarnings("empty-statement")
    private static int getNextIndex(Random rnd, int len, char[] pswd) {
        int index = rnd.nextInt(len);
        while (pswd[index = rnd.nextInt(len)] != 0);
        return index;
    }
}
