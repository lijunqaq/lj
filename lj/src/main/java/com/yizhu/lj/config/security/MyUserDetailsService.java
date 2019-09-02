package com.yizhu.lj.config.security;

import com.yizhu.lj.dao.entity.Module;
import com.yizhu.lj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
    * 〈用于提供给spring security访问数据库〉
    *
    * @author coach tam
    * @create 2018/6/15
    * @since 1.0.0
    * 〈坚持灵活 灵活坚持〉
*/
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1,根据用户名到数据库查询到user-->password
           com.yizhu.lj.dao.entity.User user = userService.findByUsername(username);
        //2,获取当前用户所拥有权限(模块)
        Set<Module> moduleSet = new HashSet<>(0);
        user.getRoles().forEach(role -> {
            //获取到当前角色的所有模块
            moduleSet.addAll(role.getModules());
        });

        Set<SimpleGrantedAuthority> authorities = new HashSet<>(moduleSet.size());

        moduleSet.forEach(module -> {
        //3,将 module -> authority
            SimpleGrantedAuthority authorityGranter = new SimpleGrantedAuthority(module.getCpermission());
            authorities.add(authorityGranter);
        });
        return new User(username,user.getPawwWord(),authorities);
    }
}
