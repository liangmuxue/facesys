package com.ss.isc.web.manage.viid.controller.collect;

import com.alibaba.fastjson.JSONObject;
import com.ss.isc.data.archives.client.IDispositionService;
import com.ss.isc.data.collect.common.web.DispositionVO;
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
* VIID 布控
* @author chao
* @create 2019/10/28
* @email lishuangchao@ss-cas.com
**/
@RestController
@RequestMapping({"/viid/collect/disposition"})
public class DispositionController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(DispositionController.class);

    @Resource
    private IDispositionService dispositionService;

    /**
     * VIID 新增布控
     * @param dispositionVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST})
    public ResponseEntity<JSONObject> add(@RequestBody @Validated({APIAddGroup.class}) DispositionVO dispositionVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<JSONObject> resp = validite(bindingResult);
        try {
            //新增布控
            String message = this.dispositionService.add(dispositionVO);
            resp.setMessage(message);
        }catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("VIID 新增布控失败!");
            this.logger.error("VIID 新增布控失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * VIID 撤控
     * @param dispositionVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    public ResponseEntity<JSONObject> delete(@RequestBody @Validated({APIDeltGroup.class}) DispositionVO dispositionVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<JSONObject> resp = validite(bindingResult);
        try {
            //撤控
            String message = this.dispositionService.delete(dispositionVO);
            resp.setMessage(message);
        }catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("VIID 撤控失败!");
            this.logger.error("VIID 撤控失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * VIID 修改布控
     * @param dispositionVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/edit"}, method = {RequestMethod.POST})
    public ResponseEntity<JSONObject> edit(@RequestBody @Validated({APIEditGroup.class}) DispositionVO dispositionVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<JSONObject> resp = validite(bindingResult);
        try {
            //修改布控
            String message = this.dispositionService.edit(dispositionVO);
            resp.setMessage(message);
        }catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("VIID 修改布控失败!");
            this.logger.error("VIID 修改布控失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}
