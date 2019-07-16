package com.example.lj.filter;

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
 * @author Wang Jialiang
 * @version 1.0.0
 * @date 2017/11/13
 * @see
 * @since
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

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String handlerName = handlerMethod.getBean().getClass().getSimpleName();
        String handlerMethodName = handlerMethod.getMethod().getName();

        Long start = (Long) request.getAttribute(TimeLogInterceptor.REQUEST_ATTR_KEY_TIME_START);

//        log.info("Interceptor postHandle 记录 {}.{}() 执行耗时: {}ms", handlerName, handlerMethodName, (System.currentTimeMillis() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //log.debug("{} afterCompletion");

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String handlerName = handlerMethod.getBean().getClass().getSimpleName();
        String handlerMethodName = handlerMethod.getMethod().getName();
        //
        String uri = request.getRequestURI();

        Long start = (Long) request.getAttribute(TimeLogInterceptor.REQUEST_ATTR_KEY_TIME_START);
        log.info("执行耗时记录: {}.{}() : {} ms; uri={}", handlerName, handlerMethodName, (System.currentTimeMillis() - start), uri);
    }
}
