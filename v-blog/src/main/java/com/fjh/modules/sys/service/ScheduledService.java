package com.fjh.modules.sys.service;

/**
 * @Author: fjh
 * @ProjectName: adminsystem
 * @Package: com.fjh.modules.sys.service
 * @Description: 定时任务存储信息
 * @Date: 2019/9/9 0009 16:31
 **/
public interface ScheduledService {

    /**
     * 网站浏览次数存入数据库
     */
    public void visitorCustom();

    /**
     * 博客访问量
     */
    public void lookBlog();

}
