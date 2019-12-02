package com.ss.isc.util.export;

import com.j7cai.common.exception.FrameException;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.export.excel.ImportExcel;
import com.ss.isc.util.file.FileUtil;
import com.ss.tools.UUIDUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


public final class TemplateReflectUtils {

    private static final Logger log = LoggerFactory.getLogger(TemplateReflectUtils.class);


    public static <T> Map<String, Object> getDataList(Class<T> cls, String path, MultipartFile file, boolean isDelect) throws Exception {
        try {
            Map<String, Object> object = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
            Map<String, String> imagePaths = new HashMap<String, String>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
            List<T> tempList = new ArrayList<T>();

            String filePath = FileUtil.getRealFilePath() + "zipcache" + File.separator + path;

            String filename = file.getOriginalFilename();
            if (filename.contains("zip")) {

                FileUtil.deleteDirectory(filePath);

                FileUtil.saveFile(file.getInputStream(), filename, filePath);

                String zipPath = filePath + File.separator + filename;

                FileUtil.unZipFiles(zipPath, filePath, "gbk");
                File decompressedFile = new File(zipPath.substring(0, zipPath.lastIndexOf(".")));
                File[] files = decompressedFile.listFiles();
                if (files.length > CommonConstant.COMMON_2.intValue()) {
                    return null;
                }

                for (File fi : files) {
                    if (fi.isDirectory()) {
                        String imageName = "";
                        String[] filelist = fi.list();
                        String imagePath = fi.getPath() + File.separator;
                        for (int i = 0; i < filelist.length; i++) {
                            imageName = filelist[i];
                            if (isDelect) {
                                String imgpath = copyFile(imagePath + imageName, path);
                                imagePaths.put(imageName.substring(0, imageName.lastIndexOf(".")), imgpath);
                            } else {
                                imagePaths.put(imageName.substring(0, imageName.lastIndexOf(".")), imagePath + imageName);
                            }

                        }
                    } else {
                        ImportExcel ei = new ImportExcel(fi, 1, 0);
                        tempList = ei.getDataList(cls, new int[0]);
                    }
                }

                if (isDelect) {
                    boolean flag = FileUtil.deleteDirectory(filePath);
                    log.info("删除" + filePath + "目录下文件" + flag);
                }
            } else {

                tempList = getDataList(cls, file);
            }
            object.put("list", tempList);
            object.put("imagePaths", imagePaths);
            return object;
        } catch (Exception e) {
            log.error("导入文件失败" + e);
            throw e;
        }
    }


    public static <T> List<T> getDataList(Class<T> classT, MultipartFile file, int headerNum, int sheetIndex) throws Exception {
        ImportExcel ei = new ImportExcel(file, headerNum, sheetIndex);
        return ei.getDataList(classT, new int[0]);
    }


    public static <T> List<T> getDataList(Class<T> classT, MultipartFile file) throws Exception {
        ImportExcel ei = new ImportExcel(file, 1, 0);
        return ei.getDataList(classT, new int[0]);
    }


    public static String copyFile(String srcFileName, String path) throws FrameException {
        StringBuffer remoteFilePath = new StringBuffer();
        remoteFilePath.append(FileUtil.getRealFilePath());
        if ("people".equals(path)) {
            remoteFilePath.append("resource" + File.separator + "people" + File.separator);

        } else if ("company".equals(path)) {
            remoteFilePath.append("resource" + File.separator + "company" + File.separator);

        } else if ("company_people".equals(path)) {
            remoteFilePath.append("resource" + File.separator + "company_people" + File.separator);

        } else if ("wifi".equals(path)) {
            remoteFilePath.append("resource" + File.separator + "wifi" + File.separator);

        } else if ("vehicle_tollgate".equals(path)) {
            remoteFilePath.append("resource" + File.separator + "vehicle_tollgate" + File.separator);
        }


        String dbImageName = System.currentTimeMillis() + "_" + UUIDUtils.getUUID() + srcFileName.substring(srcFileName.lastIndexOf("."), srcFileName.length());

        FileUtil.copyFile(srcFileName, remoteFilePath.toString() + File.separator + dbImageName);
        return remoteFilePath + dbImageName;
    }


    public static void main(String[] args) throws FrameException {
        boolean flag = FileUtil.deleteDirectory("E:\\iscFile\\zipcache\\people");
        System.out.println(flag);
    }

}
