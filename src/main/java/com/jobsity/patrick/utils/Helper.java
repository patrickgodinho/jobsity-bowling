package com.jobsity.patrick.utils;

public class Helper {
    static public int getIntegerValue(String score) {
        return score != null ? score.equals("F") ? 0 : Integer.parseInt(score) : 0;
    }
}
