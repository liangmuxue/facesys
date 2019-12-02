package com.ss.isc.web.manage.viid.controller.collect;

import com.alibaba.fastjson.JSONObject;
import com.ss.isc.data.archives.client.ISubscribeService;
import com.ss.isc.data.collect.common.web.SubscribeVO;
import com.ss.isc.web.manage.baseinfo.controller.BaseController;
import com.ss.response.ResponseEntity;
import com.ss.valide.APIAddGroup;
import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIEditGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
* VIID 订阅
* @author chao
* @create 2019/10/28
* @email lishuangchao@ss-cas.com
**/
@RestController
@RequestMapping({"/viid/collect/subscribe"})
public class SubscribeController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(SubscribeController.class);

    @Resource
    private ISubscribeService subscribeService;

    /**
     * 新增订阅
     * @param subscribeVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST})
    public ResponseEntity<JSONObject> add(@RequestBody @Validated({APIAddGroup.class})SubscribeVO subscribeVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<JSONObject> resp = validite(bindingResult);
        try {
            //新增订阅
            String message = this.subscribeService.add(subscribeVO);
            resp.setMessage(message);
        }catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("VIID 新增订阅失败!");
            this.logger.error("VIID 新增订阅失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 删除订阅
     * @param subscribeVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    public ResponseEntity<JSONObject> delete(@RequestBody @Validated({APIDeltGroup.class})SubscribeVO subscribeVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<JSONObject> resp = validite(bindingResult);
        try {
            //删除订阅
            String message = this.subscribeService.delete(subscribeVO);
            resp.setMessage(message);
        }catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("VIID 删除订阅失败!");
            this.logger.error("VIID 删除订阅失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 修改订阅
     * @param subscribeVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    public ResponseEntity<JSONObject> update(@RequestBody @Validated({APIEditGroup.class})SubscribeVO subscribeVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<JSONObject> resp = validite(bindingResult);
        try {
            //修改订阅
            String message = this.subscribeService.update(subscribeVO);
            resp.setMessage(message);
        }catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("VIID 修改订阅失败!");
            this.logger.error("VIID 修改订阅失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}
