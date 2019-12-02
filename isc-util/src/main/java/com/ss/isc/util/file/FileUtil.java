package com.ss.isc.util.file;

import com.ss.isc.util.DateUtil;
import com.ss.isc.util.PropertiesUtil;
import com.ss.isc.util.StringUtils;
import com.ss.tools.FileUtils;
import com.j7cai.common.exception.FrameException;
import com.j7cai.common.util.UUIDUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


public class FileUtil extends FileUtils {

    public static final Logger logger = LoggerFactory.getLogger(FileUtil.class);


    public static boolean transferFile(String remoteFilePath, String dbFileName, MultipartFile file) throws Exception {
        File directory = new File(remoteFilePath);
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }
        try {
            file.transferTo(new File(directory, dbFileName));
            return true;
        } catch (Exception e) {
            logger.error(e.toString(), e);
            throw e;
        }
    }


    public static boolean deleteFile(String path) {
        boolean flag = false;
        File file = new File(path);

        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }


    public static String downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
        String imagePath = "";
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setConnectTimeout(3000);

        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        InputStream inputStream = conn.getInputStream();

        byte[] getData = readInputStream(inputStream);

        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        File file = new File(saveDir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
        return saveDir + File.separator + fileName;
    }


    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }


    public static InputStream getInputStreamByGet(String url) {
        try {
            HttpURLConnection conn = (HttpURLConnection) (new URL(url)).openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                return conn.getInputStream();

            }

        } catch (IOException e) {
            logger.error(e.toString(), e);
        }
        return null;
    }


    public static boolean deleteDirectory(String sPath) {
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);

        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;

        File[] files = dirFile.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {

                if (files[i].isFile()) {
                    flag = deleteFile(files[i].getAbsolutePath());
                    if (!flag) {
                        break;
                    }
                } else {

                    flag = deleteDirectory(files[i].getAbsolutePath());
                    if (!flag) {
                        break;
                    }
                }
            }
        }
        if (!flag) {
            return false;
        }

        if (dirFile.delete()) {
            return true;
        }

        return false;
    }


    public static void unpack(String zip, String charsetName) {
        unpack(new File(zip), charsetName);
    }


    public static void unpack(String zip, String outputDir, String charsetName) {
        unpack(new File(zip), new File(outputDir), charsetName);
    }


    public static void unpack(File zip, String charsetName) {
        unpack(zip, null, charsetName);
    }


    public static void unpack(File zip, File outputDir) {
        unpack(zip, outputDir, "");
    }


    public static void unpack(File zip, File outputDir, String charsetName) {
        FileOutputStream out = null;
        InputStream in = null;
        ZipFile zipFileData = null;
        ZipFile zipFile = null;
        try {
            if (outputDir != null &&
                    !outputDir.exists()) {
                outputDir.mkdirs();
            }

            if (charsetName != null && charsetName != "") {
                zipFile = new ZipFile(zip.getPath(), Charset.forName(charsetName));
            } else {

                zipFile = new ZipFile(zip.getPath(), Charset.forName("utf8"));
            }
            Enumeration entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String filePath = "";
                if (outputDir == null) {
                    filePath = zip.getParentFile().getPath() + File.separator + entry.getName();
                } else {

                    filePath = outputDir.getPath() + File.separator + entry.getName();
                }
                File file = new File(filePath);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                if (parentFile.isDirectory()) ;
            }


            if (charsetName != null && charsetName != "") {
                zipFileData = new ZipFile(zip.getPath(), Charset.forName(charsetName));
            } else {

                zipFileData = new ZipFile(zip.getPath(), Charset.forName("utf8"));
            }
            Enumeration<? extends ZipEntry> entriesData = zipFileData.entries();
            while (entriesData.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entriesData.nextElement();
                in = zipFile.getInputStream(entry);
                String filePath = "";
                if (outputDir == null) {
                    filePath = zip.getParentFile().getPath() + File.separator + entry.getName();
                } else {

                    filePath = outputDir.getPath() + File.separator + entry.getName();
                }
                File file = new File(filePath);
                if (file.isDirectory()) {
                    continue;
                }
                out = new FileOutputStream(filePath);
                int len = -1;
                byte[] bytes = new byte[1024];
                while ((len = in.read(bytes)) != -1) {
                    out.write(bytes, 0, len);
                }
                out.flush();
            }

            logger.debug("文件解压成功");
        } catch (Exception e) {
            logger.debug("文件解压失败", e.getMessage());
        } finally {

            try {
                out.close();
                in.close();
                zipFile.close();
                zipFileData.close();
            } catch (IOException e) {
                logger.debug("文件解压失败", e.getMessage());
            }
        }
    }


    public static boolean unZipFiles(String zipFileName, String descFileName, String charsetName) {
        String descFileNames = descFileName;
        if (!descFileNames.endsWith(File.separator)) {
            descFileNames = descFileNames + File.separator;
        }
        if (StringUtils.isBlank(charsetName)) {
            charsetName = "utf8";
        }

        try {
            ZipFile zipFile = new ZipFile(zipFileName, Charset.forName(charsetName));
            ZipEntry entry = null;
            String entryName = null;
            String descFileDir = null;
            byte[] buf = new byte[4096];
            int readByte = 0;


            Enumeration enums = zipFile.entries();

            while (enums.hasMoreElements()) {
                entry = (ZipEntry) enums.nextElement();

                entryName = entry.getName();
                descFileDir = descFileNames + entryName;
                if (entry.isDirectory()) {

                    (new File(descFileDir)).mkdirs();

                    continue;
                }

                (new File(descFileDir)).getParentFile().mkdirs();

                File file = new File(descFileDir);

                OutputStream os = new FileOutputStream(file);

                InputStream is = zipFile.getInputStream(entry);
                while ((readByte = is.read(buf)) != -1) {
                    os.write(buf, 0, readByte);
                }
                os.close();
                is.close();
            }
            zipFile.close();
            logger.debug("文件解压成功!");
            return true;
        } catch (Exception e) {
            logger.debug("文件解压失败：" + e.getMessage());
            return false;
        }
    }


    public static void saveFile(InputStream inputStream, String fileName, String path) {
        FileOutputStream os = null;

        try {
            byte[] bs = new byte[1024];
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            int len;
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (IOException e) {
            logger.error(e.toString(), e);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        } finally {
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                logger.error(e.toString(), e);
            }
        }
    }


    public static String getFileNameWithoutSuffix(File file) {
        String fileName = file.getName();
        return fileName.substring(0, fileName.lastIndexOf("."));
    }


    public static String getRealFilePath() {
        String path = "";
        if (PropertiesUtil.isLocal()) {
            path = "/media/isc" + File.separator;
        } else {

            path = FilePropertiesUtil.getLocation() + File.separator;
        }
        return path;
    }


    public static String encryptToBase64(String filePath) {
        if (filePath == null) {
            return null;
        }
        try {
            byte[] b = Files.readAllBytes(Paths.get(filePath, new String[0]));
            return Base64.getEncoder().encodeToString(b);
        } catch (IOException e) {
            logger.error(e.toString(), e);

            return null;
        }
    }


    public static String fileBase64(File inFile) {
        byte[] bytes = copyFile2Byte(inFile);
        if (bytes == null) {
            return null;
        }

        return Base64.getEncoder().encodeToString(bytes);
    }


    public static byte[] copyFile2Byte(File inFile) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(inFile);

            int len = in.available();

            byte[] bytes = new byte[len];

            in.read(bytes);
            return bytes;
        } catch (Exception e) {
            logger.error(e.toString(), e);
            return null;
        } finally {

            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                logger.error(e.toString(), e);
            }
        }
    }


    public static boolean copyFile(String srcFileName, String descFileName) throws FrameException {
        return copyFileCover(srcFileName, descFileName, false);
    }


    public static boolean copyFileCover(String srcFileName, String descFileName, boolean coverlay) throws FrameException {
        File srcFile = new File(srcFileName);

        if (!srcFile.exists()) {
            logger.info("复制文件失败，源文件 " + srcFileName + " 不存在!");
            return false;
        }

        if (!srcFile.isFile()) {
            logger.info("复制文件失败，" + srcFileName + " 不是一个文件!");
            return false;
        }
        File descFile = new File(descFileName);

        if (descFile.exists()) {

            if (coverlay) {
                logger.debug("目标文件已存在，准备删除!");
                if (!delFile(descFileName)) {
                    logger.info("删除目标文件 " + descFileName + " 失败!");
                    return false;
                }
            } else {

                logger.info("复制文件失败，目标文件 " + descFileName + " 已存在!");
                return false;
            }

        } else if (!descFile.getParentFile().exists()) {

            logger.info("目标文件所在的目录不存在，创建目录!");

            if (!descFile.getParentFile().mkdirs()) {
                logger.info("创建目标文件所在的目录失败!");
                return false;
            }
        }


        int readByte = 0;
        FileInputStream ins = null;
        FileOutputStream outs = null;

        try {
            ins = new FileInputStream(srcFile);

            outs = new FileOutputStream(descFile);
            byte[] buf = new byte[1024];

            while ((readByte = ins.read(buf)) != -1) {
                outs.write(buf, 0, readByte);
            }
            logger.info("复制单个文件 " + srcFileName + " 到" + descFileName + "成功!");
            return true;
        } catch (Exception e) {
            logger.info("复制文件失败：" + e.getMessage());
            return false;
        } finally {

            if (outs != null) {
                try {
                    outs.close();
                } catch (IOException oute) {
                    logger.error(oute.toString(), oute);
                }
            }
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException ine) {
                    logger.error(ine.toString(), ine);
                }
            }
        }
    }


    public static boolean delFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            logger.debug(fileName + " 文件不存在!");
            return true;
        }

        if (file.isFile()) {
            return deleteFile(fileName);
        }

        return deleteDirectory(fileName);
    }


    public static boolean createJsonFile(String jsonString, String filePath, String fileName) {
        boolean flag = true;

        String fullPath = filePath + File.separator + fileName + ".json";


        try {
            File file = new File(fullPath);

            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();

            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            write.write(jsonString);
            write.flush();
            write.close();

            file = new File(fullPath + ".ok");
            write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            write.write("");
            write.flush();
            write.close();
        } catch (Exception e) {
            flag = false;
            logger.error(e.toString(), e);
        }

        return flag;
    }


    public static boolean createJsonFile(List<String> jsonString, String filePath, String fileName) {
        boolean flag = true;

        String fullPath = filePath + File.separator + fileName + ".json";


        try {
            File file = new File(fullPath);

            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();

            FileWriter out = new FileWriter(file);
            BufferedWriter bw = null;
            for (String s : jsonString) {
                if (null == bw) {
                    bw = new BufferedWriter(out);
                    bw.write(s);
                    continue;
                }
                bw.newLine();
                bw.write(s);
            }

            bw.flush();
            bw.close();

            file = new File(fullPath + ".ok");
            out = new FileWriter(file);
            bw = new BufferedWriter(out);
            bw.flush();
            bw.close();
        } catch (Exception e) {
            flag = false;
            logger.error(e.toString(), e);
        }

        return flag;
    }


    public static List<String> readFile(String filePaht) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        List<String> result = new ArrayList<String>();
        try {
            fis = new FileInputStream(filePaht);
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            String line = "";
            while ((line = br.readLine()) != null) {
                result.add(line);

            }
        } catch (Exception e) {
            logger.error(e.toString(), e);
        } finally {

            try {
                if (null != br) {
                    br.close();
                }
                if (null != br) {
                    isr.close();
                }
                if (null != br) {
                    fis.close();

                }
            } catch (IOException e) {
                logger.error(e.toString(), e);
            }
        }
        return result;
    }


    public static String getFileName(String typeStr) {
        return "1000" + typeStr + DateUtil.getCurrentSqlTimestampString("yyyyMMddHHmmss") + "_" +
                UUIDUtil.uuid();
    }


    public static String decryptByBase64(String base64, String filePath) {
        if (base64 == null && filePath == null) {
            return "生成文件失败，请给出相应的数据。";
        }
        try {
            Files.write(Paths.get(filePath, new String[0]), Base64.getDecoder().decode(base64), new OpenOption[]{StandardOpenOption.CREATE});
        } catch (IOException e) {
            logger.error(e.toString(), e);
        }
        return "指定路径下生成文件成功！";
    }

    public static void writeLog(Object obj) {
        try {
            String syncLogPath = null;
            if (PropertiesUtil.isLocal()) {
                syncLogPath = "E://capture_today.txt";

            } else {


                syncLogPath = "//data//program//cw_isc//logs//capture_today.txt";
            }

            File file = new File(syncLogPath);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream out = new FileOutputStream(file, true);
            StringBuffer sb = new StringBuffer();
            DateUtil.getCurrentDay("yyyy-MM-dd hh:mm:ss sss");
            String str = DateUtil.getCurrentDay("yyyy-MM-dd hh:mm:ss sss") + obj.toString();
            sb.append(str + "\r\n");
            out.write(sb.toString().getBytes("utf-8"));
            out.close();
        } catch (IOException iOException) {
        }
    }

}
