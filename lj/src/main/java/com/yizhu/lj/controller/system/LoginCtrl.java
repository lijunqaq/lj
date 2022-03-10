package com.yizhu.lj.controller.system;



import cn.hutool.extra.mail.MailUtil;
import com.yizhu.lj.utils.SendMailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
/**
 * 〈〉
 *
 * @author coach tam
 * @email 1077@qq.com
 * @create 2018/8/15
 * @since 1.0.0
 * 〈坚持灵活 灵活坚持〉
 */

@RestController
@RequestMapping("/login")
public class LoginCtrl {
    @Autowired
    @Qualifier("consumerTokenServices")
    private ConsumerTokenServices consumerTokenServices;
    @PostMapping("/logout")
    public  String logout(@RequestParam("access_token") String access_token, HttpServletRequest req, HttpServletResponse res){
        //清空token
        consumerTokenServices.revokeToken(access_token);
        //清空 spring security 认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            new SecurityContextLogoutHandler().logout(req,res,authentication);
        }
        return  "ok";
    }

    @GetMapping("/email")
    public void email(){
        SendMailUtils.sendMessage("1077387582@qq.com","你好","哈哈哈");
    }
}

