package com.fjh.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fjh.common.utils.TimeUtil;
import com.fjh.modules.sys.dao.RegisterDao;
import com.fjh.modules.sys.entity.UsersEntity;
import com.fjh.modules.sys.service.AsyncService;
import com.fjh.modules.sys.service.RedisService;
import com.fjh.modules.sys.service.RegisterService;
import com.fjh.modules.sys.shiro.ShiroMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @Author: fjh
 * @ProjectName: adminsystem
 * @Package: com.fjh.modules.sys.service.impl
 * @Description:
 * @Date: 2019/9/9 0009 17:32
 **/
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterDao registerDao;

    @Autowired
    private AsyncService asyncService;

    @Autowired
    private RedisService redisService;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int insUsers(UsersEntity users) {
        String id = UUID.randomUUID().toString();
        users.setId(id);
        users.setRoleId(2);
        Object o = ShiroMD5.MD5(users.getPhone(), users.getPassword());
        users.setPassword(String.valueOf(o));
        String data = new TimeUtil().getFormatDateForThree();
        users.setLastTime(data);
        // 手机号和用户名存入缓存
        redisService.SavePhoneAndUsername(users.getPhone(), users.getUsername());
        return registerDao.insert(users);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int findByPhone(String phone) {
        int phoneNum = registerDao.selectCount(new QueryWrapper<UsersEntity>().eq("phone", phone));
        // 异步把数据库中的手机号存入缓存
        asyncService.insUserPhone();
        return phoneNum;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int findByEmail(String email) {
        int emailNum = registerDao.selectCount(new QueryWrapper<UsersEntity>().eq("email", email));
        return emailNum;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int findByUsername(String username) {
        int name = registerDao.selectCount(new QueryWrapper<UsersEntity>().eq("username", username));
        // 异步把数据库中的用户名存入缓存
        asyncService.insUserName();
        return name;
    }



}
