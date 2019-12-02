package com.ss.util.nasstorage.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;


public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private static int BUF_SIZE = 8096;


    public static File createDir(String dirPath) {
        File dir;
        try {
            dir = new File(dirPath);
            if (!dir.exists()) {
                FileUtils.forceMkdir(dir);
            }
        } catch (Exception e) {
            logger.error("创建目录出错！", e);
            throw new RuntimeException(e);
        }
        return dir;
    }


    public static File createFile(String filePath) {
        File file;
        try {
            file = new File(filePath);
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                FileUtils.forceMkdir(parentDir);
            }
        } catch (Exception e) {
            logger.error("创建文件出错！", e);
            throw new RuntimeException(e);
        }
        return file;
    }


    public static void copyDir(String srcPath, String destPath) {
        try {
            File srcDir = new File(srcPath);
            File destDir = new File(destPath);
            if (srcDir.exists() && srcDir.isDirectory()) {
                FileUtils.copyDirectoryToDirectory(srcDir, destDir);
            }
        } catch (Exception e) {
            logger.error("复制目录出错！", e);
            throw new RuntimeException(e);
        }
    }


    public static void copyFile(String srcPath, String destPath) {
        try {
            File srcFile = new File(srcPath);
            File destDir = new File(destPath);
            if (srcFile.exists() && srcFile.isFile()) {
                FileUtils.copyFileToDirectory(srcFile, destDir);
            }
        } catch (Exception e) {
            logger.error("复制文件出错！", e);
            throw new RuntimeException(e);
        }
    }


    public static void deleteDir(String dirPath) {
        try {
            File dir = new File(dirPath);
            if (dir.exists() && dir.isDirectory()) {
                FileUtils.deleteDirectory(dir);
            }
        } catch (Exception e) {
            logger.error("删除目录出错！", e);
            throw new RuntimeException(e);
        }
    }


    public static void deleteFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists() && file.isFile()) {
                FileUtils.forceDelete(file);
            }
        } catch (Exception e) {
            logger.error("删除文件出错！", e);
            throw new RuntimeException(e);
        }
    }


    public static void renameFile(String srcPath, String destPath) {
        File srcFile = new File(srcPath);
        if (srcFile.exists()) {
            File newFile = new File(destPath);
            boolean result = srcFile.renameTo(newFile);
            if (!result) {
                throw new RuntimeException("重命名文件出错！" + newFile);
            }
        }
    }


    public static void writeFile(String filePath, String fileContent) {
        OutputStream os = null;
        Writer w = null;
        try {
            createFile(filePath);
            os = new BufferedOutputStream(new FileOutputStream(filePath));
            w = new OutputStreamWriter(os, "UTF-8");
            w.write(fileContent);
            w.flush();
        } catch (Exception e) {
            logger.error("写入文件出错！", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (w != null) {
                    w.close();
                }
            } catch (Exception e) {
                logger.error("释放资源出错！", e);
            }
        }
    }


    public static void writeAppendFile(String filePath, String fileContent) {
        OutputStream os = null;
        Writer w = null;
        try {
            createFile(filePath);
            os = new BufferedOutputStream(new FileOutputStream(filePath, true));
            w = new OutputStreamWriter(os, "UTF-8");
            w.write(fileContent);
            w.flush();
        } catch (Exception e) {
            logger.error("写入文件出错！", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (w != null) {
                    w.close();
                }
            } catch (Exception e) {
                logger.error("释放资源出错！", e);
            }
        }
    }


    public static String getRealFileName(String fileName) {
        return FilenameUtils.getName(fileName);
    }


    public static boolean checkFileExists(String filePath) {
        return (new File(filePath)).exists();
    }


    public static String getBase64ImgDataByUrl(String httpurl, String imgurl) {
        if (StringUtils.isEmpty(imgurl)) return null;
        try {
            imgurl = httpurl + imgurl;
            byte[] imgData = getImgDataByUrl(imgurl);
            if (imgData == null) return "";
            return Base64Utils.encodeToString(imgData);
        } catch (Exception e) {
            logger.error("从网络图片" + imgurl + "获取base64数据异常：" + e.getLocalizedMessage());
            return null;
        }
    }


    public static byte[] getImgDataByUrl(String imgurl) throws Exception {
        if (!StringUtils.hasText(imgurl)) {
            return null;
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        byte[] buf = new byte[BUF_SIZE];
        int size = 0;

        url = new URL(imgurl);
        httpUrl = (HttpURLConnection) url.openConnection();
        httpUrl.setConnectTimeout(10000);


        httpUrl.connect();

        bis = new BufferedInputStream(httpUrl.getInputStream());


        while ((size = bis.read(buf)) != -1) {
            bos.write(buf, 0, size);
        }
        bos.close();
        bis.close();
        httpUrl.disconnect();
        return bos.toByteArray();
    }


    public static String getFileSuffix(String base64Data) {
        try {
            byte[] imgByte = Base64Utils.decodeFromString(base64Data.replaceAll(" ", "+"));
            FileType fileType = FileTypeJudge.getType(imgByte);
            if (null != fileType) {
                return fileType.name().toLowerCase();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }


    public static void saveBase64ToFile(String base64Data, String absolutePath) throws IOException {
        File file = createFile(absolutePath);
        OutputStream os = null;
        try {
            byte[] b = Base64Utils.decodeFromString(base64Data);
            os = new FileOutputStream(file);
            os.write(b);
            os.flush();
        } catch (Exception e) {
            logger.error("图片保存本地异常！", e);
            throw new IOException("图片保存本地异常!");
        } finally {
            if (os != null)
                try {
                    os.close();
                } catch (IOException e) {
                    logger.error(e.getLocalizedMessage());
                }
        }
    }

}
