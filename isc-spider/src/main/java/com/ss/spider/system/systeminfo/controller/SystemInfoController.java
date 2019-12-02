package com.ss.spider.system.systeminfo.controller;

import com.ss.annotation.OpLog;
import com.ss.controller.AbstractController;
import com.ss.enums.OperaTypeEnum;
import com.ss.response.ResponseEntity;
import com.ss.spider.system.systeminfo.bean.SystemInfoDTO;
import com.ss.spider.system.systeminfo.form.SystemInfoForm;
import com.ss.spider.system.systeminfo.service.SystemInfoService;
import com.ss.spider.system.systeminfo.service.vo.SystemInfoVO;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"/systemInfo"})
public class SystemInfoController extends AbstractController {

    @Autowired
    private SystemInfoService systemInfoService;

    @PostMapping({"/get"})
    public ResponseEntity<SystemInfoVO> get() throws BindException {
        ResponseEntity<SystemInfoVO> resp = createSuccResponse();
        resp.setData(this.systemInfoService.get());
        return resp;
    }


    @PostMapping({"/edit"})
    @OpLog(model = "70008", desc = "修改系统信息", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> edit(@RequestBody @Validated({com.ss.valide.APIEditGroup.class}) SystemInfoForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        SystemInfoDTO updateDTO = new SystemInfoDTO();
        BeanUtils.copyProperties(para, updateDTO);
        try {
            SystemInfoVO systemInfoVO = this.systemInfoService.get();
            if (systemInfoVO == null) {
                this.systemInfoService.save(updateDTO);
            } else {
                this.systemInfoService.update(updateDTO);
            }
        } catch (MyBatisSystemException e) {
            this.logger.error("编辑系统信息失败,原因：", e);
            throw e;
        } catch (DataAccessResourceFailureException e) {
            this.logger.error("编辑系统信息失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("编辑系统信息失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
            return resp;
        }
        return resp;
    }

}
