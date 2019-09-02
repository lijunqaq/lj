package com.yizhu.lj.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller耗时记录 拦截器
 *
 */
@Component
public class TimeLogInterceptor implements HandlerInterceptor {

    private final String clazz = getClass().getName();

    private final Logger log = LoggerFactory.getLogger(clazz);

    private static final String REQUEST_ATTR_KEY_TIME_START = "__TIME_START__";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(TimeLogInterceptor.REQUEST_ATTR_KEY_TIME_START, System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String uri = request.getRequestURI();
        Long start = (Long) request.getAttribute(TimeLogInterceptor.REQUEST_ATTR_KEY_TIME_START);

        //访问静态资源走else
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            String handlerName = handlerMethod.getBean().getClass().getSimpleName();
            String handlerMethodName = handlerMethod.getMethod().getName();
            log.info("执行耗时记录: {}.{}() : {} ms; uri={}", handlerName, handlerMethodName, (System.currentTimeMillis() - start), uri);
        }else{
            log.info("访问静态资源执行耗时记录:  : {} ms; uri={}", (System.currentTimeMillis() - start), uri);
        }
    }
}
