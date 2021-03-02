package com.ss.tools;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;


public class FileUtils {

    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private static final int BUF_SIZE = 8096;


    public static byte[] getBytesByUrl(String imgUrl) throws Exception {
        if (StringUtils.isEmpty(imgUrl)) {
            return null;
        }
        BufferedInputStream bis = null;
        ByteArrayOutputStream bos = null;
        try {
            URL url = new URL(imgUrl);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setConnectTimeout(3000);
            http.connect();
            bis = new BufferedInputStream(http.getInputStream());
            bos = new ByteArrayOutputStream();
            byte[] buf = new byte[BUF_SIZE];
            int size;
            while ((size = bis.read(buf)) != -1) {
                bos.write(buf, 0, size);
            }
            http.disconnect();
            return bos.toByteArray();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    logger.error("流关闭失败，原因：" + e.getMessage(), e);
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    logger.error("流关闭失败，原因：" + e.getMessage(), e);
                }
            }
        }
    }

    public static String getBase64ByUrl(String imgUrl) throws Exception {
        if (StringUtils.isEmpty(imgUrl)) {
            return null;
        }
        byte[] bytesByUrl = getBytesByUrl(imgUrl);
        if (bytesByUrl == null) {
            return "";
        }
        return Base64Utils.encodeToString(bytesByUrl);
    }


    public static String getImageStr(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }


}
