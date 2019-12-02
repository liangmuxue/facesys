package com.ss.isc.data.archives.service;

import com.alibaba.fastjson.JSONObject;
import com.ss.isc.data.archives.client.ISubscribeService;
import com.ss.isc.data.collect.common.web.SubscribeVO;
import com.ss.isc.data.viid.common.from.SubscribeBaseForm;
import com.ss.isc.data.viid.common.from.SubscribeListObjectForm;
import com.ss.isc.data.viid.common.from.SubscribeObjectForm;
import com.ss.isc.data.viid.common.util.BaseHttpUtil;
import com.ss.isc.data.viid.mapper.SubscribeMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * VIID 订阅
 * @author 李爽超 chao
 * @create 2019/10/28
 * @email lishuangchao@ss-cas.com
 **/
@Service
public class SubscribeServiceImpl implements ISubscribeService {

    @Autowired
    private SubscribeMapper subscribeMapper;
    @Override
    public String add(SubscribeVO subscribeVO) {
        String message;
        String response = null;
        boolean result = false;
        boolean isNew = true;
        //验证订阅是否已存在
        SubscribeBaseForm check = this.subscribeMapper.check(subscribeVO.getSubscribeId());
        if (check != null && check.getSubscribeStatus() == 0){
            return "订阅标识符已存在!";
        } else if (check != null && check.getSubscribeStatus() != 0){
            isNew = false;
        }
        final SubscribeListObjectForm subscribeListObjectForm = this.switchObject(subscribeVO);
        if (subscribeListObjectForm.getSubscribeListObject() != null && CollectionUtils.isNotEmpty(subscribeListObjectForm.getSubscribeListObject().getSubscribeObject())) {
            final String reqPara = JSONObject.toJSONString(subscribeListObjectForm);
            response = BaseHttpUtil.viidHttpPost(reqPara, "/VIID/Dispositions");
        }
        if (response != null){
            result = BaseHttpUtil.checkListSuccess(response);
        }
        if (result){
            subscribeVO.setSubscribeStatus(0);
            if (isNew){
                this.subscribeMapper.add(subscribeVO);
            } else {
                this.subscribeMapper.update(subscribeVO);
            }
            message = "VIID 新增订阅成功!";
        } else {
            message = "VIID 新增订阅失败!";
        }
        return message;
    }

    /**
     * VIID 取消订阅
     * @param subscribeVO
     * @return
     */
    @Override
    public String delete(SubscribeVO subscribeVO) {
        String message;
        String response = null;
        boolean result = false;
        //验证订阅是否已存在
        SubscribeBaseForm check = this.subscribeMapper.check(subscribeVO.getSubscribeId());
        if (check == null || check.getSubscribeStatus() != 0){
            return "订阅标识符不存在!";
        }
        final SubscribeObjectForm subscribeObjectForm = this.switchObjectCancel(subscribeVO);
        if (subscribeObjectForm != null) {
            final String reqPara = JSONObject.toJSONString(subscribeObjectForm);
            response = BaseHttpUtil.viidHttpPut(reqPara, "/VIID/Dispositions/" + subscribeVO.getSubscribeId());
        }
        if (response != null){
            result = BaseHttpUtil.checkSuccess(response);
        }
        if (result){
            subscribeVO.setSubscribeStatus(1);
            //取消订阅
            this.subscribeMapper.delete(subscribeVO);
            message = "VIID 取消订阅成功!";
        } else {
            message = "VIID 取消订阅失败!";
        }
        return message;
    }

    /**
     * VIID 修改订阅
     * @param subscribeVO
     * @return
     */
    @Override
    public String update(SubscribeVO subscribeVO) {
        String message;
        String response = null;
        boolean result = false;
        //验证订阅是否已存在
        SubscribeBaseForm check = this.subscribeMapper.check(subscribeVO.getSubscribeId());
        if (check == null || check.getSubscribeStatus() != 0){
            return "订阅标识符不存在!";
        }
        final SubscribeListObjectForm subscribeListObjectForm = this.switchObject(subscribeVO);
        if (subscribeListObjectForm.getSubscribeListObject() != null && CollectionUtils.isNotEmpty(subscribeListObjectForm.getSubscribeListObject().getSubscribeObject())) {
            final String reqPara = JSONObject.toJSONString(subscribeListObjectForm);
            response = BaseHttpUtil.viidHttpPut(reqPara, "/VIID/Dispositions");
        }
        if (response != null){
            result = BaseHttpUtil.checkListSuccess(response);
        }
        if (result){
            subscribeVO.setSubscribeStatus(0);
            //修改订阅
            this.subscribeMapper.update(subscribeVO);
            message = "VIID 修改订阅成功!";
        } else {
            message = "VIID 修改订阅失败!";
        }
        return message;
    }

    /**
     * 生成订阅JSON对象
     * @param subscribeVO
     * @return
     */
    private SubscribeListObjectForm switchObject(final SubscribeVO subscribeVO) {
        final SubscribeListObjectForm subscribeListObjectForm = new SubscribeListObjectForm();
        final SubscribeListObjectForm.SubscribeObject subscribeObject = new SubscribeListObjectForm.SubscribeObject();
        final List<SubscribeBaseForm> subscribeBaseFormList = new ArrayList<SubscribeBaseForm>();
        final SubscribeBaseForm subscribeBaseForm = new SubscribeBaseForm();
        BeanUtils.copyProperties(subscribeVO, subscribeBaseForm);
        subscribeBaseForm.setOperateType(0);
        subscribeBaseFormList.add(subscribeBaseForm);
        subscribeObject.setSubscribeObject(subscribeBaseFormList);
        subscribeListObjectForm.setSubscribeListObject(subscribeObject);
        return subscribeListObjectForm;
    }

    /**
     * 生成取消订阅JSON对象
     * @param subscribeVO
     * @return
     */
    private SubscribeObjectForm switchObjectCancel(SubscribeVO subscribeVO) {
        final SubscribeObjectForm subscribeObjectForm = new SubscribeObjectForm();
        final SubscribeBaseForm subscribeBaseForm = new SubscribeBaseForm();
        BeanUtils.copyProperties(subscribeVO, subscribeBaseForm);
        subscribeBaseForm.setOperateType(1);
        subscribeObjectForm.setSubscribeObject(subscribeBaseForm);
        return subscribeObjectForm;
    }
}
