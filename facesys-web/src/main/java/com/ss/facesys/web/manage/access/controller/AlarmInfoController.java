package com.ss.facesys.web.manage.access.controller;

import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.access.common.dto.AlarmInfo;
import com.ss.facesys.data.access.common.web.AlarmInfoVO;
import com.ss.facesys.data.access.service.AlarmInfoServiceImpl;
import com.ss.facesys.data.collect.common.web.AlarmVO;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.tools.UUIDUtils;
import com.ss.valide.APIEditGroup;
import com.ss.valide.APIGetsGroup;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 报警配置信息
 * @author zhangao
 * @date 2021/2/2
 * @email zhangao@ss-cas.com
 */
@RestController
@RequestMapping({"/alarmInfo"})
public class AlarmInfoController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(AlarmInfoController.class);

    @Resource
    private AlarmInfoServiceImpl alarmInfoService;

    @RequestMapping(value = {"/select"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "查询报警配置", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<AlarmInfo>> selectAlarmInfo(@RequestBody @Validated(APIGetsGroup.class) AlarmInfoVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<AlarmInfo>> resp = validite(bindingResult);
        try {
            Page<AlarmInfo> alarmInfos = (Page)this.alarmInfoService.selectAlarmInfo(para);
            resp.setData(new PageEntity<>(alarmInfos));
            resp.setMessage("查询报警配置信息成功");
        }catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.ALARMINFO_SELECT_FAILED_CODE);
            this.logger.error("查询报警配置信息失败，原因：" + e.toString(), e);
            resp.setMessage("查询报警配置信息失败");
        }
        return resp;
    }

    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "报警配置修改", type = OperaTypeEnum.SELECT)
    public ResponseEntity<String> updateAlarmInfo(@RequestBody @Validated(APIEditGroup.class) AlarmInfoVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            String message = this.alarmInfoService.updateAlarmInfo(para);
            if("SUCCESS".equals(message)){
                resp.setMessage("报警配置信息修改成功");
            }else{
                resp.setCode(ResultCode.ALARMINFO_EDIT_FAILED_CODE);
                resp.setMessage("报警配置信息修改失败");
            }
        }catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.ALARMINFO_EDIT_FAILED_CODE);
            this.logger.error("报警配置信息修改失败，原因：" + e.toString(), e);
            resp.setMessage("报警配置信息修改失败");
        }
        return resp;
    }

    @RequestMapping(value = {"/upload"})
    @ResponseBody
    public ResponseEntity<Object> upload(@RequestParam(value = "file",required = false) MultipartFile file, HttpServletRequest request) throws IOException {
        ResponseEntity<Object> resp = createSuccResponse();
        String path = PropertiesUtil.getVoiceUrl();
        File dir = new File(path);
        //文件目录不存在，就创建一个
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }
        // 上传的文件名
        String uploadName = file.getOriginalFilename();
        // 获取后缀名
        String[] strArray = uploadName.split("\\.");
        int suffixIndex = strArray.length - 1;
        String exe = strArray[suffixIndex];
        // 组装服务器文件名
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = sdf.format(new Date()) + "_" + UUIDUtils.getUUID() + "." + exe;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        // 处理中文问题
        sfu.setHeaderEncoding("UTF-8");
        // 解码请求
        //List<FileItem> fileItems = sfu.parseRequest(request);
        //创建文件
        String filePath = path  + fileName;
        File file2 = new File(fileName);
        //若文件不存在，创建文件
        if (file2.exists()) {
            file2.createNewFile();
        }
        // 输入流
        InputStream in = file.getInputStream();
        // 输出流
        OutputStream out = new FileOutputStream(filePath, true);
        //将上传文件内容，写进服务器创建的新文件。
        try {
            byte[] buffer = new byte[1024];
            while (true) {
                int byteRead = in.read(buffer);
                if (byteRead == -1) {
                    break;
                }
                out.write(buffer, 0, byteRead);
            }
        } catch (MalformedURLException ex) {
            resp.setMessage("上传失败");
            resp.setCode(ResultCode.ALARMINFO_UPLOAD_FAILED_CODE);
            return resp;
        } finally {
            //关闭输入流，输出流
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
        resp.setMessage("上传成功");
        resp.setData(PropertiesUtil.getVoiceIP() + "/" + fileName);
        return resp;
    }
}
