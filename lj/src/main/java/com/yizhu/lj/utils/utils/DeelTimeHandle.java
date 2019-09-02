package com.yizhu.lj.utils.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenqibiao on 2018/5/10.
 */
public class DeelTimeHandle {

    /**
     * @param timeStr
     * @return
     */
    public static Date deelTimeFomart(String timeStr) {
        Date res = new Date();

        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(timeStr);

        } catch (Exception ex) {
        }

        return res;
    }

    /**
     * @param time
     * @param formartStr
     * @return
     */
    public static String deelTimeFomart(Date time, String formartStr) {
        String res = "";

        try {
            SimpleDateFormat f = new SimpleDateFormat(formartStr);
            return f.format(time);

        } catch (Exception ex) {
        }

        return res;
    }
}
