package com.ss.facesys.web.manage.system.controller;

import com.github.pagehelper.Page;
import com.ss.enums.MessageTypeEnum;
import com.ss.facesys.data.system.client.IMyMessageService;
import com.ss.facesys.data.system.common.dto.MyMessageQuery;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 关于系统
 *
 * @author 李爽超 chao
 * @create 2020/08/24
 * @email lishuangchao@ss-cas.com
 **/

@RestController
@RequestMapping({"/myMessage"})
public class MyMessageController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(MyMessageController.class);

    @Autowired
    private IMyMessageService myMessageService;

    /**
     * 我的消息
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/page"}, method = {RequestMethod.POST})
    public ResponseEntity<PageEntity<Object>> page(@RequestBody MyMessageQuery para, BindingResult bindingResult) throws Exception {
        try {
            ResponseEntity<PageEntity<Object>> resp = validite(bindingResult);
            Page<Object> page = null;
            if(para.getType().equals(MessageTypeEnum.SYSTEM.getCode())){
                page = (Page) this.myMessageService.systemPage(para);
            }else {
                page = (Page) this.myMessageService.alarmPage(para);
            }
            resp.setData(new PageEntity(page));
            return resp;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            throw e;
        }
    }

    @RequestMapping(value = {"/del"}, method = {RequestMethod.POST})
    public ResponseEntity<Integer> del(@RequestBody MyMessageQuery para, BindingResult bindingResult) throws Exception {
        try {
            ResponseEntity<Integer> resp = validite(bindingResult);
            int i = 0;
            if(para.getType().equals(MessageTypeEnum.SYSTEM.getCode())){
                i = this.myMessageService.delSystemMes(para.getId());
            }else {
                i = this.myMessageService.delAlarmMes(para.getId());
            }
            resp.setData(i);
            return resp;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            throw e;
        }
    }
}
