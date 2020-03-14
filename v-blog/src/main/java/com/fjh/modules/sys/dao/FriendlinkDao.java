package com.fjh.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fjh.modules.sys.entity.FriendLinkEntity;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: fjh
 * @ProjectName: adminsystem
 * @Package: com.fjh.modules.sys.dao
 * @Description:
 * @Date: 2019/9/19 0019 20:26
 **/
@Repository
public interface FriendlinkDao extends BaseMapper<FriendLinkEntity> {

    List<FriendLinkEntity> getAllGuest();

    @Update("update friendlink set likes = likes - 1 where id = #{arg0}")
    int updDesRepGuest(Long id);

    @Update("update friendlink set likes = likes + 1 where id = #{arg0}")
    int updInsRepGuest(Long id);


}
