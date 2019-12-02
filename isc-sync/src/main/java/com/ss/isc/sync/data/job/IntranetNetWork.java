package com.ss.isc.sync.data.job;

import com.ss.isc.data.resource.client.IRegionService;
import com.ss.isc.data.resource.common.model.Region;
import com.ss.isc.sync.data.ftp.FtpUtil;
import com.ss.isc.sync.data.service.ISyncService;
import com.ss.isc.util.DateUtil;
import com.ss.isc.util.PropertiesUtil;
import com.ss.isc.util.SpringUtil;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.autoconfigure.FtpProperties;
import com.ss.isc.util.jedis.JedisUtil;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class IntranetNetWork implements SimpleJob {

    public JedisUtil jedisUtil = (JedisUtil) SpringUtil.getBean(JedisUtil.class);
    private ISyncService syncService = (ISyncService) SpringUtil.getBean(ISyncService.class);
    private IRegionService regionService = (IRegionService) SpringUtil.getBean(IRegionService.class);

    public static int testnum = 0;


    public static boolean checkDir = false;


    public static final Log LOG = LogFactory.getLog(IntranetNetWork.class);


    public static final String REGEX = "^\\S+\\.(json.ok|JSON.OK)$";


    public void execute(ShardingContext shardingContext) {
        try {
            if (!checkDir) {
                checkDir = FtpUtil.checkDirExists(PropertiesUtil.getFtpFile());
            }
            String key = "ISC_SYNC_DOOR_FLOW";
            if (!this.jedisUtil.hasKey(key)) {
                this.jedisUtil.set(key, DateUtil.getCurrentSqlTimestampString());
                int vc = 0, rc = 0, wc = 0, bc = 0, hc = 0, dc = 0, fc = 0, cc = 0;

                List<String> fileNameList = FtpUtil.getFileNameList("^\\S+\\.(json.ok|JSON.OK)$", FtpProperties.getIntranetFilePath());

                if (null != fileNameList && fileNameList.size() > 0) {
                    List<String> region = null;
                    List<String> street = null;
                    List<String> village = null;
                    List<String> doorinfo = null;


                    List<String> doorflow = null;
                    List<String> device = null;
                    List<String> vehicle = null;
                    Map<String, Region> regionMap = null;


                    for (String name : fileNameList) {
                        name = getName(name);
                        if (name.indexOf("_region_") > 0) {
                            region = StringUtils.pickList(region, name);
                            continue;
                        }
                        if (name.indexOf("_street_") > 0) {
                            street = StringUtils.pickList(street, name);
                            continue;
                        }
                        if (name.indexOf("_vehicle_") > 0) {
                            vehicle = StringUtils.pickList(vehicle, name);
                        }
                    }

                    if (null != region) {
                        rc = this.syncService.synchroThirdRegion(region, FtpProperties.getIntranetFilePath());
                    }
                    if (null != street) {
                        wc = this.syncService.synchroStreet(street, FtpProperties.getIntranetFilePath());
                    }

                    if (null != vehicle) {
                        cc = this.syncService.synchroVehicle(vehicle, FtpProperties.getIntranetFilePath());
                    }


                    List<Region> regionList = this.regionService.getBindThirdRegion();

                    if (!StringUtils.isEmpty(regionList)) {
                        regionMap = new HashMap<String, Region>();
                        for (Region r : regionList) {
                            regionMap.put(r.getRegionCode(), r);
                        }

                        for (String name : fileNameList) {
                            name = getName(name);
                            if (name.indexOf("_village_") > 0) {
                                village = StringUtils.pickList(village, name);
                                continue;
                            }
                            if (name.indexOf("_doorinfo_") > 0) {
                                doorinfo = StringUtils.pickList(doorinfo, name);


                                continue;
                            }


                            if (name.indexOf("_device_") > 0) {
                                device = StringUtils.pickList(device, name);
                                continue;
                            }
                            if (name.indexOf("_door_flow_") > 0) {
                                doorflow = StringUtils.pickList(doorflow, name);
                            }
                        }

                        if (null != village) {
                            vc = this.syncService.synchroVillage(village, FtpProperties.getIntranetFilePath());
                        }
                        if (null != doorinfo) {
                            bc = this.syncService.synchroDoorinfo(doorinfo, FtpProperties.getIntranetFilePath());
                        }


                        if (null != device) {
                            if (device.size() > 5) {
                                List<List<String>> pList = StringUtils.averageAssign(device, 5);
                                for (List<String> ls : pList) {
                                    dc += this.syncService.synchroDevice(ls, FtpProperties.getIntranetFilePath(), regionMap);
                                }
                            } else {
                                dc = this.syncService.synchroDevice(device, FtpProperties.getIntranetFilePath(), regionMap);
                            }
                        }
                        if (null != doorflow) {
                            if (doorflow.size() > 2) {

                                List<List<String>> pList = StringUtils.averageAssign(doorflow, 2);
                                for (List<String> ls : pList) {
                                    fc += this.syncService.synchroDoorFlow(ls, FtpProperties.getIntranetFilePath());
                                }
                            } else {
                                fc = this.syncService.synchroDoorFlow(doorflow, FtpProperties.getIntranetFilePath());
                            }

                        }
                    } else {

                        LOG.error("数据同步 - 数据同步前需要先进行社区区域和第三方设备区域进行关联设置！！！");
                    }
                }
                this.jedisUtil.del(new String[]{key});
                LOG.info("已同步区域数量：" + rc);
                LOG.info("已同步街道数量：" + wc);
                LOG.info("已同步小区数量：" + vc);
                LOG.info("已同步楼栋数量：" + bc);

                LOG.info("已同步设备数量：" + dc);
                LOG.info("已同步流水数量：" + fc);
                LOG.info("已同步过车数量：" + cc);
                testnum += rc + wc + vc + bc + hc + dc + fc + cc;
                LOG.info("合计同步文件总数：" + testnum);

            }

        } catch (Exception e) {
            LOG.error("同步数据异常：", e);
        }
    }


    private String getName(String name) {
        if (!StringUtils.isEmpty(name)) {
            name = name.substring(0, name.length() - 3);
        }
        return name;
    }

}
