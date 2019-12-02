package com.ss.facesys.web.manage.baseinfo.controller;

import com.ss.facesys.data.baseinfo.common.web.TemplateVO;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.export.TemplateUtils;
import com.ss.facesys.util.file.FilePropertiesUtil;
import com.ss.response.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"/template"})
public class TemplateController extends BaseController {

    @RequestMapping(value = {"/download"}, method = {RequestMethod.POST})
    public ResponseEntity<String> get(@RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) TemplateVO para, BindingResult bindingResult) throws Exception {
        try {
            ResponseEntity<String> resp = validite(bindingResult);
            String fileName = TemplateUtils.download(para.getType());
            if (StringUtils.isBlank(fileName)) {
                resp = createFailResponse();
                resp.setMessage("模板未找到，请检查模板类型或联系管理员");
            } else {

                String templateUrl = FilePropertiesUtil.getHttpUrl() + "/" + "template" + "/" + fileName;

                resp.setData(templateUrl);
            }
            return resp;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            throw e;
        }
    }

}
