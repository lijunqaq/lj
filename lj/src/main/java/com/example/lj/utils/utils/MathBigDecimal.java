package com.example.lj.utils.utils;

import java.math.BigDecimal;

/**
 * @author baige
 * @date 2018/9/10 15:49
 */
public class MathBigDecimal {

    /**
     * 比较大小
     * v1大于v2 -> true
     * v1等于v2 -> true
     * v1小于v2 -> false
     *
     * @param v1
     * @param v2
     * @param v3
     * @return
     */

    public static boolean comparable(BigDecimal v1, BigDecimal v2, String v3) {
        switch (v3) {
            case ">":
                return v1.compareTo(v2) > 0;
            case "=":
                return v1.compareTo(v2) == 0;
            case "!=":
                return v1.compareTo(v2) != 0;
            case "<":
                return v1.compareTo(v2) < 0;
            case ">=":
                return v1.compareTo(v2) > 0 || v1.compareTo(v2) == 0;
            case "<=":
                return v1.compareTo(v2) < 0 || v1.compareTo(v2) == 0;
            default:
                return false;
        }
    }
}
