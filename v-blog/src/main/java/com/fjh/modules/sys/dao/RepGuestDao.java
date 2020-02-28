package com.fjh.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fjh.modules.sys.entity.RepGuestEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: fjh
 * @ProjectName: adminsystem
 * @Package: com.fjh.modules.sys.dao
 * @Description:
 * @Date: 2019/8/17 0017 20:38
 **/
@Repository
public interface RepGuestDao extends BaseMapper<RepGuestEntity> {

    @Select("select rid from repguest where guest_name = #{arg0} and ris_read = 1")
    List<Long> findNotGuest(String username);

    int updGuestIsRead(List<Long> rid);

}
