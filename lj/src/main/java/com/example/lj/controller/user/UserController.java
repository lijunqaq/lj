package com.example.lj.controller.user;


import com.example.lj.dao.entity.User;
import com.example.lj.dao.mapper.UserMapper;
import com.example.lj.dto.Page;
import com.example.lj.service.UserService;
import com.example.lj.service.impl.QiNiuServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 〈user控制器〉
 *
 * @author coach tam
 * @email 1077@qq.com
 * @create 2018/8/9
 * @since 1.0.0
 * 〈坚持灵活 灵活坚持〉
 */


@RestController
@RequestMapping("/v1/system/user")
@Api(tags = "用户",description = "用户相关api接口")
public class UserController {

    @Autowired
    private UserMapper uService;

    @Autowired
    private QiNiuServiceImpl qiniuService;

    @Autowired
    private UserService userService;

    //获取用户权限
    @GetMapping("/info")
    public List<String> getInfo(){
        //得到权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        ArrayList<String> result = new ArrayList<>();
        authorities.forEach(authority->{
            String authority1 = authority.getAuthority();
            result.add(authority1);
        });
        return result;
    }
    /**
     * 查询所有用户
     * @param
     * @param
     * @return
     */
    @GetMapping("/all")
    public List<User> findAll(){
        List<User> result=null;
        result=uService.selectUserList();
        return result;
    }
    /**
     *  分页查询
     * @param pageSize
     * @param pageNo
     * @return Pagination
     */
    @GetMapping
    public Page<List<User>> page(Integer pageSize, Integer pageNo, String keyWord){
        Page<List<User>> result = userService.findUserByPage(pageSize,pageNo,keyWord);

        return result;
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping
    public String  addUser(@RequestBody User user){

        boolean res=userService.addUser(user);
        if(res){
            return "ok";
        }
        return "false";
    }
    /**
     * @author lj
     * @date
     * @param
     * @return
     */
    @GetMapping("/checkUsername/{username}")
    public String checkUsername(@PathVariable("username") String username){
        User user = userService.findByUsername(username);
        return user==null?"0":"1";
    }
    @GetMapping("/txt")
    public void txt( HttpServletResponse res) throws IOException {
        StringBuffer text = new StringBuffer();
        text.append("hahha").append("2222");
        exportTxt(res,text.toString());

    }
    /* 导出txt文件
     * @author
     * @param	response
     * @param	text 导出的字符串
     * @return
     */
    public void exportTxt(HttpServletResponse response,String text){
        response.setCharacterEncoding("utf-8");
        //设置响应的内容类型
        response.setContentType("text/plain");
        //设置文件的名称和格式
        response.addHeader("Content-Disposition","attachment;filename="
                + genAttachmentFileName( "文件名称", "JSON_FOR_UCC_")//设置名称格式，没有这个中文名称无法显示
                + ".txt");
        System.out.println(genAttachmentFileName( "文件名称", "JSON_FOR_UCC_")//设置名称格式，没有这个中文名称无法显示
                + ".txt");
        BufferedOutputStream buff = null;
        ServletOutputStream outStr = null;
        try {
            outStr = response.getOutputStream();
            buff = new BufferedOutputStream(outStr);
            buff.write(text.getBytes("UTF-8"));
            buff.flush();
            buff.close();
        } catch (Exception e) {
            //LOGGER.error("导出文件文件出错:{}",e);
        } finally {try {
            buff.close();
            outStr.close();
        } catch (Exception e) {
            //LOGGER.error("关闭流对象出错 e:{}",e);
        }
        }
    }

    public  String genAttachmentFileName(String cnName, String defaultName) {
        try {
            cnName = new String(cnName.getBytes("gb2312"), "ISO8859-1");
        } catch (Exception e) {
            cnName = defaultName;
        }
        return cnName;
    }


}
