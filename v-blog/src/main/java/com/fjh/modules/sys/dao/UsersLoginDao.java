package com.fjh.modules.sys.dao;

import com.fjh.modules.sys.entity.VO.UsersVOEntity;
import org.springframework.stereotype.Repository;

/**
 * @Author: fjh
 * @ProjectName: adminsystem
 * @Package: com.fjh.modules.sys.dao
 * @Description:
 * @Date: 2019/8/17 0017 12:44
 **/
@Repository
public interface UsersLoginDao {
    UsersVOEntity findByPhone(String phone);

}
