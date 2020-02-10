package com.ss.facesys.web.manage.collect.controller;

import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.collect.client.IInternetBarService;
import com.ss.facesys.data.collect.common.model.InternetBar;
import com.ss.facesys.data.resource.common.web.InternetBarVO;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.spider.log.constants.ModuleCode;
import com.ss.valide.APIAddGroup;
import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIEditGroup;
import com.ss.valide.APIGetsGroup;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* 网吧
* @author chao
* @create 2020/2/6
* @email lishuangchao@ss-cas.com
**/
@RestController
@CrossOrigin
@RequestMapping({"/internetBar"})
public class InternetBarController extends BaseController {

    @Resource
    private IInternetBarService internetBarService;

    /**
     * 网吧分页查询
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/page"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "网吧分页查询", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<PageEntity<InternetBar>> internetBarPage(@RequestBody InternetBarVO para , BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<InternetBar>> resp = validite(bindingResult);
        try {
            Page<InternetBar> data = (Page) this.internetBarService.internetBarPage(para);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            //网吧分页查询失败处理
            this.logger.error("网吧分页查询失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 添加网吧
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/insert"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "添加网吧", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> insertInternetBar(@RequestBody @Validated({APIAddGroup.class})InternetBarVO para , BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            int num = this.internetBarService.insertInternetBar(para);
            if (num > 0) {
                resp.setData("添加成功");
            } else {
                resp = createFailResponse();
                resp.setMessage("添加失败，请联系管理员");
            }
        } catch (Exception e) {
            //添加网吧失败处理
            this.logger.error("添加网吧失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 修改网吧信息
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "修改网吧信息", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> updateInternetBar(@RequestBody @Validated({APIEditGroup.class})InternetBarVO para , BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            int num = this.internetBarService.updateInternetBar(para);
            if (num > 0) {
                resp.setData("修改成功");
            } else {
                resp = createFailResponse();
                resp.setMessage("修改失败，请联系管理员");
            }
        } catch (Exception e) {
            //修改网吧失败处理
            this.logger.error("修改网吧信息失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 删除网吧
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "删除网吧", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> deleteInternetBar(@RequestBody @Validated({APIDeltGroup.class})InternetBarVO para , BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            int num = this.internetBarService.deleteInternetBar(para);
            if (num > 0) {
                resp.setData("删除成功");
            } else {
                resp = createFailResponse();
                resp.setMessage("删除失败，请联系管理员");
            }
        } catch (Exception e) {
            //删除网吧失败处理
            this.logger.error("删除网吧失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 查询网吧详情
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/detail"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "查询网吧详情", type = OperaTypeEnum.SELECT)
    public ResponseEntity<InternetBar> internetBarDetail(@RequestBody @Validated({APIGetsGroup.class})InternetBarVO para , BindingResult bindingResult) throws Exception {
        ResponseEntity<InternetBar> resp = validite(bindingResult);
        try {
            InternetBar internetBar = this.internetBarService.detail(para);
            resp.setData(internetBar);
        } catch (Exception e) {
            //查询网吧详情失败处理
            this.logger.error("查询网吧详情失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }
}
