package com.example.lj.utils.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URLEncoder;

/**
 * 拓展方法
 * 提供些常用的方法
 * Created by chenqibiao on 2018/5/10.
 */
public class SystemParaUtil {

    /**
     * 合作商类型 01:差客E族 02:人达E家 03:空铁逸行
     *
     * @param partnerType
     * @return
     */
    public static String getPartnerTypeName(String partnerType) {
        switch (partnerType) {
            case "01":
                return "差客E族";
            case "02":
                return "人达E家";
            case "03":
                return "空铁逸行";
            default:
                return partnerType;
        }
    }

    /**
     * 判断是否是空字符串 null和"" 都返回 true
     *
     * @param str 判断的字符串
     * @return 是否有效
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().equals("");
    }

    /**
     * 判断一个对象是否为空
     */
    public final static boolean isNull(Object obj) {
        return (null == obj);
    }

    /**
     * Get请求封装
     * @param url
     * @param obj
     * @param channelID
     * @return
     */
    public static String Get(String url, Object obj, int channelID) {
        String param = "";
        try {
            param = URLEncoder.encode(new Gson().toJson(obj), "utf-8");
        } catch (Exception ex) {
        }
        Gson gson = new GsonBuilder().create();
        String trainJson = HttpHelper.requestGetAPI(url);
        trainJson = gson.fromJson(trainJson, String.class);
        return trainJson;
    }
}
