package com.fjh.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fjh.modules.sys.entity.UsersEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @Author: fjh
 * @ProjectName: adminsystem
 * @Package: com.fjh.modules.sys.dao
 * @Description:
 * @Date: 2019/9/18 0018 10:40
 **/
@Repository
public interface UsersDao extends BaseMapper<UsersEntity> {

    int updUserMess(UsersEntity users);

    @Update("update users set password = #{password} where phone = #{phone}")
    int updUserPwd(@Param("phone") String phone, @Param("password") String password);

}
