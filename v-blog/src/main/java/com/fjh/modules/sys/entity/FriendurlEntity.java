package com.fjh.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: fjh
 * @ProjectName: adminsystem
 * @Package: com.fjh.modules.sys.entity
 * @Description: 具体友链类
 * @Date: 2019/9/19 0019 21:29
 **/
@Data
@TableName(value = "friendurl")
public class FriendurlEntity implements Serializable {
    private static final long serialVersionUID = -9163902960357555427L;

    @TableId
    private Long id;

    /**
     * 友链名
     */
    private String friendName;

    /**
     * 友链地址
     */
    private String friendUrl;

    /**
     * 创建时间
     */
    private String createTime;
}
