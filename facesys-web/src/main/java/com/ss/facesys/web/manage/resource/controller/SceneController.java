package com.ss.facesys.web.manage.resource.controller;

import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.resource.client.ISceneService;
import com.ss.facesys.data.resource.common.model.Scene;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.response.ResponseEntity;
import com.ss.spider.log.constants.ModuleCode;
import com.ss.valide.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* 场景管理
* @author chao
* @create 2019/12/24
* @email lishuangchao@ss-cas.com
**/
@RestController
@RequestMapping({"/scene"})
public class SceneController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(SceneController.class);

    @Resource
    private ISceneService sceneService;

    /**
     * 查询场景列表
     * @param scene
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "查询场景列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<Scene>> detail(@RequestBody Scene scene, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<Scene>> resp = validite(bindingResult);
        try {
            List<Scene> sceneList = this.sceneService.list();
            resp.setData(sceneList);
        } catch (Exception e) {
            this.logger.error("查询场景列表失败的原因：" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("查询场景列表失败");
        }
        return resp;
    }

    /**
     * 新增场景
     * @param scene
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "新增场景", type = OperaTypeEnum.ADD)
    public ResponseEntity<Integer> add(@RequestBody @Validated({APIAddGroup.class}) Scene scene, BindingResult bindingResult) throws Exception {
        ResponseEntity<Integer> resp = validite(bindingResult);
        try {
            int result = this.sceneService.add(scene);
            resp.setData(result);
        } catch (Exception e) {
            this.logger.error("新增场景失败的原因：" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("新增场景失败");
        }
        return resp;
    }

    /**
     * 修改场景
     * @param scene
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/edit"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "修改场景", type = OperaTypeEnum.EDIT)
    public ResponseEntity<Integer> edit(@RequestBody @Validated({APIEditGroup.class}) Scene scene, BindingResult bindingResult) throws Exception {
        ResponseEntity<Integer> resp = validite(bindingResult);
        try {
            int result = this.sceneService.edit(scene);
            resp.setData(result);
        } catch (Exception e) {
            this.logger.error("修改场景的原因：" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("修改场景失败");
        }
        return resp;
    }

    /**
     * 删除场景
     * @param scene
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/delt"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "删除场景", type = OperaTypeEnum.EDIT)
    public ResponseEntity<Integer> delt(@RequestBody @Validated({APIDeltGroup.class}) Scene scene, BindingResult bindingResult) throws Exception {
        ResponseEntity<Integer> resp = validite(bindingResult);
        try {
            int result = this.sceneService.delt(scene);
            resp.setData(result);
        } catch (Exception e) {
            this.logger.error("删除场景的原因：" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("删除场景失败");
        }
        return resp;
    }
}
