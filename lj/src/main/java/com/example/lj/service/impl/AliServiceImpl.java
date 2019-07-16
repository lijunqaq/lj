package com.example.lj.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.BucketInfo;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class AliServiceImpl {

	String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
	// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
	String accessKeyId = "LTAIyCTTGgCKmb09";
	String accessKeySecret = "YFBD6QUb7Q4MJe1CrcUmcAxiDQxM7l";
	private static String bucketName = "lijunqaq";
	private static String firstKey = "my-first-key";
	// 创建OSSClient实例。
	OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);




	public  void getUptoken(byte[] data, String filename) {



		BucketInfo info = ossClient.getBucketInfo(bucketName);
		System.out.println("Bucket " + bucketName + "的信息如下：");
		System.out.println("\t数据中心：" + info.getBucket().getLocation());
		System.out.println("\t创建时间：" + info.getBucket().getCreationDate());
		System.out.println("\t用户标志：" + info.getBucket().getOwner());

		ossClient.putObject(bucketName, "filename",new ByteArrayInputStream(data));

		// 关闭OSSClient。
		ossClient.shutdown();

	}


	
	
	
	
}
