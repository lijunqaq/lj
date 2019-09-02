package com.yizhu.lj.utils;

import com.yizhu.lj.romote.InvokeException;
import com.yizhu.lj.romote.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Configuration
@Slf4j
public class RestTemplateInsiUtils {
	
	@Value("${jq.path}")
	private String path;
	
	@Autowired
	private RestTemplate restTemplate;
	
    
    @Retryable(value = Exception.class,maxAttempts = 3)
    public <T> ResponseData<T> invokeRestTemplate(String url, Object requestParams, Class<T> clazz, HttpMethod httpMethod){
    	log.info("===============请求家签url:{}",url);
    	ResponseData<T> r;
    	HttpHeaders requestHeaders = new HttpHeaders();
//    	requestHeaders.add("Authorization", JwtUtils.getJwtTokenNoUserId());
    	try { 
    		r = restTemplate.exchange(path.trim()+url.trim(),
    				httpMethod, new HttpEntity<>(requestParams,requestHeaders),
    				new ParameterizedTypeReference<ResponseData<T>>() {}).getBody();
		} catch (Exception e) {
			throw new InvokeException("访问家签系统接口失败(接口没调通)!",e);
		}
    	if(!"SUCCESS".equals(r.getCode())) {
    		throw new InvokeException("调用家签接口成功，但返回信息异常，异常信息为:"+r.getMsg()+"===============");
    	}
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
    	mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
    	r.setData(mapper.convertValue(r.getData(), clazz));
    	return r;
    }

    @Retryable(value = Exception.class,maxAttempts = 3)
    public <T> ResponseData<T> invokeRestTemplateList(String url, Object requestParams, Class<T> clazz, HttpMethod httpMethod){
    	log.info("===============请求家签url:{}",url);
    	ResponseData<T> r;
    	HttpHeaders requestHeaders = new HttpHeaders();
//    	requestHeaders.add("Authorization", JwtUtils.getJwtTokenNoUserId());
    	try {
    		r = restTemplate.exchange(path.trim()+url.trim(),
    				httpMethod, new HttpEntity<>(requestParams,requestHeaders),
    				new ParameterizedTypeReference<ResponseData<T>>() {}).getBody();
		} catch (Exception e) {
			throw new InvokeException("访问家签系统接口失败(接口没调通)!",e);
		}
    	if(!"SUCCESS".equals(r.getCode())) {
    		throw new InvokeException("调用家签接口成功，但返回信息异常，异常信息为:"+r.getMsg()+"===============");
    	}
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
    	mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
    	List list = (List) r.getData();
    	List<T> list_temp = new ArrayList<>();
    	if(CollectionUtils.isEmpty(list)) {
    		r.setDataList(list_temp);
		} else {
			for(int i = 0;i<list.size();i++) {
				T t = mapper.convertValue(list.get(i), clazz);
				list_temp.add(t);
			}
			r.setDataList(list_temp);
		}
    	return r;
    }

    public <T> ResponseData<T> invokeRestTemplateUrlEncoded(String url, Class<T> clazz, HttpMethod httpMethod) {
		log.info("===============请求家签url:{}",url);
		ResponseData<T> r;
		HttpHeaders requestHeaders = new HttpHeaders();
		try {
			r = restTemplate.exchange(path.trim() + url.trim(),
					httpMethod, new HttpEntity<>(requestHeaders),
					new ParameterizedTypeReference<ResponseData<T>>() {}).getBody();
		} catch (Exception e) {
			throw new InvokeException("访问家签系统接口失败(接口没调通)!",e);
		}
		if(!"SUCCESS".equals(r.getCode())) {
			throw new InvokeException("调用家签接口成功，但返回信息异常，异常信息为:"+r.getMsg()+"===============");
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
		mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
		r.setData(mapper.convertValue(r.getData(), clazz));
		return r;
	}
    
    
	@Recover
    public void recover(Exception e) {
		System.out.println("处理异常");

    }

}
