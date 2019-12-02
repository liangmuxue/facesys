package com.ss.facesys.web.manage.baseinfo.controller;

import com.ss.facesys.data.baseinfo.client.IEnumService;
import com.ss.facesys.data.baseinfo.common.model.BaseEnums;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.response.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 枚举常量请求
 * @author FrancisYs
 * @date 2019/08/08
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/baseinfo/enums"})
public class EnumsController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(EnumsController.class);
    @Resource
    private IEnumService enumService;

    /**
     * 查询单个枚举常量
     * @param enumDto 参数对象
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/enums"}, method = {RequestMethod.POST})
    public ResponseEntity<List<BaseEnums>> enums(@RequestBody BaseEnums enumDto, BindingResult bindingResult) throws Exception {
        try {
            ResponseEntity<List<BaseEnums>> resp = validite(bindingResult);
            if (StringUtils.isBlank(enumDto.getEnumType())) {
                resp = createFailResponse();
                resp.setCode(ResultCode.INVALID_PARAMETER);
                resp.setMessage(ResultCode.INVALID_PARAMETER_MESSAGE);
                return resp;
            }
            List<BaseEnums> enumList = this.enumService.findList(enumDto);
            resp.setData(enumList);
            return resp;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            throw e;
        }
    }

    /**
     * 查询多个枚举常量
     * @param para 参数Map对象
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/multipleEnums"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, List<BaseEnums>>> multipleEnums(@RequestBody Map<String, String> para, BindingResult bindingResult) throws Exception {
        try {
            ResponseEntity<Map<String, List<BaseEnums>>> resp = validite(bindingResult);
            Map<String, List<BaseEnums>> map = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
            String enumTypes = para.get("enumTypes");
            if (StringUtils.isNotBlank(enumTypes)) {
                BaseEnums baseEnums = new BaseEnums();
                String[] arr = enumTypes.split(",");
                for (String s : arr) {
                    baseEnums.setEnumType(s);
                    map.put(s, this.enumService.findList(baseEnums));
                }
                resp.setData(map);
            } else {
                resp = createFailResponse();
                resp.setCode(ResultCode.INVALID_PARAMETER);
                resp.setMessage(ResultCode.INVALID_PARAMETER_MESSAGE);
                resp.setData(map);
            }
            return resp;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            throw e;
        }
    }

}
