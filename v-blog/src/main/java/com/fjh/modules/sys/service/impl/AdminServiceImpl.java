package com.fjh.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fjh.common.utils.Constant;
import com.fjh.common.utils.PagedResult;
import com.fjh.common.utils.RedisOperator;
import com.fjh.common.utils.TimeUtil;
import com.fjh.modules.sys.dao.BlogDao;
import com.fjh.modules.sys.dao.FriendurlDao;
import com.fjh.modules.sys.dao.NoticeDao;
import com.fjh.modules.sys.dao.UsersDao;
import com.fjh.modules.sys.entity.FriendurlEntity;
import com.fjh.modules.sys.entity.NoticeEntity;
import com.fjh.modules.sys.entity.UsersEntity;
import com.fjh.modules.sys.entity.VO.BlogMessageVOEntity;
import com.fjh.modules.sys.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: fjh
 * @ProjectName: adminsystem
 * @Package: com.fjh.modules.sys.service.impl
 * @Description: 管理员业务逻辑层
 * @Date: 2019/9/2 0002 18:00
 **/
@Service
public class AdminServiceImpl implements AdminService {



    @Autowired
    private UsersDao usersDao;

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private RedisOperator redisOperator;

    @Autowired
    private FriendurlDao friendurlDao;

    @Autowired
    private NoticeDao noticeDao;

//
//    @Autowired
//    private EsService esService;
//
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int deleteBlog(Long id) {
        // 删除缓存里东西
        redisOperator.lremove(Constant.PAGE_BLOG, 0, redisOperator.hget(Constant.BLOG_DETAIL, String.valueOf(id))); // 首页
        redisOperator.del(Constant.BLOG_DETAIL+id); // 文章浏览次数
        redisOperator.hdel(Constant.BLOG_DETAIL, String.valueOf(id)); // 详情
       // esService.removeEsBlog(id); // 搜索
      //  BlogMessageVO byId = adminMapper.findById(id);
        int i = blogDao.deleteById(id);
        return i;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int deleteUsers(String username) {
        int i = usersDao.delete(new QueryWrapper<UsersEntity>().eq("username", username));
        return i;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int delFriendUrl(Long id) {
        int i = friendurlDao.delete(new QueryWrapper<FriendurlEntity>().eq("id", id));
        return i;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult findAllBlogs(Integer pageSize, Integer pageNum) {
        QueryWrapper queryWrapper = new QueryWrapper<BlogMessageVOEntity>().orderByDesc("id");
        IPage<BlogMessageVOEntity> usersEmptyIPage = blogDao.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
        PagedResult grid = new PagedResult();
        grid.setPage(pageNum);
        grid.setTotal(usersEmptyIPage.getPages());
        grid.setRecords(usersEmptyIPage.getTotal());
        grid.setRows(usersEmptyIPage.getRecords());
        return grid;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int insFriendUrl(FriendurlEntity friendurlEntity) {
        TimeUtil timeUtil = new TimeUtil();
        friendurlEntity.setId(timeUtil.getLongTime());
        friendurlEntity.setCreateTime(timeUtil.getFormatDateForSix());
        return friendurlDao.insert(friendurlEntity);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int delNotice(Long id) {
        int i = noticeDao.delete(new QueryWrapper<NoticeEntity>().eq("id", id));
        return i;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int insNotice(NoticeEntity noticeEntity) {
        TimeUtil timeUtil = new TimeUtil();
        noticeEntity.setId(timeUtil.getLongTime());
        noticeEntity.setCreateTime(timeUtil.getFormatDateForSix());
        return noticeDao.insert(noticeEntity);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult findAllUsers(Integer pageSize, Integer pageNum) {

        IPage<UsersEntity> usersEmptyIPage = usersDao.selectPage(new Page<>(pageNum, pageSize), new QueryWrapper<UsersEntity>().orderByDesc("id"));
        List<UsersEntity> records = usersEmptyIPage.getRecords(); // 显示内容
        PagedResult grid = new PagedResult();
        grid.setPage(pageNum);
        grid.setTotal(usersEmptyIPage.getPages());
        grid.setRecords(usersEmptyIPage.getTotal());
        grid.setRows(records);
        return grid;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult getAllFriendsUrl(Integer pageSize, Integer pageNum) {

        IPage<FriendurlEntity> usersEmptyIPage = friendurlDao.selectPage(new Page<>(pageNum, pageSize), new QueryWrapper<FriendurlEntity>().orderByDesc("id"));
        List<FriendurlEntity> records = usersEmptyIPage.getRecords(); // 显示内容
        PagedResult grid = new PagedResult();
        grid.setPage(pageNum);
        grid.setTotal(usersEmptyIPage.getPages());
        grid.setRecords(usersEmptyIPage.getTotal());
        grid.setRows(records);
        return grid;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult getAllNotice(Integer pageSize, Integer pageNum) {

        IPage<NoticeEntity> usersEmptyIPage = noticeDao.selectPage(new Page<>(pageNum, pageSize), new QueryWrapper<NoticeEntity>().orderByDesc("id"));
        List<NoticeEntity> records = usersEmptyIPage.getRecords(); // 显示内容
        PagedResult grid = new PagedResult();
        grid.setPage(pageNum);
        grid.setTotal(usersEmptyIPage.getPages());
        grid.setRecords(usersEmptyIPage.getTotal());
        grid.setRows(records);
        return grid;
    }
}
