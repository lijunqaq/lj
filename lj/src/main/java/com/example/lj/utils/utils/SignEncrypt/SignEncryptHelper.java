package com.example.lj.utils.utils.SignEncrypt;

import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gongdejun on 2017/11/10.
 */
public class SignEncryptHelper {
    /**
     * MD5Key
     */
    private static final String privateKey = "9FC4622CCDE74A87D05824C7E7CADD3BQA";


    /**
     * 返回加签的Json数据
     *
     * @param jsonParam
     * @return
     */
    public static String signEncryptJson(String jsonParam) {
        // json传map
        Map<String, Object> mapStr = jsonToDic(jsonParam);
        // 所有参数值加密生成sign,去掉空值
        String sigMd5 = VirtualCapitalSignHelper.signMd5(mapStr, privateKey);
        if (!mapStr.containsKey("sign_type")) {
            mapStr.put("sign_type", sigMd5);
        }

        JSONObject object = JSONObject.fromObject(mapStr);
        return object.toString();
    }

    /**
     * 返回加签的Json数据
     *
     * @param urlParam
     * @return
     */
    public static String signEncryptUrl(String urlParam) {
        // json转map
        Map<String, Object> mapStr = jsonToDic(urlParam);
        // 所有参数值加密生成sign,去掉空值
        String sigMd5 = VirtualCapitalSignHelper.signMd5(mapStr, privateKey);
        mapStr.put("sign_type", sigMd5);

        JSONObject object = JSONObject.fromObject(mapStr);
        return object.toString();
    }


    /**
     * json传map
     *
     * @param jsonParam
     * @return
     */
    public static Map<String, Object> jsonToDic(String jsonParam) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!jsonParam.isEmpty()) {
            try {
                //转json->Object
                map = (Map<String, Object>) JSONObject.fromObject(jsonParam);

            } catch (Exception e) {
                map = ParamConvertHelper.getParamDic(jsonParam);
            }
        }

        if (!map.containsKey("Timestamp")) {
            map.put("Timestamp", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        }
        return map;
    }
}
