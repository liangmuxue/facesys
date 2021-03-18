package com.ss.facesys.data.system.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.ss.enums.CollectionTypeEnum;
import com.ss.exception.ServiceException;
import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.baseinfo.common.dto.CompareResultDTO;
import com.ss.facesys.data.baseinfo.common.dto.PersonCaptureDTO;
import com.ss.facesys.data.collect.mapper.SnapRecordMapper;
import com.ss.facesys.data.system.client.IImgCollectionService;
import com.ss.facesys.data.system.common.dto.ImgCollectionResultDTO;
import com.ss.facesys.data.system.common.model.CollectionCaptureDetail;
import com.ss.facesys.data.system.common.model.CollectionPersoncardDetail;
import com.ss.facesys.data.system.common.model.ImgCollection;
import com.ss.facesys.data.system.mapper.CollectionCaptureDetailMapper;
import com.ss.facesys.data.system.mapper.CollectionPersoncardDetailMapper;
import com.ss.facesys.data.system.mapper.ImgCollectionMapper;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.constant.HttpConstant;
import com.ss.facesys.util.em.ResultCode;
import com.ss.facesys.util.file.FilePropertiesUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ImgCollectionServiceImpl
 *
 * @author FrancisYs
 * @date 2020/2/18
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class ImgCollectionServiceImpl implements IImgCollectionService {

    private static final int COLLECTION_DATA_TYPE_CAPTURE = 1;
    private static final int COLLECTION_DATA_TYPE_PERSONCARD = 2;

    @Resource
    private ImgCollectionMapper imgCollectionMapper;
    @Resource
    private CollectionCaptureDetailMapper collectionCaptureDetailMapper;
    @Resource
    private CollectionPersoncardDetailMapper collectionPersoncardDetailMapper;
    @Resource
    private IAccessService accessService;
    @Resource
    private SnapRecordMapper snapRecordMapper;


    @Override
    public List<ImgCollectionResultDTO> pages(ImgCollection query, int currentPage, int pageSize) {

        PageHelper.startPage(currentPage, pageSize);
        List<ImgCollectionResultDTO> resultDTOList = imgCollectionMapper.pages(query);
        if (CollectionUtils.isNotEmpty(resultDTOList)) {
            for (ImgCollectionResultDTO resultDTO : resultDTOList) {
                //除了离线视频分析结果人脸照收藏之外的其他收藏都查询抓拍库
                if(!resultDTO.getDataType().equals(CollectionTypeEnum.OFFLINE)){
                    List<CompareResultDTO> compareResultDTOS = new ArrayList<>();
                    CompareResultDTO compareResultDTO = new CompareResultDTO();
                    compareResultDTO.setUserId(Long.valueOf(resultDTO.getDataId()));
                    compareResultDTOS.add(compareResultDTO);
                    List<PersonCaptureDTO> resultList = snapRecordMapper.getById(compareResultDTOS, null, null, null);
                    if(!resultList.isEmpty()){
                        resultDTO.setDeviceName(resultList.get(0).getDeviceName());
                        resultDTO.setDataCreateTime(resultList.get(0).getCreateTime());
                        resultDTO.setCollectionUrl(FilePropertiesUtil.getHttpUrl() + resultList.get(0).getCaptureUrl());
                        resultDTO.setPanoramaUrl(FilePropertiesUtil.getHttpUrl() + resultList.get(0).getPanoramaUrl());
                        resultDTO.setAge(resultList.get(0).getAge());
                        resultDTO.setGender(resultList.get(0).getGender());
                        resultDTO.setGenderName(resultList.get(0).getGenderName());
                        resultDTO.setNationName(resultList.get(0).getNationName());
                        resultDTO.setGlassesState(resultList.get(0).getGlassesState());
                        resultDTO.setSunGlassesState(resultList.get(0).getSunGlassesState());
                        resultDTO.setMaskState(resultList.get(0).getMaskState());
                    }
                }
                if(resultDTO.getDataType().equals(CollectionTypeEnum.OFFLINE)){
                    //TODO:离线视频分析未做
                }
            }
        }
        return resultDTOList;
    }

    @Override
    public String collect(ImgCollection imgCollection) throws ServiceException {
        // 新增主表信息
        imgCollection.setCollectionTime(System.currentTimeMillis());
        imgCollectionMapper.insertSelective(imgCollection);
        return String.valueOf(imgCollection.getId());
    }

    @Override
    public String remove(ImgCollection imgCollection) throws ServiceException {
        // 删除主表信息
        imgCollectionMapper.deleteByPrimaryKey(imgCollection);
        return "成功取消收藏";
    }

}
