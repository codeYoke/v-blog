package com.fjh.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: NoticeEntity
 * Description:公告实体类
 *
 * @author 冯佳豪
 */

@Data
@TableName(value = "notice")
public class NoticeEntity implements Serializable {
    private static final long serialVersionUID = -9163902960357555427L;

    @TableId
    private Long id;

    /**
     * 公告内容
     */
    private String noticeContent;


    /**
     * 创建时间
     */
    private String createTime;
}

