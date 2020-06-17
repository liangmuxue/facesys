package com.ss.facesys.data.system.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.ss.exception.ServiceException;
import com.ss.facesys.data.access.client.IAccessService;
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


    @Override
    public List<ImgCollectionResultDTO> pages(ImgCollection query, int currentPage, int pageSize) {
        // 获取有效的抓拍库、离线视频收藏数据的明细集合
        Example captureDataExample = new Example(CollectionCaptureDetail.class);
        captureDataExample.createCriteria().andIn("deviceId", CollectionUtils.isNotEmpty(query.getCaptureDeviceIds()) ? query.getCaptureDeviceIds() : Collections.singleton("-1"));
        List<CollectionCaptureDetail> collectionCaptureDetailList = collectionCaptureDetailMapper.selectByExample(captureDataExample);
        List<Integer> captureDataIds = collectionCaptureDetailList.stream().map(CollectionCaptureDetail::getId).collect(Collectors.toList());
        Map<Integer, CollectionCaptureDetail> captureDataMap = collectionCaptureDetailList.stream().collect(Collectors.toMap(CollectionCaptureDetail::getId, Function.identity()));
        // 获取有效的人证库收藏数据的明细集合
        Example personcardDataExample = new Example(CollectionPersoncardDetail.class);
        personcardDataExample.createCriteria().andIn("deviceId", CollectionUtils.isNotEmpty(query.getPersoncardDeviceIds()) ? query.getPersoncardDeviceIds() : Collections.singleton("-1"));
        List<CollectionPersoncardDetail> collectionPersoncardDetailList = collectionPersoncardDetailMapper.selectByExample(personcardDataExample);
        List<Integer> personcardDataIds = collectionPersoncardDetailList.stream().map(CollectionPersoncardDetail::getId).collect(Collectors.toList());
        Map<Integer, CollectionPersoncardDetail> personcardDataMap = collectionPersoncardDetailList.stream().collect(Collectors.toMap(CollectionPersoncardDetail::getId, Function.identity()));
        // 整合查询条件
        query.setCaptureDataIds(StringUtils.join(captureDataIds, CommonConstant.SPLIT_COMMA));
        query.setPersoncardDataIds(StringUtils.join(personcardDataIds, CommonConstant.SPLIT_COMMA));
        PageHelper.startPage(currentPage, pageSize);
        List<ImgCollectionResultDTO> resultDTOList = imgCollectionMapper.pages(query);
        if (CollectionUtils.isNotEmpty(resultDTOList)) {
            for (ImgCollectionResultDTO resultDTO : resultDTOList) {
                CollectionPersoncardDetail personcardDetail;
                CollectionCaptureDetail captureDetail;
                if (resultDTO.getDataType() == COLLECTION_DATA_TYPE_PERSONCARD && (personcardDetail = personcardDataMap.get(resultDTO.getDataId())) != null) {
                    personcardDetail.setCaptureUrl(FilePropertiesUtil.getNginxImgUrl() + personcardDetail.getCaptureUrl());
                    personcardDetail.setCardUrl(FilePropertiesUtil.getNginxImgUrl() + personcardDetail.getCardUrl());
                    resultDTO.setCollectionPersoncardDetail(personcardDetail);
                    resultDTO.setCollectionUrl(personcardDetail.getCaptureUrl());
                    resultDTO.setDeviceName(personcardDetail.getDeviceName());
                    resultDTO.setDataCreateTime(personcardDetail.getCaptureTime());
                } else if ((captureDetail = captureDataMap.get(resultDTO.getDataId())) != null) {
                    captureDetail.setCaptureUrl(FilePropertiesUtil.getNginxImgUrl() + captureDetail.getCaptureUrl());
                    captureDetail.setPanoramaUrl(FilePropertiesUtil.getNginxImgUrl() + captureDetail.getPanoramaUrl());
                    resultDTO.setCollectionCaptureDetail(captureDetail);
                    resultDTO.setCollectionUrl(captureDetail.getCaptureUrl());
                    resultDTO.setDeviceName(captureDetail.getDeviceName());
                    resultDTO.setDataCreateTime(captureDetail.getCaptureTime());
                }
            }
        }
        return resultDTOList;
    }

    @Override
    public String collect(ImgCollection imgCollection) throws ServiceException {
        // 新增明细表信息
        Integer dataId;
        String requestUrl = imgCollection.getDataType() == COLLECTION_DATA_TYPE_CAPTURE ? HttpConstant.CAMERA_CAPTURE_GET :
                imgCollection.getDataType() == COLLECTION_DATA_TYPE_PERSONCARD ? HttpConstant.PERSONCARD_COLLECT_GET : HttpConstant.VIDEO_CAPTURE_GET;
        JSONObject param = new JSONObject();
        param.put("captureId", imgCollection.getCaptureId());
        JSONObject detailObject = accessService.request(param.toString(), requestUrl);
        if (!StringUtils.checkSuccess(detailObject)) {
            throw new ServiceException(ResultCode.COLLECT_VPLAT_GET_FAIL);
        }
        if (imgCollection.getDataType() == COLLECTION_DATA_TYPE_PERSONCARD) {
            CollectionPersoncardDetail collectionPersoncardDetail = detailObject.getJSONObject("data").toJavaObject(CollectionPersoncardDetail.class);
            collectionPersoncardDetail.setValiddate(detailObject.getString("validdateStart") + "-" + detailObject.getString("validdateEnd"));
            collectionPersoncardDetailMapper.insertSelective(collectionPersoncardDetail);
            dataId = collectionPersoncardDetail.getId();
        } else {
            CollectionCaptureDetail collectionCaptureDetail = detailObject.getJSONObject("data").toJavaObject(CollectionCaptureDetail.class);
            collectionCaptureDetailMapper.insertSelective(collectionCaptureDetail);
            dataId = collectionCaptureDetail.getId();
        }
        // 新增主表信息
        imgCollection.setDataId(dataId);
        imgCollection.setCollectionTime(System.currentTimeMillis());
        imgCollectionMapper.insertSelective(imgCollection);
        return String.valueOf(imgCollection.getId());
    }

    @Override
    public String remove(ImgCollection imgCollection) throws ServiceException {
        ImgCollection original = imgCollectionMapper.selectByPrimaryKey(imgCollection);
        Integer dataId = original.getDataId();
        // 删除明细表信息
        if (original.getDataType() == COLLECTION_DATA_TYPE_PERSONCARD) {
            CollectionPersoncardDetail collectionPersoncardDetail = new CollectionPersoncardDetail();
            collectionPersoncardDetail.setId(dataId);
            collectionPersoncardDetailMapper.deleteByPrimaryKey(collectionPersoncardDetail);
        } else {
            CollectionCaptureDetail collectionCaptureDetail = new CollectionCaptureDetail();
            collectionCaptureDetail.setId(dataId);
            collectionCaptureDetailMapper.deleteByPrimaryKey(collectionCaptureDetail);
        }
        // 删除主表信息
        imgCollectionMapper.deleteByPrimaryKey(imgCollection);
        return "成功取消收藏";
    }

}
