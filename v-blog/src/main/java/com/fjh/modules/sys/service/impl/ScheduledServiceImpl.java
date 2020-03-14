package com.fjh.modules.sys.service.impl;

import com.fjh.common.utils.Constant;
import com.fjh.common.utils.RedisOperator;
import com.fjh.modules.sys.dao.IndexDao;
import com.fjh.modules.sys.service.ScheduledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: fjh
 * @ProjectName: adminsystem
 * @Package: com.fjh.modules.sys.service.impl
 * @Description:
 * @Date: 2019/9/9 0009 16:35
 **/
@Service
public class ScheduledServiceImpl implements ScheduledService {

    @Autowired
    private IndexDao indexDao;

    @Autowired
    private RedisOperator redisOperator;

    /**
     * second(秒),minute(分),hour(时)，day of month(日),month(月),day of week(周几)
     * 0 * * * * MON-FRI
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void visitorCustom() {
        indexDao.updWebVisitorCount((int)redisOperator.get(Constant.BLOG_VISIT_COUNT));
    }

    @Scheduled(cron = "0 5 * * * ?")
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void lookBlog() {

    }
}
