package com.ss.facesys.data.baseinfo.common.job;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockSimpleJob {

    @Autowired
    private ZookeeperRegistryCenter regCenter;

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter(@Value("${elaticjob.zookeeper.server-lists}") String serverList, @Value("${elaticjob.zookeeper.namespace}") String namespace) {
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverList, namespace));
    }

    @Autowired
    private DataSourceConfig dataSource;

    public void addSimpleJobScheduler(Class<? extends SimpleJob> jobClass, String cron, int shardingTotalCount, String shardingItemParameters, boolean isSave) {
        JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount).shardingItemParameters(shardingItemParameters).jobParameter("job参数").build();
        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(coreConfig, jobClass.getCanonicalName());
        JobScheduler jobScheduler = null;
        if (isSave) {
            JobEventRdbConfiguration jobEventRdbConfiguration = new JobEventRdbConfiguration(this.dataSource.init());
            jobScheduler = new JobScheduler(this.regCenter, LiteJobConfiguration.newBuilder(simpleJobConfig).build(), jobEventRdbConfiguration, new com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener[0]);
        } else {
            jobScheduler = new JobScheduler(this.regCenter, LiteJobConfiguration.newBuilder(simpleJobConfig).build(), new com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener[0]);
        }
        jobScheduler.init();
    }

}
