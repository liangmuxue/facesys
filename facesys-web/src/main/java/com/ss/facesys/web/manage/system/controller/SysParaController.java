package com.ss.facesys.web.manage.system.controller;

import com.ss.facesys.data.system.client.ISysParaService;
import com.ss.facesys.data.system.common.model.SysPara;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.system.form.SysParaForm;
import com.ss.response.ResponseEntity;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * SysParaController
 *
 * @author FrancisYs
 * @date 2020/2/14
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/syspara"})
public class SysParaController extends BaseController {

    @Resource
    private ISysParaService sysParaService;

    /**
     * 查询当前的个性化设置信息
     *
     * @param sysPara
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/infoMap"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, String>> infoMap(@RequestBody SysPara sysPara, BindingResult bindingResult) throws Exception {
        try {
            ResponseEntity<Map<String, String>> resp = validite(bindingResult);
            Map<String, String> map = this.sysParaService.getSysParaInfoMap(sysPara);
            resp.setData(map);
            return resp;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            throw e;
        }
    }

    /**
     * 恢复默认配置
     *
     * @param sysPara
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/reset"}, method = {RequestMethod.POST})
    public ResponseEntity<String> reset(@RequestBody SysPara sysPara, BindingResult bindingResult) throws Exception {
        try {
            ResponseEntity<String> resp = validite(bindingResult);
            resp.setData(this.sysParaService.reset(sysPara));
            return resp;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            throw e;
        }
    }

    /**
     * 修改个性化配置
     *
     * @param form
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    public ResponseEntity<String> update(@RequestBody SysParaForm form, BindingResult bindingResult) throws Exception {
        try {
            ResponseEntity<String> resp = validite(bindingResult);
            if (CollectionUtils.isEmpty(form.getSysParaList())) {
                return createFailResponse("修改内容不能为空");
            }
            resp.setData(this.sysParaService.updateSysPara(form.getSysParaList()));
            return resp;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            throw e;
        }
    }

}
