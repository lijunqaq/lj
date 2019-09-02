package com.yizhu.lj.dao.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yizhu.lj.dao.entity.RoleUser;
import org.apache.ibatis.annotations.Param;

public interface RoleUserMapper extends BaseMapper<RoleUser> {
    int deleteByPrimaryKey(@Param("userID") String userID, @Param("roleID") String roleID);

    //int insert(RoleUser record);

    int insertSelective(RoleUser record);

    RoleUser selectByUserID(String userID);
}