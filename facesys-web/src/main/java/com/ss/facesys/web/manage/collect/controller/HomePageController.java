package com.ss.facesys.web.manage.collect.controller;

import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.collect.client.IHomePageService;
import com.ss.facesys.data.collect.common.model.HomePageBase;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.response.ResponseEntity;
import com.ss.spider.log.constants.ModuleCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * com.ss.facesys.web.manage.collect.controller
 *
 * @author 李爽超 chao
 * @create 2021/03/02
 * @email lishuangchao@ss-cas.com
 **/
@RestController
@RequestMapping({"/homePage"})
public class HomePageController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(HomePageController.class);

    @Resource
    private IHomePageService homePage;

    @RequestMapping(value = {"/get"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "首页基础数据统计查询", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<HomePageBase> get(@RequestBody HomePageBase para , BindingResult bindingResult) throws Exception {
        ResponseEntity<HomePageBase> resp = validite(bindingResult);
        try {
            HomePageBase data = this.homePage.get(para);
            resp.setData(data);
        } catch (Exception e) {
            //首页基础数据统计查询失败处理
            this.logger.error("首页基础数据统计查询失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }
}
