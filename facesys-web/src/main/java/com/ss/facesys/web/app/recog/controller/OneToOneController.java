package com.ss.facesys.web.app.recog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ss.annotation.OpLog;
import com.ss.common.Constants;
import com.ss.controller.AbstractController;
import com.ss.enums.OperaTypeEnum;
import com.ss.exception.ServiceException;
import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.em.ResultCode;
import com.ss.facesys.util.file.FilePropertiesUtil;
import com.ss.facesys.web.app.recog.query.RecogQuery;
import com.ss.response.ResponseEntity;
import com.ss.spider.log.constants.ModuleCode;
import com.ss.tools.Base64ImageUtils;
import com.ss.tools.FileUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 1:1 比对
 *
 * @author FrancisYs
 * @date 2019/12/4
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/recog"})
public class OneToOneController extends AbstractController {

    @Resource
    private IAccessService accessService;

    @PostMapping({"/1vs1"})
    @OpLog(model = ModuleCode.BUSINESS, desc = "人像1:1比对", type = OperaTypeEnum.OTHER)
    public ResponseEntity<String> recog(@RequestBody @Validated final RecogQuery para, final BindingResult bindingResult) throws BindException {
        this.logger.info("1:1比对-开始。请求参数:{}", JSON.toJSONString(para));
        final ResponseEntity<String> resp = this.validite(bindingResult);
        JSONObject recog;
        String faceA, faceB;
        try {
            try {
                faceA = imgCheck(para.getFaceA(), para.getTypeA());
            } catch (ServiceException e) {
                throw new ServiceException("上传图片内容错误（图片1）");
            }
            try {
                faceB = imgCheck(para.getFaceB(), para.getTypeB());
            } catch (ServiceException e) {
                throw new ServiceException("上传图片内容错误（图片2）");
            }
            //人脸1:1
            if (!StringUtils.checkSuccess(recog = accessService.getRecogOne(faceA, faceB))) {
                throw new ServiceException(ResultCode.ONETOONE_VPLAT_RSP_ERROR);
            }
            String data = recog.getString("score");
            if(data == null){
                throw new ServiceException(ResultCode.ONETOONE_VPLAT_RSP_ERROR);
            }
            double doubleData = Double.parseDouble(data);
            if(doubleData>1){
                doubleData = doubleData*0.01;
                data = String.valueOf(doubleData);
            }
            resp.setData(data);
        } catch (ServiceException e) {
            this.logger.error("人脸比对 1:1 失败，错误码: {},错误描述：{}", e.getCode(), e.getMessage(), e);
            return createFailResponse(e.getCode(), e.getMessage());
        } catch (Exception e) {
            this.logger.error("人脸比对失败，错误信息：{}", e.getMessage(), e);
            return createFailResponse("人脸比对失败，错误信息：" + e.getMessage());
        }
        this.logger.info("1:1比对-结束。返回数据:{}", JSON.toJSONString(resp));
        return resp;
    }

    private boolean isBase64(String img) {
        return StringUtils.isNotBlank(img) && Base64ImageUtils.isCheckBase64(img, Constants.IMAGE_TYPE.split(CommonConstant.SPLIT_COMMA));
    }

    private String imgCheck(String img, Integer imgType) throws ServiceException {
        // 图片格式处理
        if (imgType.equals(CommonConstant.IMGTYPE_BASE64)) {
            if (!isBase64(img)) {
                throw new ServiceException(ResultCode.ONETOONE_IMAGE_TYPE_ERROR);
            }
        } else if (imgType.equals(CommonConstant.IMGTYPE_URL) || imgType.equals(CommonConstant.IMGTYPE_FULLURL)) {
            String imgUrl = imgType.equals(CommonConstant.IMGTYPE_FULLURL) ? img : FilePropertiesUtil.getHttpUrl() + img;
            try {
                img = FileUtils.getBase64ByUrl(imgUrl);
            } catch (Exception e) {
                this.logger.error("URL转换Base64失败，img=" + img, e);
                img = null;
            }
            if (StringUtils.isBlank(img)) {
                throw new ServiceException(ResultCode.ONETOONE_IMAGE_TYPE_ERROR);
            }
        }
        return img;
    }

}
