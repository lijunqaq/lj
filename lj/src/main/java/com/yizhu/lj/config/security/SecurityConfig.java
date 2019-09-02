
package com.yizhu.lj.config.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


/**
 * @author coach tam
 * @create 2018/4/11
 * @since 1.0.0
 * 〈坚持灵活 灵活坚持〉
 */

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(-1)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private MyUserDetailsService userDetailsService;



/**
     * 设置获取token的url
     *
     * @param http
     * @throws Exception
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                //允许以下请求方式
                requestMatchers().antMatchers(HttpMethod.OPTIONS, "/oauth/*")
                .and().requestMatchers().antMatchers(HttpMethod.OPTIONS, "/img/*").and()
                .requestMatchers().antMatchers(HttpMethod.GET, "/swagger-ui.html").and()
                .requestMatchers().antMatchers(HttpMethod.GET, "/webjars/*").and()

                //启用跨站请求伪造保护
                .csrf().disable();
    }


/**
     * 从数据库中查询用户
     *
     * @param auth
     * @throws Exception
     */

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }


/**
     * 密码加密器
     */


// @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


    //需要配置这个支持password模式 support password grant type
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


/**
     * 实例化一个TokenStore，他的实现是InMemoryTokenStore，会把OAuth授权的token保存在内存中
     *
     * @return
     */

    @Bean("tokenStore")
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }



}

