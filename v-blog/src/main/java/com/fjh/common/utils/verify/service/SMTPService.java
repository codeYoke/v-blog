package com.fjh.common.utils.verify.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.fjh.common.utils.Constant;
import com.fjh.common.utils.RedisOperator;
import com.fjh.common.utils.verify.component.MailRandomBuilder;
import com.fjh.common.utils.verify.component.PhoneRandomBuilder;
import com.fjh.common.utils.verify.util.SMSUtil;
import com.fjh.common.utils.verify.util.SMTPUtil;
import com.github.qcloudsms.*;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * @Author: fjh
 * @ProjectName: adminsystem
 * @Package: com.fjh.service
 * @Description: 邮箱服务类
 **/
@Service
public class SMTPService {

    @Autowired
    private RedisOperator redisOperator;
    /**
     * 自定义内容发送
     * @param msg 短信内容
     * @param number 用户邮箱
     * @return OK 成功  null 失败
     */
    public String sendMess(String msg, String number){
        try {
            MailAccount account = new MailAccount();
            account.setFrom(SMTPUtil.FROM_SENDER);
            account.setPass(SMTPUtil.PASS);
            MailUtil.send(account, CollUtil.newArrayList(number),
                    SMTPUtil.NOTESIGN, msg, false);
            return "OK";
        } catch (Exception e) {
            //邮件发送报错
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 指定发送邮箱
     * @param number 用户邮箱
     * @param mark 0 -- 注册验证   1 -- 修改密码验证
     * @return OK 成功  null 失败
     */
    public String sendMesModel(String number, Integer mark){
        try {
            // 验证码随机数
            String code = MailRandomBuilder.randomBuilder();
            // 把验证码存入缓存中
            redisOperator.set(Constant.USER_PHONE_CODE + number, code);
            // 设置过期时间（默认以秒为单位）
            redisOperator.expire(Constant.USER_PHONE_CODE + number, 300);

            String[] params = {code};
            if(mark == 0){
                sendMess("欢迎注册vblog博客，验证码："+code,number);
                return "OK";//OK
            }else if(mark == 1){
                sendMess("vblog博客账户密码修改，验证码："+code,number);
                return "OK";//OK
            }
        } catch (Exception e) {
            //发送错误
            e.printStackTrace();
        }
        return null;
    }



}

