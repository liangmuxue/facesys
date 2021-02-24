/*
package com.ss.facesys.web.manage.access.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.access.common.dto.MonitorDTO;
import com.ss.facesys.data.access.common.web.MonitorVO;
import com.ss.facesys.data.baseinfo.client.IEnumService;
import com.ss.facesys.data.baseinfo.common.model.BaseEnums;
import com.ss.facesys.data.baseinfo.common.web.Device;
import com.ss.facesys.data.collect.client.IFacedbService;
import com.ss.facesys.data.collect.common.model.Facedb;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.em.Enums.cameraType;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.ResponseEntity;
import com.ss.valide.*;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

*/
/**
 * MonitorController 布控请求
 * @author FrancisYs
 * @date 2019/8/06
 * @update 2019/8/22
 * @email yaoshuai@ss-cas.com
 *//*

@RestController
@RequestMapping({"/monitor"})
public class MonitorController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(MonitorController.class);

    @Resource
    private IAccessService accessService;
    @Resource
    private IFacedbService facedbService;
    @Resource
    private IEnumService enumService;


    */
/**
     * 查询布控分页列表
     * @param monitor
     * @param bindingResult
     * @return
     * @throws Exception
     *//*

    @RequestMapping(value = {"/page"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "查询布控分页列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Map<String, Object>> getMonitorPage(@RequestBody @Validated(APIPageGroup.class) MonitorVO monitor, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            Map<String, Object> map = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
            // 转换人像库标识类型条件为底库编号数组
            if (StringUtils.isNotBlank(monitor.getType())) {
                Facedb facedb = new Facedb();
                facedb.setModel(CommonConstant.FACEDB_MODEL_SPECIAL);
                facedb.setType(monitor.getType());
                List<Facedb> facedbList = facedbService.getFacedbList(facedb);
                if (CollectionUtils.isNotEmpty(facedbList)) {
                    monitor.setFacegroupIds(facedbList.stream().map(Facedb::getFacedbId).collect(Collectors.toList()));
                }
            }
            // 调用欧神接口查询布控分页列表
            JSONObject jsonObject = this.accessService.getMonitorPages(JSONObject.toJSONString(monitor));
            this.logger.info("查询布控分页列表返回结果" + jsonObject.toString());
            // 请求成功封装布控集合
            if (StringUtils.checkSuccess(jsonObject)) {
                map = jsonObject.getJSONObject("data");
                JSONArray datas = (JSONArray) (map.get("datas"));
                if (CollectionUtils.isNotEmpty(datas)) {
                    for (Object data : datas) {
                        JSONObject m = (JSONObject) data;
                        m.put("monitorTypeDesc", getName("MONITOR_TYPE", m.getInteger("monitorType")));
                        m.put("monitorStatusDesc", getName("MONITOR_STATUS", m.getInteger("monitorStatus")));
                        JSONArray groupArray = JSONArray.parseArray(JSON.toJSONString(m.get("groups")));
                        if (CollectionUtils.isNotEmpty(groupArray)) {
                            List<String> facegroupName = new ArrayList<>();
                            for (Object group : groupArray) {
                                facegroupName.add(JSONObject.parseObject(JSON.toJSONString(group)).getString("facegroupName"));
                            }
                            m.put("facegroupName", StringUtils.join(facegroupName, CommonConstant.SPLIT_COMMA));
                        }
                    }
                }
            } else {
                String message = "查询布控分页列表失败，原因：" + jsonObject.getString("message");
                this.logger.info(message);
                resp = createFailResponse();
                resp.setCode(ResultCode.MONITOR_PAGE_FAILED_CODE);
                resp.setMessage(message);
            }
            resp.setData(map);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.MONITOR_PAGE_FAILED_CODE);
            this.logger.error("查询布控分页列表失败，原因：" + e.toString(), e);
            resp.setMessage("查询布控列表失败");
        }
        return resp;
    }

    */
