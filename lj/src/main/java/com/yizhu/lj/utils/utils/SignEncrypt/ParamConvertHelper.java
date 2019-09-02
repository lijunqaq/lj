package com.yizhu.lj.utils.utils.SignEncrypt;


import net.sf.json.JSONObject;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gongdejun on 2017/11/13.
 */
public class ParamConvertHelper {

    /**
     * 根据参数返回字典对象
     *
     * @param param
     * @return
     */
    public static Map<String, Object> getParamDic(String param) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] paramArr = param.split("&");
        for (int i = 0; i < paramArr.length; i++) {
            String item = paramArr[i];
            String[] value = item.split("=");
        }
        return map;
    }

    /**
     * JSON首字母小写转换
     *
     * @param jsonParam 参数
     * @return
     */
    public static String getJsonParamToLowerCase(String jsonParam) {
        Map<String, Object> map = (Map<String, Object>) JSONObject.fromObject(jsonParam);
        Map<String, Object> mapNow = new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String oneStr = entry.getKey().substring(0, 1);
            String otherStr = entry.getKey().substring(1, entry.getKey().length());
            String key = MessageFormat.format("{0}{1}", oneStr.toLowerCase(), otherStr);
            mapNow.put(key, entry.getValue());
        }
        JSONObject object = JSONObject.fromObject(mapNow);
        String strJson = object.toString();
        return strJson;
    }

    /**
     * 计算两个时间段相差 返回格式 XX时XX分
     *
     * @param beginDate 结束时间
     * @param endDate   开始时间
     * @return XX时XX分
     */
    public static String getDatePoor(String beginDate, String endDate) {

        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date begin = dfs.parse(beginDate);
            Date end = dfs.parse(endDate);
            // 除以1000是为了转换成秒
            long between = (end.getTime() - begin.getTime()) / 1000;
            long hours = between / (60 * 60);
            long minutes = (between / 60) % 60;
            return MessageFormat.format("{0}时{1}分", hours, minutes);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }
}
