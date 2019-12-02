package com.ss.facesys.web.manage.system.controller;

import com.ss.facesys.data.system.client.ISysParamService;
import com.ss.facesys.data.system.common.model.SysParam;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.response.ResponseEntity;
import com.ss.spider.system.user.model.User;

import java.util.HashMap;
import java.util.Map;
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
@RequestMapping({"/sysparam"})
public class SysParamController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(SysParamController.class);


    @Resource
    private ISysParamService sysParamService;


    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, String>> list(HttpServletRequest request, HttpServletResponse response, @RequestBody SysParam sysParamDTO, BindingResult bindingResult) throws Exception {
        try {
            ResponseEntity<Map<String, String>> resp = validite(bindingResult);


            Map<String, String> map = this.sysParamService.getSysParamList(sysParamDTO);

            resp.setData(map);
            return resp;
        } catch (Exception e) {

            this.logger.error(e.toString(), e);
            throw e;
        }
    }


    @RequestMapping(value = {"/mod"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, String>> mod(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, String> sysParamMap, BindingResult bindingResult) throws Exception {
        try {
            ResponseEntity<Map<String, String>> resp = validite(bindingResult);


            this.sysParamService.modSysParam(sysParamMap);
            return resp;
        } catch (Exception e) {

            this.logger.error(e.toString(), e);
            throw e;
        }
    }


    @RequestMapping(value = {"/hello"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, String>> name1(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, String> sysParamMap, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);

        Map<String, String> map = new HashMap<String, String>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
        map.put("1", "1");

        resp.setData(map);
        return resp;
    }


    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, String>> add(HttpServletRequest request, HttpServletResponse response, @RequestBody SysParam sysParamDTO, BindingResult bindingResult) throws Exception {
        try {
            ResponseEntity<Map<String, String>> resp = validite(bindingResult);


            this.sysParamService.addSysParam(sysParamDTO);
            return resp;
        } catch (Exception e) {

            this.logger.error(e.toString(), e);
            throw e;
        }
    }


    @RequestMapping(value = {"/logout"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, String>> logout(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, String> sysParamMap, BindingResult bindingResult) throws Exception {
        try {
            ResponseEntity<Map<String, String>> resp = validite(bindingResult);
            String userid = (String) sysParamMap.get("userid");
            String username = (String) sysParamMap.get("username");
            User u = getUser(userid);
            this.jedisUtil.del(new String[]{"TOKEN_" + username, "USERNAME_" + username, "USERINFO_" + userid, "REFRESH_TOKEN_" + username});
            return resp;
        } catch (Exception e) {

            this.logger.error(e.toString(), e);
            throw e;
        }
    }

}
