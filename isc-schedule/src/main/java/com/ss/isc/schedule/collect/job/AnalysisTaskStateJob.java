package com.ss.isc.schedule.collect.job;

import com.ss.isc.data.access.client.IAccessService;
import com.ss.isc.data.collect.client.IAnalysisTaskService;
import com.ss.isc.data.collect.common.model.AnalysisTask;
import com.ss.isc.util.DateUtil;
import com.ss.isc.util.JsonUtil;
import com.ss.isc.util.SpringUtil;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.em.Enums;
import com.ss.isc.util.thread.SysThreadPool;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class AnalysisTaskStateJob implements SimpleJob {

    public static final Log LOG = LogFactory.getLog(AnalysisTaskStateJob.class);


    private IAccessService accessService = SpringUtil.getBean(IAccessService.class);
    private IAnalysisTaskService analysisTaskService = SpringUtil.getBean(IAnalysisTaskService.class);


    public void execute(ShardingContext shardingContext) {
        LOG.info("定时任务AnalysisAddJob已经启动" + DateUtil.getCurrentSqlTimestampString());
        StateJobThread job = new StateJobThread();
        SysThreadPool.getThread().execute(job);
    }


    public class StateJobThread
            implements Runnable {

        public void run() {
            List<AnalysisTask> list = AnalysisTaskStateJob.this.analysisTaskService.queryAnalysisTask(Enums.AnalysisTaskState.RUNNING.getCode(), null);

            if (!list.isEmpty()) {
                List<String> taskId = new ArrayList<String>();
                Map<String, AnalysisTask> map = new HashMap<String, AnalysisTask>();
                for (AnalysisTask at : list) {
                    taskId.add(at.getId());
                    map.put(at.getId(), at);
                }

                JSONObject resultStr = AnalysisTaskStateJob.this.accessService.getAnalysisTaskPages(null, taskId, null, null, null);
                if (StringUtils.checkSuccess(resultStr)) {
                    List<AnalysisTask> editList = new ArrayList<AnalysisTask>();
                    AnalysisTask editObj = null;
                    JSONArray arr = JsonUtil.getJsonArray(resultStr);
                    JSONObject obj = null;
                    if (arr.size() > 0) {
                        for (int i = 0; i < arr.size(); i++) {
                            obj = arr.getJSONObject(i);

                            if (map.containsKey(obj.getString("taskId"))) {
                                editObj = (AnalysisTask) map.get(obj.getString("taskId"));
                                editObj.setState(obj.getInteger("state").intValue());
                                editList.add(editObj);
                            }
                        }


                        if (!editList.isEmpty())
                            for (AnalysisTask at : editList)
                                AnalysisTaskStateJob.this.analysisTaskService.updateAnalysisTaskState(at);
                    }
                }
            }
        }

    }

}
