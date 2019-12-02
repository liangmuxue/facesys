package com.ss.facesys.data.archives.service;

import com.alibaba.fastjson.JSONObject;
import com.ss.facesys.data.archives.client.IDispositionService;
import com.ss.facesys.data.collect.common.web.DispositionVO;
import com.ss.facesys.data.viid.common.dto.common.Disposition;
import com.ss.facesys.data.viid.common.dto.common.DispositionInfoObject;
import com.ss.facesys.data.viid.common.dto.common.DispositionList;
import com.ss.facesys.data.viid.common.dto.common.DispositionObject;
import com.ss.facesys.data.viid.common.util.BaseHttpUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * VIID 布控
 * @author 李爽超 chao
 * @create 2019/10/31
 * @email lishuangchao@ss-cas.com
 **/
@Service
public class DispositionServiceImpl implements IDispositionService {
    /**
     * VIID 新增布控
     * @param dispositionVO
     * @return
     */
    @Override
    public String add(DispositionVO dispositionVO) {
        String message;
        String response = null;
        boolean result = false;
        final DispositionList dispositionList = this.switchObject(dispositionVO);
        if (dispositionList.getDispositionListObject() != null && CollectionUtils.isNotEmpty(dispositionList.getDispositionListObject().getDispositionObject())) {
            final String reqPara = JSONObject.toJSONString(dispositionList);
            //请求VIID
            response = BaseHttpUtil.viidHttpPost(reqPara, "/VIID/Dispositions");
        }
        if (response != null){
            result = BaseHttpUtil.checkListSuccess(response);
        }
        if (result){
            message = "VIID 新增布控成功!";
        } else {
            message = "VIID 新增布控失败!";
        }
        return message;
    }

    /**
     * VIID 撤控
     * @param dispositionVO
     * @return
     */
    @Override
    public String delete(DispositionVO dispositionVO) {
        String message;
        String response = null;
        boolean result = false;
        final DispositionInfoObject dispositionObject = this.switchObjectCancel(dispositionVO);
        if (dispositionObject != null) {
            final String reqPara = JSONObject.toJSONString(dispositionObject);
            //请求VIID
            response = BaseHttpUtil.viidHttpPut(reqPara, "/VIID/Dispositions" + dispositionVO.getDispositionID());
        }
        if (response != null){
            result = BaseHttpUtil.checkSuccess(response);
        }
        if (result){
            message = "VIID 撤控成功!";
        } else {
            message = "VIID 撤控失败!";
        }
        return message;
    }

    /**
     * 修改布控
     * @param dispositionVO
     * @return
     */
    @Override
    public String edit(DispositionVO dispositionVO) {
        String message;
        String response = null;
        boolean result = false;
        final DispositionList dispositionList = this.switchObject(dispositionVO);
        if (dispositionList.getDispositionListObject() != null && CollectionUtils.isNotEmpty(dispositionList.getDispositionListObject().getDispositionObject())) {
            final String reqPara = JSONObject.toJSONString(dispositionList);
            //请求VIID
            response = BaseHttpUtil.viidHttpPut(reqPara, "/VIID/Dispositions");
        }
        if (response != null){
            result = BaseHttpUtil.checkListSuccess(response);
        }
        if (result){
            message = "VIID 修改布控成功!";
        } else {
            message = "VIID 修改布控失败!";
        }
        return message;
    }

    /**
     * 生成布控JSON对象
     * @param dispositionVO
     * @return
     */
    private DispositionList switchObject(final DispositionVO dispositionVO) {
        final DispositionList dispositionListObject = new DispositionList();
        final DispositionObject dispositionObject = new DispositionObject();
        final List<Disposition> dispositionList = new ArrayList<>();
        final Disposition disposition = new Disposition();
        BeanUtils.copyProperties(dispositionVO, disposition);
        //设置操作类型为布控
        disposition.setOperateType("0");
        dispositionList.add(disposition);
        dispositionObject.setDispositionObject(dispositionList);
        dispositionListObject.setDispositionListObject(dispositionObject);
        return dispositionListObject;
    }

    /**
     * 生成撤控JSON对象
     * @param dispositionVO
     * @return
     */
    private DispositionInfoObject switchObjectCancel(final DispositionVO dispositionVO) {
        final DispositionInfoObject dispositionObject = new DispositionInfoObject();
        final Disposition disposition = new Disposition();
        BeanUtils.copyProperties(dispositionVO, disposition);
        //设置操作类型为撤控
        disposition.setOperateType("1");
        dispositionObject.setDispositionObject(disposition);
        return dispositionObject;
    }

}
