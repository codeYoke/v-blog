package com.fjh.modules.sys.service;

import com.fjh.modules.sys.entity.UsersEntity;

/**
 * @Author: fjh
 * @ProjectName: adminsystem
 * @Package: com.fjh.modules.sys.service
 * @Description:
 * @Date: 2019/9/9 0009 17:30
 **/
public interface RegisterService {


    /**
     * 注册
     * @param users
     * @return
     */
    public int insUsers(UsersEntity users);

    /**
     * 手机号检测
     * @param phone
     * @return
     */
    public int findByPhone(String phone);
    /**
     * 邮箱号检测
     * @param email
     * @return
     */
    public int findByEmail(String email);

    /**
     * 用户名检测
     * @param username
     * @return
     */
    public int findByUsername(String username);

}
