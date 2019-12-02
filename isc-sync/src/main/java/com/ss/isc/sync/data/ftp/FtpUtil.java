package com.ss.isc.sync.data.ftp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.pool2.ObjectPool;
import org.springframework.util.Assert;


public class FtpUtil {

    public static final Log LOG = LogFactory.getLog(FtpUtil.class);

    private static ObjectPool<FTPClient> intranetClientPool;

    private static int CONNECT_NUM = 3;

    private static FTPClient ftpClient;

    private static boolean intranetHasInit;

    public static void init(ObjectPool<FTPClient> intranetPool) {
        if (!intranetHasInit) {
            synchronized (FtpUtil.class) {
                if (!intranetHasInit) {
                    intranetClientPool = intranetPool;
                    intranetHasInit = true;
                }
            }
        }
    }


    public static List<String> readFileByLine(String remoteFilePath, String remotePath) throws IOException {
        ftpClient = getFtpClient();
        try (InputStream in = ftpClient.retrieveFileStream(encodingPath(remotePath + remoteFilePath));
             BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            return (List) br.lines().map(line -> StringUtils.trimToEmpty(line)).filter(line -> StringUtils.isNotEmpty(line))
                    .collect(Collectors.toList());
        } finally {

            ftpClient.completePendingCommand();
            releaseFtpClient(ftpClient);
        }
    }


    public static FTPFile[] retrieveFTPFiles(String remotePath) throws IOException {
        ftpClient = getFtpClient();
        try {
            return ftpClient.listFiles(encodingPath(remotePath + "/"), file -> (file != null && file.getSize() > 0L));
        } finally {

            releaseFtpClient(ftpClient);
        }
    }


    public static List<String> retrieveFileNames(String remotePath) throws IOException {
        FTPFile[] ftpFiles = retrieveFTPFiles(remotePath);
        if (ArrayUtils.isEmpty(ftpFiles)) {
            return new ArrayList();
        }
        return (List) Arrays.stream(ftpFiles).filter(Objects::nonNull).map(FTPFile::getName).collect(Collectors.toList());
    }


    private static String encodingPath(String path) throws UnsupportedEncodingException {
        return new String(path.replaceAll("//", "/").getBytes("GBK"), "iso-8859-1");
    }


    private static FTPClient getFtpClient() {
        checkFtpClientPoolAvailable();
        ftpClient = null;
        Exception ex = null;

        for (int i = 0; i < CONNECT_NUM; i++) {
            try {
                ftpClient = (FTPClient) intranetClientPool.borrowObject();
                ftpClient.changeWorkingDirectory("/");

                break;
            } catch (Exception e) {
                ex = e;
            }
        }
        if (ftpClient == null) {
            throw new RuntimeException("Could not get a ftpClient from the pool", ex);
        }
        return ftpClient;
    }


