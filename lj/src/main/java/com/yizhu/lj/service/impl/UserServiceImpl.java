package com.yizhu.lj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yizhu.lj.dao.entity.*;
import com.yizhu.lj.dao.mapper.*;
import com.yizhu.lj.dto.Page;
import com.yizhu.lj.service.UserService;
import com.yizhu.lj.utils.Md5Helper;
import com.yizhu.lj.utils.PrimaryKeyHandler;
import com.yizhu.lj.utils.SmsUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述
 *
 * @author lijun
 * @date 2019/2/1
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleModuleMapper roleModuleMapper;
    @Autowired
    private ModuleMapper moduleMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private RoleUserMapper roleUserMapper;

    //线程池
    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

    @Override
    public User findByUsername(String username) {
        User user = userMapper.findByUsername(username);
        if (user != null) {
            RoleUser roleUser = roleUserMapper.selectByUserID(user.getKeyID());
            List<RoleModule> roleModules = roleModuleMapper.selectByRole(roleUser.getRoleID());
            Role role1 = roleMapper.selectByPrimaryKey(roleUser.getRoleID());
            for (RoleModule role : roleModules) {
                Module module = moduleMapper.selectByPrimaryKey(role.getModuleID());
                role1.getModules().add(module);
            }
            user.getRoles().add(role1);
            return user;
        } else {
            return null;
        }

    }

    /**
     * 分页查询用户
     *
     * @param pageSize
     * @param pageNo
     * @param keyWord
     * @return
     */
    @Override
    public Page<List<User>> findUserByPage(Integer pageSize, Integer pageNo, String keyWord) {
        Page<List<User>> result = new Page<>();
        PageHelper.startPage(pageNo, pageSize);
        List<User> users = userMapper.selectUserList();
        for (User user : users) {
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(user.getKeyID());
            user.setName(userInfo.getName());
            user.setSex(userInfo.getSex());
            user.setBirthday(userInfo.getBirthday());
            user.setSalary(userInfo.getSalary());
            user.setTelephone(userInfo.getTelephone());
            user.setStation(userInfo.getStation());
            user.setEmail(userInfo.getEmail());
            user.setManagerID(userInfo.getManagerID());
            user.setPicture(userInfo.getPicture());
            Dept dept = deptMapper.selectByPrimaryKey(user.getDeptID());
            user.setDeptName(dept.getDeptName());

        }
        PageInfo<User> usersPageInfo = new PageInfo<>(users);
        result.setDataList(users);
        result.setPageNo(pageNo);
        result.setPageSize(pageSize);
        result.setTotalCount((int) usersPageInfo.getTotal());
        return result;
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUser(User user) {
        boolean res = saveUser(user);
        if(!res){
            return false;
        }
        //发送短信
        try {
            SmsUtils.sendSms(user.getUserName(), "小猪佩奇身上纹", user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * 保存用户信息
     *
     * @param user
     * @return
     */
    private boolean saveUser(User user) {
        //转成密文密码
        String mwpassword = Md5Helper.MD5("111111");
        //创建用户信息
        user.setCreateBy("伍六七");
        user.setCreateTime(new Date());
        user.setUpdateBy("伍六七");
        user.setUpdateTime(new Date());
        user.setPawwWord(mwpassword);
        user.setKeyID(PrimaryKeyHandler.getKeyIDByUID());

        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user, userInfo);
        userInfo.setAddBy("伍六七");
        userInfo.setAddTime(new Date());
        userInfo.setModifyBy("伍六七");
        userInfo.setModifyTime(new Date());
        userInfo.setUserID(user.getKeyID());
        boolean res = userMapper.insertSelective(user) > 0;
        boolean ret = userInfoMapper.insertSelective(userInfo) > 0;
        if (res && ret) {
            return true;
        }
        return false;
    }
}
