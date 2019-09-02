package com.yizhu.lj.utils.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by chenqibiao on 2018/5/10.
 */
public class PrimaryKeyHandler {
    /**
     * 生成主键
     *
     * @return 返回值
     */
    public static String getKeyIDByUID() {
        String keyID = new String();
        // 时间戳
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyMM");
        keyID = df.format(day);
        // 4位随机数
        keyID = keyID + "-" + UUID.randomUUID().toString();

        return keyID + "K";
    }

    /**
     * 获取表名
     *
     * @param tableName 表的基础名字
     * @param keyID     查询数据得主键
     * @return 返回表全名字
     */
    public static String getTableName(String tableName, String keyID) {
        StringBuilder sb = new StringBuilder();
        sb.append(tableName);
        // 哥不考虑百年之后的事情，所以，固定20
        sb.append(20);
        // 获取当前年月
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyMM");
        String nowTable = df.format(day);
        if (SystemParaUtil.isEmpty(keyID)) {
            sb.append(nowTable);
            return sb.toString();
        }
        if (keyID.length() >= 4) {
            try {
                int start = 1701;
                int end = Integer.parseInt(nowTable);
                int keyIDYearMonth = Integer.parseInt(keyID.substring(0, 4));
                int keyIDMonth = Integer.parseInt(keyID.substring(2, 4));
                if (keyIDYearMonth >= start && start <= end && keyIDMonth >= 1 && keyIDMonth <= 12) {
                    sb.append(keyID.substring(0, 4));
                } else {
                    sb.append(nowTable);
                }
            } catch (Exception ex) {
                sb.append(nowTable);
            }
        }
        return sb.toString();
    }


    /**
     * 生生成
     *
     * @return 返回值
     */
    public static String getKeyID(String partnerType) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String keyID = new String();
        // 时间戳
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmssSSS");
        keyID = df.format(day);
        // 业务线 火车票002
        String serviceCode = "002";
        // 4位随机数
        int r = (int) ((Math.random() * 9 + 1) * 1000);
        if(partnerType.equals("03")) {
            ///空铁
            keyID = keyID + serviceCode + r + "K";
        }
        else {
            ///差客
            keyID = keyID + serviceCode + r + "C";
        }
        return keyID;
    }
}
