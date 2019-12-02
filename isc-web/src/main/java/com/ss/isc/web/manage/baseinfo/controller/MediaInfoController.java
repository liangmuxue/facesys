package com.ss.isc.web.manage.baseinfo.controller;

import com.ss.isc.data.baseinfo.client.MediaInfoService;
import com.ss.isc.data.baseinfo.common.model.MediaInfo;
import com.ss.isc.util.file.FilePropertiesUtil;
import com.ss.isc.util.file.FileUtil;
import com.ss.response.ResponseEntity;
import com.ss.spider.system.organization.model.Organization;
import com.ss.spider.system.organization.service.OrganizationService;
import com.ss.tools.UUIDUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * MediaInfoController 附件上传相关接口
 * @author FrancisYs
 * @date 2019/9/17
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/mediaInfo"})
public class MediaInfoController extends BaseController {

    @Autowired
    private MediaInfoService mediaInfoService;

    @Autowired
    @Qualifier("organizationService")
    private OrganizationService<Organization> organizationService;

    @RequestMapping(value = {"/uploadFile"}, method = {RequestMethod.POST})
    public ResponseEntity<Object> uploadFIle(HttpServletRequest request, HttpServletResponse response, MediaInfo mediaInfo) throws Exception {
        try {
            ResponseEntity<Object> resp = createSuccResponse();

            List<MediaInfo> mediaInfos = new ArrayList<MediaInfo>();

            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {

                MultipartFile file = multiRequest.getFile((String) iter.next());


                String fileName = file.getOriginalFilename();
                if (fileName.length() > 60) {
                    resp = createFailResponse();
                    resp.setMessage("文件名不能超过60个字！");
                    return resp;
                }

                String dbFileName = System.currentTimeMillis() + "_" + UUIDUtils.getUUID() + fileName.substring(fileName.lastIndexOf("."), fileName.length());
                StringBuffer remoteFilePath = new StringBuffer();


                remoteFilePath.append(FilePropertiesUtil.getLocation() + "/");
                if ("add_person".equals(mediaInfo.getDateAttachmentBusiType())) {
                    remoteFilePath.append("add_person/");
                } else if ("leave_person".equals(mediaInfo.getDateAttachmentBusiType())) {
                    remoteFilePath.append("leave_person/");
                } else if ("frequency_person".equals(mediaInfo.getDateAttachmentBusiType())) {
                    remoteFilePath.append("frequency_person/");
                } else if ("special_person".equals(mediaInfo.getDateAttachmentBusiType())) {
                    remoteFilePath.append("special_person/");
                } else if ("alarm_person".equals(mediaInfo.getDateAttachmentBusiType())) {
                    remoteFilePath.append("alarm_person/");
                }
                boolean res = false;
                res = FileUtil.transferFile(remoteFilePath.toString(), dbFileName, file);
                if (res) {

                    mediaInfo.setDateAttachmentName(fileName);
                    mediaInfo.setDateAttachmentPath(remoteFilePath.toString() + dbFileName);
                    this.mediaInfoService.save(mediaInfo);


                    mediaInfo.setDateAttachmentUrl(
                            FilePropertiesUtil.getHttpUrl() + remoteFilePath.toString() + dbFileName);
                    mediaInfos.add(mediaInfo);
                }
            }
            resp.setData(mediaInfos);
            return resp;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            throw e;
        }
    }

    /**
     * 上传图片
     * @param request
     * @param mediaInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/uploadImage"}, method = {RequestMethod.POST})
    public ResponseEntity<Object> uploadImage(HttpServletRequest request, MediaInfo mediaInfo) throws Exception {
        try {
            ResponseEntity<Object> resp = createSuccResponse();
            List<MediaInfo> list = new ArrayList<>();
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                // 文件原始名称校验
                String imageName = file.getOriginalFilename();
                if (imageName.length() > 60) {
                    resp = createFailResponse();
                    resp.setMessage("文件名不能超过60个字！");
                    return resp;
                }
                // 服务器存储文件名定义：[时间戳]_[UUID].[后缀名]
                String dbImageName = System.currentTimeMillis() + "_" + UUIDUtils.getUUID() + imageName.substring(imageName.lastIndexOf("."));
                // 文件存储绝对路径业务处理
                StringBuilder remoteFilePath = new StringBuilder();
                remoteFilePath.append(FilePropertiesUtil.getLocation()).append("/");
                if ("add_person".equals(mediaInfo.getDateAttachmentBusiType())) {
                    remoteFilePath.append("add_person/");
                } else if ("leave_person".equals(mediaInfo.getDateAttachmentBusiType())) {
                    remoteFilePath.append("leave_person/");
                } else if ("frequency_person".equals(mediaInfo.getDateAttachmentBusiType())) {
                    remoteFilePath.append("frequency_person/");
                } else if ("special_person".equals(mediaInfo.getDateAttachmentBusiType())) {
                    remoteFilePath.append("special_person/");
                } else if ("alarm_person".equals(mediaInfo.getDateAttachmentBusiType())) {
                    remoteFilePath.append("alarm_person/");
                } else if ("village".equals(mediaInfo.getDateAttachmentBusiType())) {
                    remoteFilePath.append("resource/village/");
                } else if ("company".equals(mediaInfo.getDateAttachmentBusiType())) {
                    remoteFilePath.append("resource/company/");
                } else if ("people".equals(mediaInfo.getDateAttachmentBusiType())) {
                    // 实有人口照片
                    remoteFilePath.append("resource/people/");
                } else if ("vehicle_discovery".equals(mediaInfo.getDateAttachmentBusiType())) {
                    remoteFilePath.append("vihicle/vehicle_discovery/");
                } else if ("vehicle_leave".equals(mediaInfo.getDateAttachmentBusiType())) {
                    remoteFilePath.append("vihicle/vehicle_leave/");
                } else if ("vehicle_retation".equals(mediaInfo.getDateAttachmentBusiType())) {
                    remoteFilePath.append("vihicle/vehicle_retation/");
                } else if ("vehicle_tollgate".equals(mediaInfo.getDateAttachmentBusiType())) {
                    remoteFilePath.append("resource/vehicle_tollgate/");
                } else if ("village_Entrance".equals(mediaInfo.getDateAttachmentBusiType())) {
                    remoteFilePath.append("resource/village_Entrance/");
                }
                // 存储文件
                boolean res = FileUtil.transferFile(remoteFilePath.toString(), dbImageName, file);
                if (res) {
                    // 返回文件存储信息
                    String storageRelativePath = remoteFilePath.toString().replace(FilePropertiesUtil.getLocation(), FilePropertiesUtil.getStorageRelativePath());
                    mediaInfo.setDateAttachmentName(imageName);
                    mediaInfo.setDateAttachmentPath(storageRelativePath + dbImageName);
                    mediaInfo.setDateAttachmentUrl(FilePropertiesUtil.getHttpUrl() + storageRelativePath + dbImageName);
                    list.add(mediaInfo);
                }
            }
            resp.setData(list);
            return resp;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            throw e;
        }
    }
}
