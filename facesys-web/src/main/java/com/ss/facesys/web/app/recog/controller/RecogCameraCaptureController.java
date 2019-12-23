package com.ss.facesys.web.app.recog.controller;

import com.alibaba.fastjson.JSONObject;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.exception.ServiceException;
import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.access.common.dto.FacedbfaceDTO;
import com.ss.facesys.data.baseinfo.common.dto.PersonCaptureDTO;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.em.ResultCode;
import com.ss.facesys.util.file.FilePropertiesUtil;
import com.ss.facesys.util.http.BaseFormatJsonUtil;
import com.ss.facesys.web.app.recog.query.RecogCaptureQuery;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.response.ResponseEntity;
import com.ss.spider.log.constants.ModuleCode;
import com.ss.tools.Base64ImageUtils;
import com.ss.tools.FileUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1:N 抓拍库检索
 *
 * @author FrancisYs
 * @date 2019/12/20
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/recog"})
public class RecogCameraCaptureController extends BaseController {

    @Resource
    private IAccessService accessService;

    @PostMapping(value = {"/cameraDb"})
    @OpLog(model = ModuleCode.BUSINESS, desc = "1:N 抓拍库检索", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<Map<String, Object>> recog(@RequestBody @Validated RecogCaptureQuery captureQuery, BindingResult bindingResult) throws BindException {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            paramCheck(captureQuery);
            deviceCheck(captureQuery);
            // 请求汇聚平台
            JSONObject oceanResult;
            try {
                oceanResult = this.accessService.getRecogCameraDb(getVplatParam(captureQuery));
                if (!StringUtils.checkSuccess(oceanResult)) {
                    throw new ServiceException(oceanResult.getString("code"), oceanResult.getString("message"));
                }
            } catch (ServiceException e) {
                throw e;
            } catch (Exception e) {
                throw new ServiceException(ResultCode.RECOG_CAPTURE_VPLAT_FAIL);
            }
            // 将结果转换为数据传输对象
            List<PersonCaptureDTO> resultList = BaseFormatJsonUtil.formatList(oceanResult.get("data"), PersonCaptureDTO.class);
            resp.setData(assemblePage(filterCondition(resultList, captureQuery), null, null));
        } catch (ServiceException e) {
            this.logger.error("1:N 抓拍库检索失败，错误码：{}，异常信息：{}", e.getCode(), e.getMessage(), e);
            return createFailResponse(e);
        }
        return resp;
    }

    private void paramCheck(RecogCaptureQuery captureQuery) throws ServiceException {
        // 图片格式处理
        if (captureQuery.getImgType().equals(CommonConstant.IMGTYPE_BASE64)) {
            if (!Base64ImageUtils.isCheckBase64(captureQuery.getImg())) {
                throw new ServiceException(ResultCode.ONETOONE_IMAGE_TYPE_ERROR);
            }
            captureQuery.setImgType(CommonConstant.IMGTYPE_BASE64);
        } else if (captureQuery.getImgType().equals(CommonConstant.IMGTYPE_URL) || captureQuery.getImgType().equals(CommonConstant.IMGTYPE_FULLURL)) {
            String imgUrl = captureQuery.getImgType().equals(CommonConstant.IMGTYPE_FULLURL) ? captureQuery.getImg() : FilePropertiesUtil.getHttpUrl() + captureQuery.getImg();
            String img = null;
            try {
                img = FileUtils.getBase64ByUrl(imgUrl);
            } catch (Exception e) {
                this.logger.error("URL转换Base64失败，img=" + captureQuery.getImg(), e);
            }
            if (StringUtils.isBlank(img)) {
                throw new ServiceException(ResultCode.ONETOONE_IMAGE_TYPE_ERROR);
            }
            captureQuery.setImg(img);
        }
        // topN 处理
        captureQuery.setTopN(PropertiesUtil.getTopN());
        // 相似度范围处理
        if (captureQuery.getThresholdMin() == null) {
            captureQuery.setThresholdMin(PropertiesUtil.getThreshold());
        }
        if (captureQuery.getThresholdMax() == null) {
            captureQuery.setThresholdMax(1.0);
        }
    }

    /**
     * 校验设备权限并获取汇聚平台设备ID参数 TODO
     *
     * @param captureQuery
     * @throws ServiceException
     */
    private void deviceCheck(RecogCaptureQuery captureQuery) throws ServiceException {

    }

    private String getVplatParam(RecogCaptureQuery captureQuery) {
        JSONObject param = new JSONObject();
        param.put("img", captureQuery.getImg());
        param.put("imgType", CommonConstant.IMGTYPE_BASE64);
        param.put("deviceIds", captureQuery.getVplatDeviceIds());
        param.put("captureTimeB", captureQuery.getCaptureTimeB());
        param.put("captureTimeE", captureQuery.getCaptureTimeE());
        param.put("topN", captureQuery.getTopN());
        param.put("thresholdMin", captureQuery.getThresholdMin());
        param.put("thresholdMax", captureQuery.getThresholdMax());
        return param.toJSONString();
    }

    private List<PersonCaptureDTO> filterCondition(List<PersonCaptureDTO> resultList, RecogCaptureQuery captureQuery) {
        List<PersonCaptureDTO> filter = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(resultList)) {
            Integer ageB, ageE, glassesState, sunglassesState, maskState, minorityState, gender;
            for (PersonCaptureDTO personCaptureDTO : resultList) {
                boolean ageValid = (ageB = captureQuery.getAgeB()) == null || (ageE = captureQuery.getAgeE()) == null || personCaptureDTO.getAge() >= ageB && personCaptureDTO.getAge() <= ageE;
                boolean propertyValid =
                        ((glassesState = captureQuery.getGlassesState()) == null || personCaptureDTO.getGlassesState().equals(glassesState)) &&
                                ((sunglassesState = captureQuery.getSunglassesState()) == null || personCaptureDTO.getSunGlassesState().equals(sunglassesState)) &&
                                ((maskState = captureQuery.getMaskState()) == null || personCaptureDTO.getMaskState().equals(maskState)) &&
                                ((minorityState = captureQuery.getMinorityState()) == null || personCaptureDTO.getNation() != 1);
                boolean genderValid = (gender = captureQuery.getGender()) == null || personCaptureDTO.getGender().equals(gender);
                if (ageValid && propertyValid && genderValid) {
                    filter.add(personCaptureDTO);
                }
            }
        }
        return filter;
    }

    private Map<String, Object> assemblePage(List<PersonCaptureDTO> resultList, Integer currentPage, Integer pageSize) {
        Map<String, Object> resultMap = new HashMap<>(1);
        resultMap.put("recogList", resultList);
        return resultMap;
    }

}
