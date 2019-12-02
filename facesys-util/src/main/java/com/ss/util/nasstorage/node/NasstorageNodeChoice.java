package com.ss.util.nasstorage.node;

import com.ss.util.nasstorage.config.properties.NasstorageProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;


public class NasstorageNodeChoice {

    private static final Logger logger = LoggerFactory.getLogger(NasstorageNodeChoice.class);


    public NasstorageNode getNasstorageNode(NasstorageProperties properties) {
        List<NasstorageNode> nodeList = getNasstorageMountList(properties);
        NasstorageNode retNode = null;

        if (CollectionUtils.isEmpty(nodeList)) {
            logger.error("nas存储节点配置错误");
            return null;
        }

        if (1 == nodeList.size()) {
            retNode = (NasstorageNode) nodeList.get(0);
        } else {

            int weightSum = calculateWeightSum(nodeList);
            int random = (new Random()).nextInt(weightSum);
            for (NasstorageNode node : nodeList) {
                random -= node.getWeight().intValue();
                if (random < 0) {
                    retNode = node;
                    break;
                }
            }
        }
        logger.info("已选择nas存储节点:{}", retNode.toString());
        return retNode;
    }


    public List<NasstorageNode> getNasstorageMountList(NasstorageProperties properties) {
        String[] deputyArr = null;
        String[] pathArr = null;
        String[] weightArr = null;

        if (StringUtils.isEmpty(properties.getMountDeputy()) ||
                StringUtils.isEmpty(properties.getMountPath()) ||
                StringUtils.isEmpty(properties.getWeight())) {
            return null;
        }


        try {
            deputyArr = properties.getMountDeputy().split(",");
            pathArr = properties.getMountPath().split(",");
            weightArr = properties.getWeight().split(",");
            int size = deputyArr.length;
            if (deputyArr.length == pathArr.length && pathArr.length == weightArr.length) {

                List<NasstorageNode> list = new ArrayList<NasstorageNode>();
                for (int index = 0; index < size; index++) {
                    list.add(new NasstorageNode(properties.getServerDeputy(), deputyArr[index], pathArr[index], Integer.valueOf(weightArr[index])));
                }
                return list;
            }
        } catch (Exception exp) {
            return null;
        }
        return null;
    }

    private int calculateWeightSum(List<NasstorageNode> nodeList) {
        int sum = 0;
        for (NasstorageNode node : nodeList) {
            sum += node.getWeight().intValue();
        }
        return sum;
    }

}
