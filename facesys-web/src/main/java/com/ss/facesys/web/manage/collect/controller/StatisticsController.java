package com.ss.facesys.web.manage.collect.controller;

import com.ss.annotation.OpLog;
import com.ss.controller.AbstractController;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.baseinfo.common.web.BaseQueryEntity;
import com.ss.facesys.data.collect.client.ICaptureService;
import com.ss.facesys.data.collect.client.IStatisticsService;
import com.ss.facesys.data.collect.common.dto.CaptureCountDTO;
import com.ss.facesys.data.collect.common.dto.CaptureSumDTO;
import com.ss.facesys.data.collect.common.dto.RegisterStatisticsDTO;
import com.ss.facesys.data.collect.common.dto.Tendency;
import com.ss.facesys.data.resource.client.ICommunityResourceService;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.jedis.JedisUtil;
import com.ss.response.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"/statistics"})
public class StatisticsController extends AbstractController {

    private Logger logger = LoggerFactory.getLogger(StatisticsController.class);


    @Resource
    private ICommunityResourceService communityResourceService;


    @Resource
    private IStatisticsService statisticsService;


    @Resource
    private ICaptureService captureService;


    @Resource
    private JedisUtil jedisUtil;


    @RequestMapping(value = {"/realHouse"}, method = {RequestMethod.POST})
    @OpLog(model = "80001", desc = "实有房屋统计", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<Map<String, Object>> realHouse(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) BaseQueryEntity para, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            Map<String, Object> result = this.communityResourceService.statisticsHouse(para.getVillageCode());
            resp.setData(result);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70801001");
            resp.setMessage("实有房屋统计查询失败");
            this.logger.error("实有房屋统计查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/populace"}, method = {RequestMethod.POST})
    @OpLog(model = "80001", desc = "实有人口统计", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<Map<String, Integer>> populace(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) BaseQueryEntity para, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Integer>> resp = validite(bindingResult);
        try {
            Map<String, Integer> result = this.statisticsService.populace(para.getVillageCode());
            resp.setData(result);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70801002");
            resp.setMessage("实有人口统计失败");
            this.logger.error("实有人口统计查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/realCompany"}, method = {RequestMethod.POST})
    @OpLog(model = "80001", desc = "实有单位统计", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<Map<String, Object>> realCompany(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) BaseQueryEntity para, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            Map<String, Object> result = this.statisticsService.statisticsCompany(para.getVillageCode());
            resp.setData(result);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70801003");
            resp.setMessage("实有单位统计查询失败");
            this.logger.error("实有单位统计查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/captureCount"}, method = {RequestMethod.POST})
    @OpLog(model = "80001", desc = "抓拍统计", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<Integer> captureCount(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) BaseQueryEntity para, BindingResult bindingResult) throws Exception {
        ResponseEntity<Integer> resp = validite(bindingResult);
        try {
            String villageCode = para.getVillageCode();
            int thisVillageCaptureCount = 0;

            List<CaptureSumDTO> captureSumDTOs = this.captureService.findVillageCaptureTotal();
            for (CaptureSumDTO captureSumDTO : captureSumDTOs) {
                if (captureSumDTO.getVillageCode().equals(villageCode)) {
                    thisVillageCaptureCount = captureSumDTO.getNum().intValue();
                    break;
                }
            }
            resp.setData(Integer.valueOf(thisVillageCaptureCount));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70801004");
            resp.setMessage("抓拍统计查询失败");
            this.logger.error("抓拍统计查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/captureRegister"}, method = {RequestMethod.POST})
    @OpLog(model = "80001", desc = "抓拍登记未登记", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<Map<String, Integer>> captureRegister(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) BaseQueryEntity para, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Integer>> resp = validite(bindingResult);
        try {
            String villageCode = para.getVillageCode();
            Map<String, Integer> map = new HashMap<String, Integer>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());


            List<RegisterStatisticsDTO> rs = (this.jedisUtil.get("CAPTURE_REGISTER_STATISTICS") == null) ? this.captureService.registerStatistics() : (List) this.jedisUtil.get("CAPTURE_REGISTER_STATISTICS");

            for (RegisterStatisticsDTO registerStatisticsDTO : rs) {
                if (villageCode.equals(registerStatisticsDTO.getVillageCode())) {
                    map.put("captureTodayRegister", registerStatisticsDTO.getRegisterNumber());
                    map.put("captureTodayUnregister", Integer.valueOf(registerStatisticsDTO.getUnRegisterNumbe()));
                    break;
                }
            }
            resp.setData(map);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70801005");
            resp.setMessage("抓拍登记未登记查询失败");
            this.logger.error("抓拍登记未登记查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/snapTendency"}, method = {RequestMethod.POST})
    @OpLog(model = "80001", desc = "抓拍趋势", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<List<CaptureCountDTO>> snapTendency(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) BaseQueryEntity para, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<CaptureCountDTO>> resp = validite(bindingResult);
        try {
            List<CaptureCountDTO> result = this.statisticsService.snapTendency(para.getVillageCode());
            resp.setData(result);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70801006");
            resp.setMessage("抓拍趋势查询失败");
            this.logger.error("抓拍趋势查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/tendency"}, method = {RequestMethod.POST})
    @OpLog(model = "80001", desc = "疑似新增离开趋势", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<Map<String, Object>> addAndLeaveTendency(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) BaseQueryEntity para, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            List<Tendency> add = this.statisticsService.addTendency(para.getVillageCode());
            List<Tendency> leave = this.statisticsService.leaveTendency(para.getVillageCode());
            Map<String, Object> resultObj = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
            resultObj.put("add", add);
            resultObj.put("leave", leave);
            resp.setData(resultObj);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70801007");
            resp.setMessage("疑似新增离开趋势查询失败");
            this.logger.error("疑似新增离开趋势查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}
