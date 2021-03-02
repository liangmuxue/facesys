package com.ss.facesys.schedule.collect.job;

import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.collect.client.IAlarmService;
import com.ss.facesys.data.collect.client.IAnalysisTaskService;
import com.ss.facesys.data.collect.common.model.AlarmRecord;
import com.ss.facesys.data.collect.common.model.AnalysisTask;
import com.ss.facesys.util.DateUtil;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.SpringUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CacheConstant;
import com.ss.facesys.util.em.Enums;
import com.ss.facesys.util.jedis.JedisUtil;
import com.ss.facesys.util.thread.SysThreadPool;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class AnalysisTaskJob implements SimpleJob {

    public static final Log LOG = LogFactory.getLog(AnalysisTaskJob.class);


    public JedisUtil jedisUtil = SpringUtil.getBean(JedisUtil.class);
    private IAccessService accessService = SpringUtil.getBean(IAccessService.class);
    private IAnalysisTaskService analysisTaskService = SpringUtil.getBean(IAnalysisTaskService.class);
    private IAlarmService alarmService = SpringUtil.getBean(IAlarmService.class);


    public void execute(ShardingContext shardingContext) {
        LOG.info("定时任务AnalysisAddJob已经启动" + DateUtil.getCurrentSqlTimestampString());
        String key = CacheConstant.TASK_KEY + DateUtil.getYesterdayCurrentDay();

        this.jedisUtil.del(new String[]{key});

        boolean isRun = true;
        while (isRun) {
            if (!this.jedisUtil.hasKey(key)) {

                isRun = false;
                AddTaskThread job = new AddTaskThread();
                SysThreadPool.getThread().execute(job);

                continue;
            }
            try {
                Thread.sleep(PropertiesUtil.getJobSleepTime().longValue());
            } catch (InterruptedException e) {
                LOG.error("创建聚类 分析异常", e);
            }
        }
    }


    public AnalysisTask addAnalysisTaskFactory(String taskId, int taskType) {
        AnalysisTask analysisTask = new AnalysisTask();
        analysisTask.setId(taskId);
        analysisTask.setTaskName(taskId);
        analysisTask.setState(Enums.AnalysisTaskState.INIT.getCode());
        analysisTask.setTaskType(taskType);
        return analysisTask;
    }

    public class AddTaskThread
            implements Runnable {

        public void run() {
            String key = CacheConstant.TASK_KEY + DateUtil.getYesterdayCurrentDay();


            if (!AnalysisTaskJob.this.jedisUtil.hasKey(key)) {
                Date[] time = DateUtil.getStartAndEndDate(PropertiesUtil.getAddPersonJudgedDays().intValue());

                //查询陌生人布控任务
                List<AlarmRecord> alarmDateList = AnalysisTaskJob.this.alarmService.queryAnalysisData(null, null, Enums.AlarmMonitorType.STRANGER.getCode(), time[0], time[1]);

                List<List<AlarmRecord>> strangerGroup = new ArrayList<List<AlarmRecord>>();
                List<AlarmRecord> strangerList = new ArrayList<AlarmRecord>();

                if (!alarmDateList.isEmpty()) {
                    for (AlarmRecord ad : alarmDateList) {
                        if (strangerList.size() < PropertiesUtil.getTaskMaxNum()) {
                            strangerList.add(ad);
                            continue;
                        }
                        strangerGroup.add(strangerList);
                        strangerList = new ArrayList<AlarmRecord>();
                    }

                    if (!StringUtils.isEmpty(strangerList)) {
                        strangerGroup.add(strangerList);
                    }
                    List<String> taskIds = new ArrayList<String>();

                    List<String> sTaskIds = AnalysisTaskJob.this.createTask(strangerGroup, Enums.AnalysisTaskType.ADD.getCode());
                    if (!StringUtils.isEmpty(sTaskIds)) {
                        taskIds.addAll(sTaskIds);
                    }

                    if (!taskIds.isEmpty()) {
                        AnalysisTaskJob.this.jedisUtil.set(key, taskIds);
                    }
                }
            } else {
                AnalysisTaskJob.LOG.error(AnalysisTaskJob.this.jedisUtil.get(key) + "人口感知聚类任务已经存在不能重复创建");
            }
        }

    }


    private List<String> createTask(List<List<AlarmRecord>> list, int taskType) {
        List<String> taskIds = null;
        String taskId = null;
        if (!StringUtils.isEmpty(list)) {
            taskIds = new ArrayList<String>();
            for (List<AlarmRecord> alarmList : list) {
                //设备ID和对应抓拍ID主键
                List<Map<String, Object>> groupList = deviceIdsGroup(alarmList);
                Map<String, Object> parm = new HashMap<String, Object>();
                parm.put("groups", groupList);
                LOG.error("创建聚类任务: groupList:" + JSON.toJSONString(parm));
                JSONObject resultStr = this.accessService.addAnalysisTask(
                        DateUtil.getYesterdayCurrentTime(), Float.valueOf(PropertiesUtil.getThreShold()), 1, groupList);


                taskId = insertAnalysis(resultStr, taskType);
                if (!StringUtils.isEmpty(taskId)) {
                    taskIds.add(taskId);
                }
            }
        }
        return taskIds;
    }


    public List<Map<String, Object>> deviceIdsGroup(List<AlarmRecord> list) {
        List<Map<String, Object>> groupList = new ArrayList<Map<String, Object>>();
        Map<String, List<String>> groups = null;
        List<String> captureIds = null;
        captureIds = new ArrayList<String>();

        if (!StringUtils.isEmpty(list)) {
            groups = new HashMap<String, List<String>>();

            for (AlarmRecord ad : list) {
                if (groups.containsKey(ad.getDeviceId())) {
                    captureIds = (List) groups.get(ad.getDeviceId());
                    captureIds.add(ad.getCaptureId());
                    continue;
                }
                captureIds = new ArrayList<String>();
                captureIds.add(ad.getCaptureId());
                groups.put(String.valueOf(ad.getDeviceId()), captureIds);
            }

            for (String k : groups.keySet()) {
                Map<String, Object> item = new HashMap<String, Object>();
                item.put("deviceId", k);
                item.put("captureIds", groups.get(k));
                groupList.add(item);
            }
        }
        return groupList;
    }


    private String insertAnalysis(JSONObject resultStr, int taskType) {
        String taskId = null;
        if ("00000000".equals(resultStr.getString("code"))) {

            AnalysisTask analysisTask = addAnalysisTaskFactory(resultStr.getString("data"), taskType);

            int count = this.analysisTaskService.insertAnalysisTask(analysisTask);
            if (count > 0) {
                taskId = analysisTask.getId();
            }
        } else {
            LOG.error("创建聚类任务 - 创建失败，" + resultStr.getString("message"));
        }
        return taskId;
    }

}
