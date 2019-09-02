package com.yizhu.lj.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class QiNiuServiceImpl {
	@Value("${qiniu.accessKey}")
    private static String accessKey;

	@Value("${qiniu.secretKey}")
    private static String secretKey;

	@Value("${qiniu.bucket}")
    private static String bucket;

	@Value("${qiniu.path}")
    private static String path;
	private static String upToken;
	public static void getUptoken() {
		
//			String accessKey = "NFZWipkIy4PA9YjJHMCd60BG96r0MuwoWRqKIbyZ";
//			String secretKey = "gjGeHYedC_fxR6FyOAzfkaFjHcFoSJNVlBr_m8S4";
//			String bucket = "lijun";
			Auth auth = Auth.create(accessKey, secretKey);
			String upToken = auth.uploadToken(bucket);
			
//			HashMap<String, String> hashMap = new HashMap<>();
//			hashMap.put("uptoken", upToken);
//		return hashMap;
	}

	// 创建上传对象
		
		public String uploadObject(byte[] data, String key) {
			try {
				// 构造一个带指定Zone对象的配置类
				/*
				 * 华东 Zone.zone0() 华北 Zone.zone1() 华南 Zone.zone2() 北美 Zone.zoneNa0()
				 */
				// Configuration cfg = new Configuration(Zone.zone2());
				Configuration cfg = new Configuration(Zone.autoZone());
				// 调用put方法上传
				UploadManager uploadManager = new UploadManager(cfg);
				Auth auth = Auth.create(accessKey, secretKey);
				String upToken = auth.uploadToken(bucket, key, 3600, new StringMap().put("insertOnly", 0));

				Response res = uploadManager.put(data, key, upToken);
				// 解析上传成功的结果
				DefaultPutRet putRet = JSONObject.parseObject(res.bodyString(), DefaultPutRet.class);
				return putRet.key;
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		}
	
	
	
	
}
