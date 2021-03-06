package com.fjh.modules.sys.service.impl;

import com.fjh.common.utils.Constant;
import com.fjh.common.utils.RedisOperator;
import com.fjh.modules.sys.entity.VO.BlogMessageVOEntity;
import com.fjh.modules.sys.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: fjh
 * @ProjectName: adminsystem
 * @Package: com.fjh.modules.sys.service.impl
 * @Description:
 * @Date: 2019/9/17 0017 12:16
 **/
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisOperator redisOperator;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void SavePhoneAndUsername(String phone, String username) {
        // 手机号加入缓存
        redisOperator.hset(Constant.USER_PHONE_EXIST, phone, 1);
        // 用户名加入缓存
        redisOperator.hset(Constant.USER_NAME_EXIST, username, 1);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void SaveEditBlog(BlogMessageVOEntity blogMessageVOEntity) {
        // 存入缓存中(首页分页查询)
        redisOperator.lpush(Constant.PAGE_BLOG, blogMessageVOEntity);
        // 存入缓存（博客具体详情）
        redisOperator.hset(Constant.BLOG_DETAIL, String.valueOf(blogMessageVOEntity.getId()), blogMessageVOEntity);
        // 文章浏览次数
        redisOperator.set(Constant.BLOG_DETAIL + blogMessageVOEntity.getId(), 0);
    }
}