    private static void releaseFtpClient(FTPClient ftpClient) {
        if (ftpClient == null) {
            return;
        }

        try {
            intranetClientPool.returnObject(ftpClient);
        } catch (Exception e) {
            LOG.error("Could not return the ftpClient to the pool", e);

            if (ftpClient.isAvailable()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException iOException) {
                }
            }
        }
    }


    private static void checkFtpClientPoolAvailable() {
        Assert.state(intranetHasInit, "FTP未启用或连接失败！");
    }


    public static FTPFile[] getFiles(String remotePath) throws IOException {
        ftpClient = getFtpClient();
        FTPFile[] files = null;
        try {
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setFileType(2);


            boolean change = ftpClient.changeWorkingDirectory(remotePath);
            if (change) {
                files = ftpClient.listFiles();
            }
            ftpClient.logout();
        } catch (IOException e) {
            LOG.error("获取文件列表异常", e);
        } finally {
            releaseFtpClient(ftpClient);
        }
        return files;
    }

    public static boolean downFiles(List<String> files, String localPath, String remotePath) {
        ftpClient = getFtpClient();
        boolean result = false;
        try {
            if (null != files && files.size() > 0) {
                LOG.debug("文件下载 - FTP目录：" + remotePath + "，下载至:" + localPath);
                OutputStream is = null;
                for (String ff : files) {
                    File okFile = new File(localPath + ff);
                    is = new FileOutputStream(okFile);
                    ftpClient.retrieveFile(ff, is);
                    is.close();
                    File file = new File(localPath + ff + ".ok");
                    is = new FileOutputStream(file);
                    ftpClient.retrieveFile(file.getName(), is);
                    is.close();
                    ftpClient.deleteFile(remotePath + ff);
                    ftpClient.deleteFile(remotePath + ff + ".ok");
                    LOG.debug("文件名：" + file.getName() + " 下载并删除成功");
                }

                ftpClient.logout();
                result = true;
            }

        } catch (IOException e) {
            LOG.error("下载文件异常", e);
        } finally {
            releaseFtpClient(ftpClient);
        }
        return result;
    }


    public static File[] uploadFile(String localPath, String remotePath) {
        ftpClient = getFtpClient();
        File[] files = null;

        try {
            boolean change = ftpClient.changeWorkingDirectory(remotePath);
            ftpClient.setFileType(2);

            if (change) {
                File file = new File(localPath);
                files = file.listFiles();

                for (File f : files) {

                    InputStream input = new FileInputStream(f);
                    ftpClient.storeFile(f.getName(), input);
                    input.close();
                    f.delete();
                }
            }

            ftpClient.logout();
        } catch (IOException e) {
            LOG.error("目录文件上传异常", e);
        } finally {
            releaseFtpClient(ftpClient);
        }
        return files;
    }


    public static boolean uploadOneFile(String filePath, String remotePath) {
        ftpClient = getFtpClient();
        boolean result = false;
        File file = null;

        try {
            boolean change = ftpClient.changeWorkingDirectory(remotePath);
            ftpClient.setFileType(2);

            if (change) {
                file = new File(filePath);
                if (null != file) {
                    InputStream input = new FileInputStream(file);
                    ftpClient.storeFile(file.getName(), input);
                    input.close();
                    file.delete();
                }
            }

            ftpClient.logout();
            result = true;
        } catch (IOException e) {
            LOG.error("单个文件上传异常", e);
        } finally {
            releaseFtpClient(ftpClient);
        }
        return result;
    }

    public static boolean deleteFile(FTPFile[] files, String remotePath) {
        ftpClient = getFtpClient();
        try {
            if (null != files && files.length > 0) {
                LOG.debug("文件删除 - 目录：" + remotePath + "下的文件");
                for (FTPFile ff : files) {
                    ftpClient.deleteFile(remotePath + ff.getName());
                    LOG.debug("文件删除 - 文件名：" + ff.getName() + "删除成功");
                }

            }
        } catch (IOException e) {
            LOG.error("删除文件上传异常", e);
        } finally {
            releaseFtpClient(ftpClient);
        }
        return true;
    }

    public static boolean deleteFile(String remotePath, String filename) {
        ftpClient = getFtpClient();
        boolean flag = false;

        try {
            ftpClient.changeWorkingDirectory(remotePath);
            ftpClient.dele(filename);
            flag = true;
            LOG.info("删除文件:" + filename);
        } catch (Exception e) {
            LOG.error("删除文件失败", e);
        } finally {
            releaseFtpClient(ftpClient);
        }
        return flag;
    }


    @Deprecated
    public static InputStream downloadFile(String filename) throws IOException {
        InputStream in = null;
        try {
            FTPClient ftpClient = getFtpClient();
            ftpClient.enterLocalPassiveMode();

            setFileType(ftpClient, 2);
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                throw new IOException("failed to connect to the FTP Server");
            }
            ftpClient.changeWorkingDirectory(filename);


            in = ftpClient.retrieveFileStream(filename);

        } catch (Exception e) {
            LOG.error("ERR : download file " + filename + " from ftp : failed!", e);
        }
        return in;
    }


    private static void setFileType(FTPClient ftpClient, int fileType) {
        try {
            ftpClient.setFileType(fileType);
        } catch (Exception e) {
            LOG.error("ftp设置传输文件的类型时失败！", e);
        }
    }


    public static long getLastTime(String remotePath) {
        ftpClient = getFtpClient();
        FTPFile[] fileList = null;
        String path = remotePath;
        path = path.substring(0, path.length() - 1);
        path = path.substring(0, path.lastIndexOf("/") + 1);
        long last = 0L;
        try {
            fileList = ftpClient.listFiles(path);
            if (null != fileList && fileList.length > 0) {
                last = fileList[0].getTimestamp().getTime().getTime();
            }
        } catch (IOException e) {
            LOG.error("获取文件夹最后的更新", e);
        } finally {
            releaseFtpClient(ftpClient);
        }
        return last;
    }

    public static List<String> getFileNameList(String remotePath) throws IOException {
        List<String> list = null;
        FTPFile[] fileList = null;
        try {
            fileList = getFiles(remotePath);
            if (null != fileList && fileList.length > 0) {
                List<FTPFile> allFile = new ArrayList<FTPFile>(fileList.length);

                Collections.addAll(allFile, fileList);

                Collections.sort(allFile, new Comparator<FTPFile>() {
                    public int compare(FTPFile file, FTPFile newFile) {
                        if (file.getTimestamp().getTime().getTime() < newFile.getTimestamp().getTime().getTime()) {
                            return 1;
                        }
                        if (file.getTimestamp().getTime().getTime() == newFile.getTimestamp().getTime()
                                .getTime()) {
                            return 0;
                        }

                        return -1;
                    }
                });


                list = new ArrayList<String>();
                for (FTPFile f : allFile) {
                    list.add(f.getName());
                }
            }

        } catch (Exception e) {
            LOG.error("获取文件名称列表", e);
        }

        return list;
    }


    public static List<String> getFileNameList(String regex, String remotePath) throws IOException {
        List<String> list = null;
        ftpClient = getFtpClient();
        FTPFile[] fileList = null;
        try {
            FtpFilter filter = new FtpFilter(regex);
            fileList = ftpClient.listFiles(remotePath, filter);

            if (null != fileList && fileList.length > 0) {
                List<FTPFile> allFile = new ArrayList<FTPFile>(fileList.length);
                Collections.addAll(allFile, fileList);
                Collections.sort(allFile, new Comparator<FTPFile>() {
                    public int compare(FTPFile file, FTPFile newFile) {
                        if (file.getTimestamp().getTime().getTime() < newFile.getTimestamp().getTime().getTime()) {
                            return 1;
                        }
                        if (file.getTimestamp().getTime().getTime() == newFile.getTimestamp().getTime().getTime()) {
                            return 0;
                        }
                        return -1;
                    }
                });
                list = new ArrayList<String>();
                for (FTPFile f : allFile) {
                    list.add(f.getName());
                }
            }

        } catch (IOException e) {
            LOG.error("根据表达式过滤文件", e);
        } finally {
            releaseFtpClient(ftpClient);
        }

        return list;
    }


    public static boolean checkDirExists(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return file.mkdirs();
        }
        return true;
    }

    public static interface IDateType {

        public static final String DEVICE = "_device_";
        public static final String DOOR_FLOW = "_door_flow_";
        public static final String DOORINFO = "_doorinfo_";
        public static final String VILLAGE = "_village_";
        public static final String HOUSE = "_house_";
        public static final String RESIDENT = "_resident_";
        public static final String STREET = "_street_";
        public static final String REGION = "_region_";
        public static final String VEHICLE = "_vehicle_";

    }

}
