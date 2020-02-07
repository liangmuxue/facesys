package com.ss.facesys.web.manage.collect.controller;

import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.collect.client.IOfflineVideoService;
import com.ss.facesys.data.collect.common.model.OfflineVideo;
import com.ss.facesys.data.resource.common.web.OfflineVideoVO;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.spider.log.constants.ModuleCode;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* 离线视频
* @author chao
* @create 2020/2/7
* @email lishuangchao@ss-cas.com
**/
@RestController
@CrossOrigin
@RequestMapping({"/offlineVideo"})
public class OfflineVideoController extends BaseController {

    @Resource
    private IOfflineVideoService offlineVideoService;

    /**
     * 离线视频分页查询
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/page"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "离线视频分页查询", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<PageEntity<OfflineVideo>> internetBarPage(@RequestBody OfflineVideoVO para , BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<OfflineVideo>> resp = validite(bindingResult);
        try {
            Page<OfflineVideo> data = (Page) this.offlineVideoService.offlineVideoPage(para);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            //离线视频分页查询失败处理
            this.logger.error("离线视频分页查询失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }
}
