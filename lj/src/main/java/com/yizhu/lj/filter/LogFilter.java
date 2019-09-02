package com.yizhu.lj.filter;


import com.yizhu.lj.dao.entity.LoginLog;
import com.yizhu.lj.service.LoginLogService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 〈登陆记录〉
 *
 * @author coach tam
 * @email 1077@qq.com
 * @create 2018/8/28
 * @since 1.0.0
 * 〈坚持灵活 灵活坚持〉
 */
@Component
@WebFilter(filterName = "myFilter", urlPatterns = "/*")
public class LogFilter implements Filter {


    private LoginLogService loginLogService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //在过滤器调用 logservice
        ServletContext servletContext = filterConfig.getServletContext();
        WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        if (cxt!=null && cxt.getBean(LoginLogService.class)!=null && loginLogService ==null) {
            loginLogService = (LoginLogService) cxt.getBean(LoginLogService.class);
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;

        if ("OPTIONS".equals(request.getMethod())){
            //创建日志实体
            LoginLog loginLog = new LoginLog();
            //设置登陆ip
            String loginIp = request.getRemoteAddr();
            System.out.println(loginIp);
            loginLog.setIpAdress(loginIp);
            //获取登陆用户名
            String username = request.getParameter("username");
            System.out.println(username);

            loginLog.setLoginName(username);
            loginLog.setLoginTime(new Date());
            loginLogService.saveLog(loginLog);
        }



        filterChain.doFilter(request,response);


    }

    @Override
    public void destroy() {

    }

}
