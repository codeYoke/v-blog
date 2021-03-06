package com.fjh.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: fjh
 * @ProjectName: adminsystem
 * @Package: com.fjh.modules.sys.entity
 * @Description: 友链评论点赞用户实体类
 * @Date: 2019/7/24 0024 19:51
 **/
@Data
@TableName(value = "friendlikes")
public class FriendLikesEntity implements Serializable {


    private static final long serialVersionUID = -4400820938884482478L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 留言者id
     */
    private Long friendId;

    /**
     * 点赞名称
     */
    private String likeName;

}
