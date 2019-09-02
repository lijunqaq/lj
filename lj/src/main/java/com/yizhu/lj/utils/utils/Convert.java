package com.yizhu.lj.utils.utils;

import java.math.BigDecimal;

/**
 * 转转转
 * Created by chenqibiao on 2018/5/10.
 */
public class Convert {

    /**
     * String Convert to int
     *
     * @param target
     * @return
     */
    public static int ToInt(String target) {
        if (target == null || target.trim().length() == 0) {
            return 0;
        }
        return Integer.parseInt(target);
    }

    /**
     * String Convert to BigDecimal
     *
     * @param target
     * @return
     */
    public static BigDecimal ToDecimal(String target) {
        if (target == null || target.trim().length() == 0) {
            return new BigDecimal(0);
        }
        // 构造以字符串内容为值的BigDecimal类型的变量bd
        BigDecimal bd = new BigDecimal(target);
        // 设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd;
    }

}
