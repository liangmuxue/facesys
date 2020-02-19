package com.ss.facesys.data.system.service;

import com.github.pagehelper.PageHelper;
import com.ss.exception.ServiceException;
import com.ss.facesys.data.system.client.IImgCollectionService;
import com.ss.facesys.data.system.common.model.ImgCollection;
import com.ss.facesys.data.system.mapper.CollectionCaptureDetailMapper;
import com.ss.facesys.data.system.mapper.CollectionPersoncardDetailMapper;
import com.ss.facesys.data.system.mapper.ImgCollectionMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

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

    private static final int PARA_TYPE_IMG = 2;

    @Resource
    private ImgCollectionMapper imgCollectionMapper;
    @Resource
    private CollectionCaptureDetailMapper collectionCaptureDetailMapper;
    @Resource
    private CollectionPersoncardDetailMapper collectionPersoncardDetailMapper;


    @Override
    public List<ImgCollection> pages(ImgCollection query, int currentPage, int pageSize) {
        Example example = new Example(ImgCollection.class);
        example.createCriteria().andEqualTo("userId", query.getUserId());
        if (query.getCollectionTimeMin() != null && query.getCollectionTimeMax() != null) {
            example.and().andBetween("collectionTime", query.getCollectionTimeMin(), query.getCollectionTimeMax());
        }
        example.orderBy("collectionTime").desc();
        PageHelper.startPage(currentPage, pageSize);
        List<ImgCollection> imgCollectionList = imgCollectionMapper.selectByExample(example);
        /*
            TODO 明细内容处理：一并返回
         */
        if (CollectionUtils.isNotEmpty(imgCollectionList)) {

        }
        return imgCollectionList;
    }

    @Override
    public String remove(ImgCollection imgCollection) throws ServiceException {
        // 删除明细表信息 TODO

        // 删除主表信息
        imgCollectionMapper.deleteByPrimaryKey(imgCollection);
        return "成功取消收藏";
    }

}
