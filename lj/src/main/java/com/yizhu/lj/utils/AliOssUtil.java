package com.yizhu.lj.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;

/**
 * 功能描述
 *
 * @author Lj
 * @date 2019/8/16
 */
public class AliOssUtil {

    // Endpoint以杭州为例，其它Region请按实际情况填写。
    static String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    static String accessKeyId = "LTAIyCTTGgCKmb09";
    static String accessKeySecret = "YFBD6QUb7Q4MJe1CrcUmcAxiDQxM7l";
    static String bucketName = "lijunqaq";
    static String objectName = "img/";


    public static void upload(String context) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(context.getBytes()));

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    public static void uploadByte(FileInputStream inputStream, String fileName) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/jpeg");
        // 上传Byte数组。
        // 上传到OSS
        ossClient.putObject(bucketName,fileName, inputStream,metadata);
       // ossClient.putObject("<yourBucketName>", "<yourObjectName>", new ByteArrayInputStream(content));

        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
