package com.ss.facesys.web.manage.collect.controller;

import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.collect.client.IOfflineVideoService;
import com.ss.facesys.data.collect.common.model.OfflineVideo;
import com.ss.facesys.data.resource.common.web.OfflineVideoVO;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.constant.NumberConstant;
import com.ss.facesys.util.file.FilePropertiesUtil;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.spider.log.constants.ModuleCode;
import com.ss.valide.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 离线视频
 *
 * @author chao
 * @create 2020/2/7
 * @email lishuangchao@ss-cas.com
 **/
@RestController
@CrossOrigin
@RequestMapping({"/offlineVideo"})
public class OfflineVideoController extends BaseController {

    @Resource
    private IOfflineVideoService offlineVideoService;

    /**
     * 离线视频分页查询
     *
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/page"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "离线视频分页查询", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<PageEntity<OfflineVideo>> internetBarPage(@RequestBody OfflineVideoVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<OfflineVideo>> resp = validite(bindingResult);
        try {
            Page<OfflineVideo> data = (Page) this.offlineVideoService.offlineVideoPage(para);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            //离线视频分页查询失败处理
            this.logger.error("离线视频分页查询失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 新增离线视频
     * @param para
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = {"/insert"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "离线视频新增", type = OperaTypeEnum.ADD)
    public ResponseEntity<Object> insertOfflineVideo(@RequestBody @Validated({APIAddGroup.class}) OfflineVideoVO para, RedirectAttributes redirectAttributes) {
        ResponseEntity<Object> resp = createSuccResponse();
        try {
            para.setCreateTime(String.valueOf(System.currentTimeMillis()));
            para.setStatus(NumberConstant.ONE);
            //添加离线视频
            int id = this.offlineVideoService.insertOfflineVideo(para);
            if (id > 0) {
                resp.setMessage("离线视频新增成功");
                resp.setData(id);
            } else {
                resp = createFailResponse();
                resp.setMessage("离线视频新增失败，请联系管理员");
            }
            resp.setMessage("离线视频新增成功");
        } catch (Exception e) {
            this.logger.error("离线视频新增失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 上传视频
     *
     * @param srcFile
     * @param para
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = {"/localUpload"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "离线视频上传", type = OperaTypeEnum.ADD)
    public ResponseEntity<Object> localUpload(@RequestParam("file") MultipartFile srcFile, @Validated({APIOpStatusGroup.class}) OfflineVideoVO para, RedirectAttributes redirectAttributes) {
        ResponseEntity<Object> resp = createSuccResponse();
        //没有选择文件，srcFile为空
        if (srcFile.isEmpty()) {
            resp = createFailResponse();
            resp.setMessage("请选择一个视频！");
            return resp;
        }
        //选择了文件，开始上传操作
        try {
            //构建上传目标路径，找到了项目的target的classes目录
            File destFile = new File(FilePropertiesUtil.getLocation() + "/video");
            if (!destFile.exists()) {
                destFile.mkdirs();
            }
            //根据srcFile大小，准备一个字节数组
            byte[] bytes = srcFile.getBytes();
            //获得文件名称
            String fileName = srcFile.getOriginalFilename();
            //获得文件前缀名称
            String prefixName = fileName.substring(0, fileName.lastIndexOf("."));
            //获得文件后缀名称
            String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (!("rmvb".equals(suffixName) || "avi".equals(suffixName) || "mp4".equals(suffixName) || "mov".equals(suffixName))) {
                resp = createFailResponse();
                resp.setMessage("视频格式不正确！");
                return resp;
            }
            //设置日期格式
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String url = "/video/" + prefixName + "_" + df.format(new Date()) + "." + suffixName;
            //拼接上传路径
            Path path = Paths.get(FilePropertiesUtil.getLocation() + url);
            //** 开始将源文件写入目标地址
            Files.write(path, bytes);
            para.setDepositUrl(url);
            para.setFormat(suffixName);
            //离线视频关联ocean
            int num = this.offlineVideoService.insertDeviceId(para);
            if (num > 0) {
                resp.setMessage("视频上传成功");
            } else {
                para.setStatus(NumberConstant.THREE);
                this.offlineVideoService.updateStatus(para);
                resp = createFailResponse();
                resp.setMessage("视频上传失败，请联系管理员");
            }
        } catch (IOException e) {
            para.setStatus(NumberConstant.THREE);
            this.offlineVideoService.updateStatus(para);
            this.logger.error("离线视频上传失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 查询离线视频详情
     *
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/detail"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "查询离线视频详情", type = OperaTypeEnum.SELECT)
    public ResponseEntity<OfflineVideo> offlineVideoDetail(@RequestBody @Validated({APIGetsGroup.class}) OfflineVideoVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<OfflineVideo> resp = validite(bindingResult);
        try {
            OfflineVideo offlineVideo = this.offlineVideoService.detail(para);
            resp.setData(offlineVideo);
        } catch (Exception e) {
            //查询离线视频详情失败处理
            this.logger.error("查询离线视频详情失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 修改离线视频信息
     *
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "修改离线视频信息", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> updateOfflineVideo(@RequestBody @Validated({APIEditGroup.class}) OfflineVideoVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            int num = this.offlineVideoService.updateOfflineVideo(para);
            if (num > 0) {
                resp.setData("修改成功");
            } else {
                resp = createFailResponse();
                resp.setMessage("修改失败，请联系管理员");
            }
        } catch (Exception e) {
            //修改离线视频信息失败处理
            this.logger.error("修改离线视频信息失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 删除离线视频
     *
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "删除离线视频", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> deleteInternetBar(@RequestBody @Validated({APIDeltGroup.class}) OfflineVideoVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            //删除表中离线视频
            int num = this.offlineVideoService.deleteOfflineVideo(para);
            if (num > 0) {
                resp.setData("删除成功");
            } else {
                resp = createFailResponse();
                resp.setMessage("删除失败，请联系管理员");
            }
        } catch (Exception e) {
            //删除离线视频失败处理
            this.logger.error("删除离线视频失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }
}
