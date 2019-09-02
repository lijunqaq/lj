package com.yizhu.lj.utils.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by dengxia on 2018/5/9.
 */
public class HttpHelper {
    /**
     * Post请求API
     *
     * @param url    地址
     * @param params 参数
     * @return
     */
    public static String requestPostAPI(String url, StringEntity params) {
        String result = "";

        try {
            HttpClient httpClient = HttpClientBuilder.create().build();

            HttpPost requests = new HttpPost(url);
            requests.addHeader("content-type", "application/json");
            requests.addHeader("charset", "utf-8");
            requests.setEntity(params);
            HttpResponse response = httpClient.execute(requests);
            HttpEntity httpEntity = response.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (UnsupportedEncodingException e) {
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }

        return result;
    }

    /**
     * Get请求API
     *
     * @param url 地址
     * @return
     */
    public static String requestGetAPI(String url) {
        String result = "";
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet requests = new HttpGet(url);
            HttpResponse response = httpClient.execute(requests);
            HttpEntity httpEntity = response.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (UnsupportedEncodingException e) {
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }

        return result;
    }
}
