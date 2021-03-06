package com.fjh.modules.sys.controller;

import com.fjh.common.utils.BlogJSONResult;
import com.fjh.common.utils.Constant;
import com.fjh.common.utils.RedisOperator;
import com.fjh.common.utils.verify.service.SMTPService;
import com.fjh.modules.sys.entity.UsersEntity;
import com.fjh.modules.sys.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: fjh
 * @ProjectName: adminsystem
 * @Package: com.fjh.modules.sys.controller
 * @Description: 注册
 * @Date: 2019/9/4 0004 12:20
 **/
@RestController
public class RegisterController {

    @Autowired
    private RedisOperator redisOperator;

 /*   @Autowired
    private SMSService smsService;*/
    @Autowired
    private SMTPService smtpService;

    @Autowired
    private RegisterService registerService;

    /**
     * 注册用户
     * @param users
     * @return
     */
    @PostMapping("register")
    public BlogJSONResult register(@RequestBody UsersEntity users){
        int result = registerService.insUsers(users);
        if(result > 0){
            redisOperator.del(Constant.USER_PHONE_CODE+users.getEmail());
            return BlogJSONResult.ok();
        }
        return BlogJSONResult.errorMsg("注册失败");
    }


    /**
     * 手机号检测
     * @param phone
     * @return 不存在 -- ok()     存在 -- errorMsg("该手机号已被注册")
     */
    @GetMapping("phoneCheck")
    public BlogJSONResult phoneCheck(@RequestParam("phone") String phone){
        // 先从缓存中查询
        if(redisOperator.hsize(Constant.USER_PHONE_EXIST) != 0){
            if(!redisOperator.hasHkey(Constant.USER_PHONE_EXIST, phone)){
                return BlogJSONResult.ok();
            }else{
                return BlogJSONResult.errorMsg("该手机号已被注册");
            }
        }else{
            int result = registerService.findByPhone(phone);
            if(result == 0){
                return BlogJSONResult.ok();
            }else{
                return BlogJSONResult.errorMsg("该手机号已被注册");
            }
        }
    }

    /**
     * 邮箱号检测
     * @param email
     * @return 不存在 -- ok()     存在 -- errorMsg("该邮箱号已被注册")
     */
    @GetMapping("emailCheck")
    public BlogJSONResult emailCheck(@RequestParam("email") String email){
            int result = registerService.findByEmail(email);
            if(result == 0){
                return BlogJSONResult.ok();
            }else{
                return BlogJSONResult.errorMsg("该手机号已被注册");
            }

    }


    /**
     * 用户检测
     * @param username 用户名称
     * @return 不存在 -- ok()     存在 -- errorMsg("该用户已存在")
     */
    @GetMapping("usernameCheck")
    public BlogJSONResult usernameCheck(@RequestParam("username") String username){
        // 先从缓存中查询
        if(redisOperator.hsize(Constant.USER_NAME_EXIST) != 0){
            if(!redisOperator.hasHkey(Constant.USER_NAME_EXIST, username)){
                return BlogJSONResult.ok();
            }else{
                return BlogJSONResult.errorMsg("该用户已存在");
            }

        }else{
            int result = registerService.findByUsername(username);
            if(result == 0){
                return BlogJSONResult.ok();
            }else{
                return BlogJSONResult.errorMsg("该用户已存在");
            }
        }
    }

/*
    *//**
     *  获取验证码(点击按钮) -- 注册
     * @param phone 手机号
     * @return
     *//*
    @GetMapping("getCode")
    public BlogJSONResult getCode(@RequestParam("phone") String phone){
        String s = smsService.sendMesModel(phone, 0);
        System.out.println(s);
        if(s.equals("OK")){
            return BlogJSONResult.ok();
        }else{
            return BlogJSONResult.errorMsg("获取验证码失败");
        }
    }

    *//**
     *  获取验证码(五分钟输入正确验证码即可)
     * @param phone 手机号
     * @return
     *//*
    @GetMapping("getCodeReflush")
    public BlogJSONResult getCodeReflush(@RequestParam("phone") String phone){
        if(redisOperator.hasKey(Constant.USER_PHONE_CODE+phone)){
            return BlogJSONResult.ok(redisOperator.get(Constant.USER_PHONE_CODE+phone));
        }else{
            return BlogJSONResult.errorMsg("验证码失效");
        }
    }*/

    /**
     *  获取验证码(点击按钮) -- 注册
     * @param email 邮箱
     * @return
     */
    @GetMapping("getCode")
    public BlogJSONResult getCode(@RequestParam("email") String email){
        String s = smtpService.sendMesModel(email, 0);
        System.out.println(s);
        if(s.equals("OK")){
            return BlogJSONResult.ok();
        }else{
            return BlogJSONResult.errorMsg("获取验证码失败");
        }
    }

    /**
     *  获取验证码(五分钟输入正确验证码即可)
     * @param email 邮箱
     * @return
     */
    @GetMapping("getCodeReflush")
    public BlogJSONResult getCodeReflush(@RequestParam("email") String email){
        if(redisOperator.hasKey(Constant.USER_PHONE_CODE+email)){
            return BlogJSONResult.ok(redisOperator.get(Constant.USER_PHONE_CODE+email));
        }else{
            return BlogJSONResult.errorMsg("验证码失效");
        }
    }
}
