package com.ss.facesys.schedule.collect.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.ss.facesys.util.DateUtil;
import com.ss.facesys.util.SpringUtil;
import com.ss.mapper.LocalCameraMapper;
import com.ss.thread.CameraThreadPool;
import com.ss.utils.DeviceUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;

/**
 * 照片删除定时任务
 * @author 李爽超 chao
 * @create 2019/09/16
 * @email lishuangchao@ss-cas.com
 **/
public class DeletePictruesJob implements SimpleJob {

    public static final Log LOG = LogFactory.getLog(DeletePictruesJob.class);
    private LocalCameraMapper localCameraMapper = SpringUtil.getBean(LocalCameraMapper.class);

    @Override
    public void execute(ShardingContext shardingContext) {
        LOG.info("定时任务DeletePictruesJob已经启动" + DateUtil.getCurrentSqlTimestampString());
        DeletePictrues deletePictrues = new DeletePictrues();
        //放入线程池
        CameraThreadPool.getThread().execute(deletePictrues);
    }
    public class DeletePictrues implements Runnable{
        /**
         * 删除2分钟之前的所有照片
         */
        @Override
        public void run() {
            String url = DeviceUtil.getCutFlowUrl();
            Date date = new Date(System.currentTimeMillis() - 1000 * 60 * 2);
            File folder = new File(url);
            if (folder.exists()) {
                //存放文件夹
                LinkedList<File> directories = new LinkedList<>();
                if (folder.isDirectory()) {
                    directories.add(folder);
                } else {
                    folder.delete();
                }
                while (directories.size() > 0) {
                    File[] files = directories.removeFirst().listFiles();
                    if (files != null) {
                        for (File f : files) {
                            if (f.isDirectory()) {
                                directories.add(f);
                            } else if (new Date(f.lastModified()).before(date)){
                                f.delete();
                            }
                        }
                    }
                }
            }
        }
    }
}


