package com.yizhu.lj.utils.utils.SignEncrypt;

import net.sf.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by gongdejun on 2017/11/10.
 */
public class VirtualCapitalSignHelper {

    /**
     * 签名名称
     */
    private static String signTypeName = "sign_type";

    /**
     * 签名key
     */
    private static String key = "123";

    /**
     * ceshi
     *
     * @param jsonParam
     * @param privateKey
     * @return
     */
    public static String capitalSignEncrypt(String jsonParam, String privateKey) {
        Map<String, Object> map = (Map<String, Object>) JSONObject.fromObject(jsonParam);
        String signMd5 = signMd5(map, privateKey);
        if (map.containsKey("Capital_sign_type")) {
            map.remove("Capital_sign_type");
        }
        map.put("Capital_sign_type", signMd5);
        JSONObject object = JSONObject.fromObject(map);
        String strJson = object.toString();
        return strJson;
    }

    //对字典里数据加密.

    public static String signMd5(Map<String, Object> map, String key) {
        // 1、字典参数排序
        String sign = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SortedMap<String, Object> sortedMap = filterPara(map);
        String strBuild = new String();
        for (Map.Entry<String, Object> entry : sortedMap.entrySet()) {
            // 2、排除掉sign_type 且值不能为空
            if (!entry.getKey().toLowerCase().equals(signTypeName.toLowerCase()) && entry.getValue() != null && !entry.getValue().toString().isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // 3、对值进行Base64编码
                String value = entry.getValue().toString().replace("\r\n", "");
                value = value.replaceAll(" ", "");
                value = value.replace(".0", "");
                try {
                    byte[] dataBytes = value.getBytes("UTF-8");
                    String strBase64 = Base64.getEncoder().encodeToString(dataBytes);
                    // 4、所有的Value值与MD5 Key值相加
                    strBuild += strBase64;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            // 4、所有的Value值与MD5 Key值相加
            strBuild += key;
            String sginStr = strBuild.toString();
            sign = MD5UtilHelper.getMD5(sginStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sign;
    }

    /**
     * 验签操作
     *
     * @param jsonParam 参数
     * @return
     */
    public static Boolean signValidate(String jsonParam) {
        Map<String, Object> map = (Map<String, Object>) JSONObject.fromObject(jsonParam);
        if (map.get("sign_type") == null) {
            return false;
        }
        String sign_type = map.get("sign_type").toString();
        Map<String, Object> convertDic = new HashMap<>();
        String signMd5 = signMd5(map, "9FC4622CCDE74A87D05824C7E7CADD3BQA");

        if (sign_type.equals(signMd5)) {
            return true;
        }
        return false;
    }

    /**
     * 除去数组中的空值和签名参数并以字母a到z的顺序排序
     *
     * @param dicArrayPre
     * @return
     */
    private static SortedMap<String, Object> filterPara(Map<String, Object> dicArrayPre) {
        SortedMap<String, Object> marArray = new TreeMap<String, Object>();
        for (Map.Entry<String, Object> entry : dicArrayPre.entrySet()) {
            if (!entry.getKey().toLowerCase().equals(signTypeName.toLowerCase()) && entry.getValue() != null) {
                marArray.put(entry.getKey().toLowerCase(), entry.getValue());
            }
        }
        return marArray;
    }
}
