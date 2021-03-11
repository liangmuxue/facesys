package com.ss.facesys.web.manage.collect.controller;

import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.collect.client.ISnapRecordService;
import com.ss.facesys.data.collect.common.model.SnapRecord;
import com.ss.facesys.data.collect.common.web.SnapRecordVO;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * com.ss.facesys.web.manage.collect.controller
 *
 * @author 李爽超 chao
 * @create 2021/03/08
 * @email lishuangchao@ss-cas.com
 **/

@RestController
@RequestMapping({"/collect/snapRecord"})
public class SnapRecordController extends BaseController {

    @Resource
    private ISnapRecordService snapRecordService;

    @RequestMapping(value = {"/page"}, method = {RequestMethod.POST})
    @OpLog(model = "80004", desc = "抓拍记录分页查询", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<PageEntity<SnapRecord>> page(@RequestBody SnapRecordVO queryVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<SnapRecord>> resp = validite(bindingResult);
        try {
            Page<SnapRecord> data = (Page)this.snapRecordService.page(queryVO);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            this.logger.error("抓拍记录分页查询失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }
}