/**
     * 查询布控列表
     * @param monitorDTO
     * @param bindingResult
     * @return
     * @throws Exception
     *//*

    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "查询布控列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Map<String, Object>> getMonitorlist(@RequestBody MonitorDTO monitorDTO, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            List<MonitorDTO> monitorList = new ArrayList<>();
            Map<String, Object> map = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
            // 调用欧神接口查询布控列表
            JSONObject jsonObject = this.accessService.getMonitorList(JSONObject.toJSONString(monitorDTO));
            this.logger.info("查询布控列表返回结果" + jsonObject.toString());
            // 请求成功封装布控集合
            if (StringUtils.checkSuccess(jsonObject)) {
                monitorList = JSONArray.parseArray(jsonObject.getString("data"), MonitorDTO.class);
            } else {
                String message = "查询布控列表失败，原因：" + jsonObject.getString("message");
                this.logger.info(message);
                resp = createFailResponse();
                resp.setCode(ResultCode.MONITOR_LIST_FAILED_CODE);
                resp.setMessage(message);
            }
            map.put("monitorList", monitorList);
            resp.setData(map);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.MONITOR_LIST_FAILED_CODE);
            this.logger.error("查询布控列表失败，原因：" + e.toString(), e);
            resp.setMessage("查询布控列表失败");
        }
        return resp;
    }

    */
/**
     * 查询布控详情
     * @param monitor
     * @param bindingResult
     * @return
     * @throws Exception
     *//*

    @RequestMapping(value = {"/detail"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "查询布控详细信息", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Map<String, Object>> getMonitorDetail(@RequestBody @Validated(APIGetsGroup.class) MonitorVO monitor, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            Map<String, Object> map = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
            map.put("monitor", null);
            // 调用欧神接口查询布控明细
            JSONObject jsonObject = this.accessService.getMonitorDetail(monitor.getMonitorId());
            this.logger.info("查询布控明细返回结果" + jsonObject.toString());
            // 请求成功封装布控对象
            if (StringUtils.checkSuccess(jsonObject)) {
                MonitorDTO monitorDTO = JSONObject.parseObject(jsonObject.getString("data"), MonitorDTO.class);
                JSONArray facegroupId = new JSONArray();
                JSONArray groupArray = jsonObject.getJSONObject("data").getJSONArray("groups");
                if (CollectionUtils.isNotEmpty(groupArray)) {
                    for (Object group : groupArray) {
                        facegroupId.add(JSONObject.parseObject(JSON.toJSONString(group)).getString("facegroupId"));
                    }
                }
                monitorDTO.setFacegroupId(facegroupId);
                map.put("monitor", monitorDTO);
            } else {
                String message = "查询布控明细失败，原因：" + jsonObject.getString("message");
                this.logger.info(message);
                resp = createFailResponse();
                resp.setCode(ResultCode.MONITOR_DETAIL_FAILED_CODE);
                resp.setMessage(message);
            }
            resp.setData(map);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.MONITOR_DETAIL_FAILED_CODE);
            this.logger.error("查询布控明细失败，原因：" + e.toString(), e);
            resp.setMessage("查询布控明细失败");
        }
        return resp;
    }

    private void cameraType2DeviceType (List<Device> devices) {
        if (CollectionUtils.isNotEmpty(devices)) {
            for (Device device: devices) {
                if (device != null) {
                    if (device.getDeviceType() == cameraType.USUAL.getCode() || device.getDeviceType() == cameraType.UNKNOWN.getCode()) {
                        device.setDeviceType(CommonConstant.OCEAN_DEVICE_TYPE_USUAL);
                    } else if (device.getDeviceType() == cameraType.CRED.getCode()) {
                        device.setDeviceType(CommonConstant.OCEAN_DEVICE_TYPE_CERD);
                    } else {
                        device.setDeviceType(CommonConstant.OCEAN_DEVICE_TYPE_TERMINAL);
                    }
                }
            }
        }
    }

    */
