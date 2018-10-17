package com.aw.utils;

/**
 * Create by zhaoxianghui on 2018/10/17.
 */
public class NumberUtils {

    public static boolean isNumeric(String var) {
        try {
            Double.parseDouble(String.valueOf(var));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
