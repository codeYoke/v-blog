package com.fjh.common.utils.verify.component;

import org.springframework.stereotype.Component;

/**
 * @Author: fjh
 * @ProjectName: adminsystem
 * @Package: com.fjh.component
 * @Description: 邮箱验证码随机生成
 **/
@Component
public class MailRandomBuilder {

    public static String randomBuilder(){
        String result = "";
        for(int i=0;i<4;i++){
            result += Math.round(Math.random() * 9);
        }

        return result;

    }

}
