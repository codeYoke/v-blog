package com.fjh.modules.sys.controller;

import com.fjh.common.utils.BlogJSONResult;
import com.fjh.modules.sys.entity.LabelEntity;
import com.fjh.modules.sys.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: fjh
 * @ProjectName: adminsystem
 * @Package: com.fjh.controller
 * @Description: 标签云
 * @Date: 2019/7/8 0008 19:05
 **/
@RestController
@RequestMapping("/tag")
public class LabelController {

    @Autowired
    private LabelService labelService;



    /**
     * 标签云
     * @return
     */
    @GetMapping("/getTags")
    public BlogJSONResult getTags(){
        List<LabelEntity> labels = labelService.selAllLabel();
        if(labels == null || labels.size() == 0 ){
            return BlogJSONResult.errorMsg("无数据");
        }
        return BlogJSONResult.ok(labels);
    }

}
