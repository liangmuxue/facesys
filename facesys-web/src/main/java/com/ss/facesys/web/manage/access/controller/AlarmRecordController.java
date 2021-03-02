package com.ss.facesys.web.manage.access.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.j7cai.common.util.JsonUtils;
import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.access.common.dto.MonitorTask;
import com.ss.facesys.data.access.common.web.MonVO;
import com.ss.facesys.data.access.mapper.AlarmRecordMapper;
import com.ss.facesys.data.access.mapper.MonMapper;
import com.ss.facesys.data.baseinfo.common.model.BaseEnums;
import com.ss.facesys.data.baseinfo.mapper.EnumMapper;
import com.ss.facesys.data.collect.common.model.*;
import com.ss.facesys.data.collect.mapper.DevicePersoncardMapper;
import com.ss.facesys.data.collect.mapper.FacedbFaceMapper;
import com.ss.facesys.data.collect.mapper.FacedbMapper;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.resource.mapper.CameraMapper;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.SfgoHttpConstant;
import com.ss.facesys.util.em.MonitorTypeEnum;
import com.ss.facesys.util.em.ResourceType;
import com.ss.facesys.util.file.FilePropertiesUtil;
import com.ss.tools.DateUtils;
import com.ss.tools.UUIDUtils;
import com.ss.utils.BaseHttpUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 报警配置信息
 * @author zhangao
 * @date 2021/2/2
 * @email zhangao@ss-cas.com
 */
@RestController
@RequestMapping({"/alarmRecord"})
public class AlarmRecordController {

    private Logger logger = LoggerFactory.getLogger(AlarmInfoController.class);



    



}