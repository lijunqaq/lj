package com.yizhu.lj.config.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.web.cors.CorsUtils;

/**
 * 资源服务器
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "my_rest_api";



    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.
//                //关闭以下资源匿名访问
//                anonymous().disable()
//                .requestMatchers().antMatchers("/*/**")
//
//                //授权后可以访问以下资源
//                .and()
//                .authorizeRequests()
//                .antMatchers("/*/**").permitAll()
//                .and()
//                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());


        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //资源服务器拦截的路径  对于我们的系统来说,用户角色功能系统为资源,请求这些资源时拦截
                //这些资源请求路径统一用 /resource 开头 这里就拦截以这个开头路径
                .requestMatchers().antMatchers("/v1/**")
                .and()
                .authorizeRequests().anyRequest().authenticated()
//			.authorizeRequests().anyRequest()
//			.access("authenticated and @userRelationService.hasPermission(request)")
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .and()
                .cors()
                .and()
                .csrf().disable();
        http.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());

    }

}
