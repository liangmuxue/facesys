package com.ss.facesys.web.manage.resource.controller;

import com.ss.facesys.data.resource.client.ICommunityResourceService;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.resource.common.model.Village;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.response.ResponseEntity;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"/communityResource"})
public class CommunityResourceController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(CommunityResourceController.class);


    @Resource
    private ICommunityResourceService communityResourceService;


    @RequestMapping(value = {"/cameras"}, method = {RequestMethod.POST})
    public ResponseEntity<List<Camera>> findCameras(HttpServletRequest request, HttpServletResponse response, @RequestBody Camera camera, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<Camera>> resp = createSuccResponse();
        try {
            List<Camera> cameras = this.communityResourceService.findCameras(camera);
            resp.setData(cameras);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("相机查询失败");
            this.logger.error("相机信息查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/villages"}, method = {RequestMethod.POST})
    public ResponseEntity<List<Village>> findVillages(HttpServletRequest request, HttpServletResponse response, @RequestBody Village village, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<Village>> resp = createSuccResponse();
        try {
            List<Village> list = this.communityResourceService.findVillages(village);
            resp.setData(list);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("小区查询失败");
            this.logger.error("小区查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}
