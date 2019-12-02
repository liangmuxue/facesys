package com.ss.isc.web.manage.access.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.isc.data.access.client.IAccessService;
import com.ss.isc.data.access.common.dto.FacedbfaceDTO;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.file.FileUtil;
import com.ss.isc.web.manage.baseinfo.controller.BaseController;
import com.ss.isc.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.ResponseEntity;
import com.ss.tools.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * FacedbfaceController 人像集请求
 * @author FrancisYs
 * @date 2019/8/06
 * @update 2019/8/22
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/facedbface"})
public class FacedbfaceController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(FacedbfaceController.class);

    @Resource
    private IAccessService accessService;


    @RequestMapping(value = {"/pages"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "查询人像集分页列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Map<String, Object>> getFacedbfacePages(HttpServletRequest request, @RequestBody FacedbfaceDTO facedbfaceDTO, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            Map<String, Object> map = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY);
            // 调用欧神接口查询人像集分页列表
            JSONObject jsonObject = this.accessService.getFacedbfacePages(JSONObject.toJSONString(facedbfaceDTO));
            this.logger.info("查询人像集分页列表返回结果" + jsonObject.toString());
            // 请求成功封装人像集分页集合
            if (StringUtils.checkSuccess(jsonObject)) {
                map = JSONObject.parseObject(jsonObject.getString("data"), Map.class);
            } else {
                String message = "查询人像集分页列表失败，原因：" + jsonObject.getString("message");
                this.logger.info(message);
                resp.setMessage(message);
            }
            resp.setData(map);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.FACEDBFACE_PAGE_FAILED_CODE);
            this.logger.error("查询人像集列表失败，原因：" + e.toString(), e);
            resp.setMessage("查询人像集列表失败");
        }
        return resp;
    }

    @RequestMapping(value = {"/detail"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "查询人像集详细信息", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Map<String, Object>> getFacedbfaceDetail(HttpServletRequest request, @RequestBody FacedbfaceDTO facedbfaceDTO, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            Map<String, Object> map = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY);
            // 调用欧神接口查询人像集明细
            JSONObject jsonObject = this.accessService.facedbfaceGet(JSONObject.toJSONString(facedbfaceDTO));
            this.logger.info("查询人像集明细返回结果" + jsonObject.toString());
            // 请求成功封装人像集对象
            if (StringUtils.checkSuccess(jsonObject)) {
                facedbfaceDTO = JSONObject.parseObject(jsonObject.getString("data"), FacedbfaceDTO.class);
            } else {
                String message = "查询人像集明细失败，原因：" + jsonObject.getString("message");
                this.logger.info(message);
                resp.setMessage(message);
            }
            map.put("facedbfaceDTO", facedbfaceDTO);
            resp.setData(map);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.FACEDBFACE_DETAIL_FAILED_CODE);
            this.logger.error("查询人像集明细失败，原因：" + e.toString(), e);
            resp.setMessage("查询人像集明细失败");
        }
        return resp;
    }

    @RequestMapping(value = {"/insert"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "新增人像集信息", type = OperaTypeEnum.ADD)
    public ResponseEntity<Map<String, Object>> insertFacedbface(HttpServletRequest request, @RequestBody FacedbfaceDTO facedbfaceDTO, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            Integer faceImgType = facedbfaceDTO.getFaceImgType();
            String faceImg = facedbfaceDTO.getFaceImg();// 传入图片路径转化为base64
            if (CommonConstant.IMGTYPE_URL.equals(faceImgType)) {
                faceImg = FileUtils.getBase64ByUrl(faceImg);
                facedbfaceDTO.setFaceImg(faceImg);
                facedbfaceDTO.setFaceImgType(CommonConstant.IMGTYPE_BASE64);
            }
            // 调用欧神接口新增人像集
            JSONObject jsonObject = this.accessService.facedbfaceInsert(JSONObject.toJSONString(facedbfaceDTO));
            this.logger.info("新增人像集返回结果" + jsonObject.toString());
            // 请求成功打印新增信息的ID
            if (StringUtils.checkSuccess(jsonObject)) {
                String id = jsonObject.getString("data");
                this.logger.info("新增一条人像集信息，id为：" + id);
            } else {
                String message = "新增人像集失败，原因：" + jsonObject.getString("message");
                this.logger.info(message);
                resp.setMessage(message);
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.FACEDBFACE_ADD_FAILED_CODE);
            this.logger.error("新增人像集失败，原因：" + e.toString(), e);
            resp.setMessage("新增人像集失败");
        }
        return resp;
    }

    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "删除人像集信息", type = OperaTypeEnum.DELETE)
    public ResponseEntity<Map<String, Object>> deleteFacedbface(HttpServletRequest request, @RequestBody FacedbfaceDTO facedbfaceDTO, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            // 单条删除
            JSONObject paramJson = new JSONObject();
            String id = facedbfaceDTO.getId();
            JSONArray facedbFaceIdsArray = new JSONArray();
            facedbFaceIdsArray.add(id);
            paramJson.put("facedbFaceIds", facedbFaceIdsArray);
            this.logger.info("将要删除id为：" + id + "的人像集信息");
            // 调用欧神接口删除人像集
            JSONObject jsonObject = this.accessService.facedbfaceDelete(paramJson.toString());
            this.logger.info("删除人像集返回结果" + jsonObject.toString());
            if (StringUtils.checkSuccess(jsonObject)) {
                String message = "删除了id为：" + id + "的人像集信息";
                this.logger.info(message);
                resp.setMessage(message);
            } else {
                String message = "删除人像集失败，原因：" + jsonObject.getString("message");
                this.logger.info(message);
                resp.setMessage(message);
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.FACEDBFACE_DELETE_FAILED_CODE);
            this.logger.error("删除人像集失败，原因：" + e.toString(), e);
            resp.setMessage("删除人像集失败");
        }
        return resp;
    }

    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "修改人像集信息", type = OperaTypeEnum.EDIT)
    public ResponseEntity<Map<String, Object>> updateFacedbface(HttpServletRequest request, @RequestBody FacedbfaceDTO facedbfaceDTO, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            String faceImg = facedbfaceDTO.getFaceImg();// 传入图片路径转化为base64
            if (StringUtils.isNotEmpty(faceImg))
                facedbfaceDTO.setFaceImg(FileUtils.getBase64ByUrl(faceImg));
            // 调用欧神接口修改人像集
            JSONObject jsonObject = this.accessService.facedbfaceUpdate(JSONObject.toJSONString(facedbfaceDTO));
            this.logger.info("修改人像集返回结果" + jsonObject.toString());
            // 请求成功打印修改信息的ID
            if (StringUtils.checkSuccess(jsonObject)) {
                this.logger.info("修改了id为：" + facedbfaceDTO.getId() + "的人像集信息");
            } else {
                String message = "修改人像集失败，原因：" + jsonObject.getString("message");
                this.logger.info(message);
                resp.setMessage(message);
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.FACEDBFACE_EDIT_FAILED_CODE);
            this.logger.error("修改人像集失败，原因：" + e.toString(), e);
            resp.setMessage("修改人像集失败");
        }
        return resp;
    }

}