/**
     * 新增布控任务
     * @param monitor
     * @param bindingResult
     * @return
     * @throws Exception
     *//*

    @RequestMapping(value = {"/insert"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "新增布控信息", type = OperaTypeEnum.ADD)
    public ResponseEntity<Map<String, Object>> insertMonitor(@RequestBody @Validated(APIAddGroup.class) MonitorVO monitor, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            // 参数校验-时间类型（自定义校验）
            if (monitor.getMonitorTimeType() == CommonConstant.MONITOR_TIME_TYPE_CUSTOM && StringUtils.isBlank(String.valueOf(monitor.getMonitorTimeE()))) {
                resp = createFailResponse(ResultCode.MONITOR_ADD_FAILED_CODE, "自定义时间类型下：布控结束时间不能为空！");
                return resp;
            }
            // 人像库条件校验
            if (monitor.getMonitorType() != CommonConstant.MONITOR_TYPE_BLACK_GATHER && CollectionUtils.isEmpty(monitor.getFacegroupId())) {
                resp = createFailResponse(ResultCode.MONITOR_ADD_FAILED_CODE, "人像库编号集合不能为空！");
                return resp;
            }
            // 像机类型与欧神设备类型映射
            cameraType2DeviceType(monitor.getDevices());
            // 调用欧神接口新增布控
            JSONObject jsonObject = this.accessService.insertMonitor(JSONObject.toJSONString(monitor));
            this.logger.info("新增布控返回结果" + jsonObject.toString());
            // 请求成功打印新增信息的ID
            if (StringUtils.checkSuccess(jsonObject)) {
                String message = "成功新增一条布控信息，id为：" + jsonObject.getString("data");
                this.logger.info(message);
                resp.setMessage(message);
            } else {
                String message = "新增布控失败，原因：" + jsonObject.getString("message");
                this.logger.info(message);
                resp = createFailResponse();
                resp.setCode(ResultCode.MONITOR_ADD_FAILED_CODE);
                resp.setMessage(message);
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.MONITOR_ADD_FAILED_CODE);
            this.logger.error("新增布控失败，原因：" + e.toString(), e);
            resp.setMessage("新增布控失败");
        }
        return resp;
    }

    */
/**
     * 删除布控任务
     * @param monitor
     * @param bindingResult
     * @return
     * @throws Exception
     *//*

    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "删除布控信息", type = OperaTypeEnum.DELETE)
    public ResponseEntity<Map<String, Object>> deleteMonitor(@RequestBody @Validated(APIDeltGroup.class) MonitorVO monitor, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            // 欧神中布控有两个删除接口：单个删除、批量删除，此处为单个删除
            String monitorId = monitor.getMonitorId();
            this.logger.info("将要删除id为：" + monitorId + "的布控信息");
            // 调用欧神接口删除布控
            JSONObject jsonObject = this.accessService.deleteMonitor(monitorId);
            this.logger.info("删除布控返回结果" + jsonObject.toString());
            if (StringUtils.checkSuccess(jsonObject)) {
                String message = "删除了id为：" + monitorId + "的布控信息";
                this.logger.info(message);
                resp.setMessage(message);
            } else {
                String message = "删除布控失败，原因：" + jsonObject.getString("message");
                this.logger.info(message);
                resp = createFailResponse();
                resp.setCode(ResultCode.MONITOR_DELETE_FAILED_CODE);
                resp.setMessage(message);
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.MONITOR_DELETE_FAILED_CODE);
            this.logger.error("删除布控失败，原因：" + e.toString(), e);
            resp.setMessage("删除布控失败");
        }
        return resp;
    }

    */
