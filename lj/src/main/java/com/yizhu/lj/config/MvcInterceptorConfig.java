//package com.yizhu.lj.config;
//
//import com.yizhu.lj.filter.TimeLogInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
///**
// * 拦截器配置
// *
// * @author Lj
// * @date 2019/8/29
// */
//@Configuration
//public class MvcInterceptorConfig  extends WebMvcConfigurationSupport {
//    @Autowired
//    private TimeLogInterceptor timeLogInterceptor;
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        // 多个拦截器组成一个拦截器链
//        // addPathPatterns 用于添加拦截规则，/**表示拦截所有请求
//        // excludePathPatterns 用户排除拦截
//        registry.addInterceptor(timeLogInterceptor).addPathPatterns("/**");
//                //.excludePathPatterns("/stuInfo/getAllStuInfoA","/account/register");
//        super.addInterceptors(registry);
//    }
////    @Override
////    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        registry.addResourceHandler("swagger-ui.html")
////                .addResourceLocations("classpath:/META-INF/resources/");
////        registry.addResourceHandler("/webjars/**")
////                .addResourceLocations("classpath:/META-INF/resources/webjars/");
////
////    }
//}
