package com.fjh.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fjh.modules.sys.entity.VO.BlogMessageVOEntity;
import org.springframework.stereotype.Repository;

/**
 * @Author: fjh
 * @ProjectName: adminsystem
 * @Package: com.fjh.modules.sys.dao
 * @Description:
 * @Date: 2019/9/17 0017 15:44
 **/
@Repository
public interface BlogDao extends BaseMapper<BlogMessageVOEntity> {
}