/**
     * 修改布控信息
     * @param monitor
     * @param bindingResult
     * @return
     * @throws Exception
     *//*

    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "修改布控信息", type = OperaTypeEnum.EDIT)
    public ResponseEntity<Map<String, Object>> updateMonitor(@RequestBody @Validated(APIEditGroup.class) MonitorVO monitor, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            // 参数校验-时间类型（自定义校验）
            if (monitor.getMonitorTimeType() == CommonConstant.MONITOR_TIME_TYPE_CUSTOM && StringUtils.isBlank(String.valueOf(monitor.getMonitorTimeE()))) {
                resp = createFailResponse(ResultCode.MONITOR_EDIT_FAILED_CODE, "自定义时间类型下：布控结束时间不能为空！");
                return resp;
            }
            // 人像库条件校验
            if (monitor.getMonitorType() != CommonConstant.MONITOR_TYPE_BLACK_GATHER && CollectionUtils.isEmpty(monitor.getFacegroupId())) {
                resp = createFailResponse(ResultCode.MONITOR_EDIT_FAILED_CODE, "人像库编号集合不能为空！");
                return resp;
            }
            // 像机类型与欧神设备类型映射
            cameraType2DeviceType(monitor.getDevices());
            // 调用欧神接口修改布控
            JSONObject jsonObject = this.accessService.updateMonitor(JSONObject.toJSONString(monitor));
            this.logger.info("修改布控返回结果" + jsonObject.toString());
            // 请求成功打印修改信息的ID
            if (StringUtils.checkSuccess(jsonObject)) {
                String message = "成功修改了id为：" + monitor.getMonitorId() + "的布控信息";
                this.logger.info(message);
                resp.setMessage(message);
            } else {
                String message = "修改布控失败，原因：" + jsonObject.getString("message");
                this.logger.info(message);
                resp = createFailResponse();
                resp.setCode(ResultCode.MONITOR_EDIT_FAILED_CODE);
                resp.setMessage(message);
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.MONITOR_EDIT_FAILED_CODE);
            this.logger.error("修改布控失败，原因：" + e.toString(), e);
            resp.setMessage("修改布控失败");
        }
        return resp;
    }

    */
/**
     * 修改布控启动暂停状态
     * @param monitor
     * @param bindingResult
     * @return
     * @throws Exception
     *//*

    @RequestMapping(value = {"/updateStatus"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "修改布控启动暂停状态", type = OperaTypeEnum.EDIT)
    public ResponseEntity<Map<String, Object>> updateMonitorStatus(@RequestBody @Validated(APIKeyStateGroup.class) MonitorVO monitor, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            String monitorId = monitor.getMonitorId();
            int monitorStatus = monitor.getMonitorStatus();
            // 调用欧神接口修改布控状态
            JSONObject jsonObject = this.accessService.updateMonitorStatus(monitorId, monitorStatus);
            this.logger.info("修改布控状态返回结果" + jsonObject.toString());
            // 请求成功打印修改信息的ID
            if (StringUtils.checkSuccess(jsonObject)) {
                String message = "成功" + (monitorStatus == 1 ? "启用" : "停用") + "id为：" + monitorId + "的布控任务";
                this.logger.info(message);
                resp.setMessage(message);
            } else {
                String message = "修改布控状态失败，原因：" + jsonObject.getString("message");
                this.logger.info(message);
                resp = createFailResponse();
                resp.setCode(ResultCode.MONITOR_STATUS_EDIT_FAILED_CODE);
                resp.setMessage(message);
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.MONITOR_STATUS_EDIT_FAILED_CODE);
            this.logger.error("修改布控状态失败，原因：" + e.toString(), e);
            resp.setMessage("修改布控状态失败");
        }
        return resp;
    }


    private String getName(String enumType, Integer enumValue) {
        BaseEnums baseEnums = new BaseEnums();
        baseEnums.setEnumType(enumType);
        List<BaseEnums> list = enumService.findList(baseEnums);
        return list.stream().filter(enums -> enums.getEnumValue().equals(enumValue)).collect(Collectors.toList()).get(0).getEnumName();
    }

}
*/
