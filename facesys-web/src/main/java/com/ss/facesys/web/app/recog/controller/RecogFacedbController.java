package com.ss.facesys.web.app.recog.controller;

import com.alibaba.fastjson.JSONObject;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.enums.StatusEnum;
import com.ss.exception.ServiceException;
import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.access.common.dto.FacedbfaceDTO;
import com.ss.facesys.data.baseinfo.common.dto.CompareResultDTO;
import com.ss.facesys.data.collect.client.IFacedbService;
import com.ss.facesys.data.collect.common.model.Facedb;
import com.ss.facesys.data.collect.common.model.FacedbFace;
import com.ss.facesys.data.collect.mapper.FacedbFaceMapper;
import com.ss.facesys.data.collect.mapper.FacedbMapper;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.em.Enums;
import com.ss.facesys.util.em.ResourceType;
import com.ss.facesys.util.em.ResultCode;
import com.ss.facesys.util.file.FilePropertiesUtil;
import com.ss.facesys.util.http.BaseFormatJsonUtil;
import com.ss.facesys.web.app.recog.query.RecogFacedbQuery;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.response.ResponseEntity;
import com.ss.spider.log.constants.ModuleCode;
import com.ss.spider.system.organization.model.Organization;
import com.ss.spider.system.organization.service.OrganizationService;
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
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 1:N 注册库检索
 *
 * @author FrancisYs
 * @date 2019/12/20
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/recog"})
public class RecogFacedbController extends BaseController {

    @Resource
    private IAccessService accessService;
    @Resource
    private IFacedbService facedbService;
    @Resource
    private FacedbFaceMapper facedbFaceMapper;
    @Resource
    private FacedbMapper facedbMapper;
    @Resource
    private OrganizationService<Organization> organizationService;

    @PostMapping(value = {"/registerDb"})
    @OpLog(model = ModuleCode.BUSINESS, desc = "1:N 注册库检索", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<Map<String, Object>> recog(@RequestBody @Validated RecogFacedbQuery facedbQuery, BindingResult bindingResult) throws BindException {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            paramCheck(facedbQuery);
            facedbCheck(facedbQuery);
            // 请求汇聚平台
            JSONObject params = new JSONObject();
            //根据条件决定传参是特征值字符串还是图片
            params.put("img", facedbQuery.getImg());
            List<String> vplatFacedbIds = facedbQuery.getVplatFacedbIds();
            params.put("groupId", StringUtils.join(vplatFacedbIds, ","));
            if (facedbQuery.getTopN() != null) {
                params.put("topN", facedbQuery.getTopN());
            }
            if (facedbQuery.getThresholdMin() != null && facedbQuery.getThresholdMax() != null) {
                List<String> strings = new ArrayList<>();
                strings.add(String.valueOf(facedbQuery.getThresholdMin()));
                strings.add(String.valueOf(facedbQuery.getThresholdMax()));
                params.put("thresholdScore", strings);
            }
            if(facedbQuery.getAgeB() != null) {
                params.put("bTime", facedbQuery.getAgeB()/1000);
            } else {
                params.put("bTime", -1);
            }
            if (facedbQuery.getAgeE() != null) {
                params.put("eTime", facedbQuery.getAgeE()/1000);
            }
            JSONObject oceanResult;
            try {
                oceanResult = this.accessService.getRecogRegisterDb(getVplatParam(facedbQuery));
                if (!StringUtils.checkSuccess(oceanResult)) {
                    throw new ServiceException(oceanResult.getString("result"), oceanResult.getString("message"));
                }
            } catch (ServiceException e) {
                throw e;
            } catch (Exception e) {
                throw new ServiceException(ResultCode.RECOG_FACEDB_VPLAT_FAIL);
            }

            List<CompareResultDTO> resultList1 = BaseFormatJsonUtil.formatList(oceanResult.get("faces"), CompareResultDTO.class);
            if(resultList1 == null  || resultList1.isEmpty()){
                resp.setData(assemblePage(new ArrayList<>(), null, null));
                return resp;
            }
            // 将结果转换为数据传输对象
            List<String> faceIdList = new ArrayList<>();
            for(CompareResultDTO c:resultList1){
                faceIdList.add(String.valueOf(c.getUserId()));
            }
            Example ffe = new Example(FacedbFace.class);
            ffe.createCriteria().andEqualTo("status", StatusEnum.EFFECT.getCode()).andIn("faceId",faceIdList);
            List<FacedbFace> facedbFaces = facedbFaceMapper.selectByExample(ffe);
            if(facedbFaces.isEmpty()){
                resp.setData(assemblePage(new ArrayList<>(), null, null));
                return resp;
            }
            //封装信息
            for(FacedbFace f:facedbFaces){
                for(CompareResultDTO c:resultList1){
                    if(String.valueOf(c.getUserId()).equals(f.getFaceId())){
                        f.setRecogScore(c.getScore());
                        continue;
                    }
                }
                if(f.getFacedbId()!=null){
                    Facedb facedb = facedbMapper.selectByPrimaryKey(f.getFacedbId());
                    Organization organization = this.organizationService.get(facedb.getOrgId());
                    f.setOrgName(organization.getOrgCname());
                }
                if(f.getGender()!=null && f.getGender() == Enums.Sex.SEX_MAN.getCode()){
                    f.setGenderName(Enums.Sex.SEX_MAN.getName());
                }
                if(f.getGender()!=null && f.getGender() == Enums.Sex.SEX_WOMEN.getCode()){
                    f.setGenderName(Enums.Sex.SEX_WOMEN.getName());
                }
                if(f.getGender()!=null && f.getGender() == Enums.Sex.SEX_UNKONW.getCode()){
                    f.setGenderName(Enums.Sex.SEX_UNKONW.getName());
                }
                if(StringUtils.isNotBlank(f.getBirthday())) {
                    Date date = new Date();
                    DateFormat formats = new SimpleDateFormat("yyyy");
                    String format = formats.format(date);
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
                    String sd = sdf.format(new Date(Long.parseLong(String.valueOf(f.getBirthday()))));
                    f.setAge(Integer.parseInt(format) - Integer.parseInt(sd));
                }else{
                    f.setAge(null);
                }
            }
            if (CollectionUtils.isNotEmpty(facedbFaces)) {
                Collections.sort(facedbFaces, new Comparator<FacedbFace>() {
                    @Override
                    public int compare(FacedbFace o1, FacedbFace o2) {
                        if (o1.getRecogScore() > o2.getRecogScore() ) {
                            return -1;
                        }
                        if (o1.getRecogScore().equals(o2.getRecogScore())) {
                            return 0;
                        }
                        return 1;
                    }
                });
            }
            resp.setData(assemblePage(facedbFaces, null, null));
        } catch (ServiceException e) {
            this.logger.error("1:N 抓拍库检索失败，错误码：{}，异常信息：{}", e.getCode(), e.getMessage(), e);
            return createFailResponse(e);
        }
        return resp;
    }

    private void paramCheck(RecogFacedbQuery facedbQuery) throws ServiceException {
        // 图片格式处理
        if (facedbQuery.getImgType().equals(CommonConstant.IMGTYPE_BASE64)) {
            if (!Base64ImageUtils.isCheckBase64(facedbQuery.getImg())) {
                throw new ServiceException(ResultCode.ONETOONE_IMAGE_TYPE_ERROR);
            }
            facedbQuery.setImgType(CommonConstant.IMGTYPE_BASE64);
        } else if (facedbQuery.getImgType().equals(CommonConstant.IMGTYPE_URL) || facedbQuery.getImgType().equals(CommonConstant.IMGTYPE_FULLURL)) {
            String imgUrl = facedbQuery.getImgType().equals(CommonConstant.IMGTYPE_FULLURL) ? facedbQuery.getImg() : FilePropertiesUtil.getHttpUrl() + facedbQuery.getImg();
            String img = null;
            try {
                img = FileUtils.getBase64ByUrl(imgUrl);
            } catch (Exception e) {
                this.logger.error("URL转换Base64失败，img=" + facedbQuery.getImg(), e);
            }
            if (StringUtils.isBlank(img)) {
                throw new ServiceException(ResultCode.ONETOONE_IMAGE_TYPE_ERROR);
            }
            facedbQuery.setImg(img);
        }
        // topN 处理
        facedbQuery.setTopN(PropertiesUtil.getTopN());
        // 相似度范围处理
        if (facedbQuery.getThresholdMin() == null) {
            facedbQuery.setThresholdMin(PropertiesUtil.getThreshold());
        }
        if (facedbQuery.getThresholdMax() == null) {
            facedbQuery.setThresholdMax(1.0);
        }
    }

    /**
     * 校验人像库权限并获取汇聚平台设备ID参数
     *
     * @param facedbQuery
     * @throws ServiceException
     */
    private void facedbCheck(RecogFacedbQuery facedbQuery) throws ServiceException {
        facedbQuery.setFacedbIds(getAuthResources(facedbQuery.getUserId(), ResourceType.FACEDB, facedbQuery.getFacedbIds()));
        Example example = new Example(Facedb.class);
        example.createCriteria().andIn("id", facedbQuery.getFacedbIds()).andEqualTo("status", StatusEnum.EFFECT.getCode());
        List<Facedb> facedbs = facedbService.selectByExample(example);
        facedbQuery.setVplatFacedbIds(facedbs.stream().map(Facedb::getFacedbId).collect(Collectors.toList()));
    }

    private String getVplatParam(RecogFacedbQuery facedbQuery) {
        JSONObject param = new JSONObject();
        param.put("img", facedbQuery.getImg());
        param.put("imgType", CommonConstant.IMGTYPE_BASE64);
        param.put("facedbIds", facedbQuery.getVplatFacedbIds());
        param.put("gender", facedbQuery.getGender());
        param.put("ageB", facedbQuery.getAgeB());
        param.put("ageE", facedbQuery.getAgeE());
        param.put("nation", facedbQuery.getNation());
        param.put("topN", facedbQuery.getTopN());
        param.put("thresholdMin", facedbQuery.getThresholdMin());
        param.put("thresholdMax", facedbQuery.getThresholdMax());
        return param.toJSONString();
    }

    private List<FacedbfaceDTO> filterCondition(List<FacedbfaceDTO> resultList, RecogFacedbQuery facedbQuery) {
        List<FacedbfaceDTO> filter = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(resultList)) {
            String name, cardId;
            for (FacedbfaceDTO facedbfaceDTO : resultList) {
                boolean nameValid = StringUtils.isBlank(name = facedbQuery.getName()) || facedbfaceDTO.getName().contains(name);
                boolean cardIdValid = StringUtils.isBlank(cardId = facedbQuery.getCardId()) || facedbfaceDTO.getCardId().contains(cardId);
                if (nameValid && cardIdValid) {
                    filter.add(facedbfaceDTO);
                }
            }
        }
        return filter;
    }

    private Map<String, Object> assemblePage(List<FacedbFace> resultList, Integer currentPage, Integer pageSize) {
        Map<String, Object> resultMap = new HashMap<>(1);
        resultMap.put("recogList", resultList);
        return resultMap;
    }

}
