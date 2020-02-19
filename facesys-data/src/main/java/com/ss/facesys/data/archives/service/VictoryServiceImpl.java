package com.ss.facesys.data.archives.service;

import com.github.pagehelper.PageHelper;
import com.ss.enums.StatusEnum;
import com.ss.facesys.data.archives.client.IVictoryService;
import com.ss.facesys.data.archives.common.model.CasePolice;
import com.ss.facesys.data.archives.common.model.Victory;
import com.ss.facesys.data.archives.common.model.VictoryStatistics;
import com.ss.facesys.data.archives.common.web.VictoryVO;
import com.ss.facesys.data.archives.mapper.VictoryMapper;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.NumberConstant;
import com.ss.spider.system.organization.mapper.OrganizationMapper;
import com.ss.spider.system.organization.model.Organization;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * 战果汇总
 *
 * @author chao
 * @create 2020/2/17
 * @email lishuangchao@ss-cas.com
 **/
@Service
@Transactional(rollbackFor = {Exception.class})
public class VictoryServiceImpl implements IVictoryService {

    @Autowired
    private VictoryMapper victoryMapper;

    @Autowired
    private OrganizationMapper organizationMapper;

    /**
     * 战果汇总分页查询
     *
     * @param victoryVO
     * @return
     */
    @Override
    public List<Victory> victoryPage(VictoryVO victoryVO) {
        PageHelper.startPage(victoryVO.getCurrentPage(), victoryVO.getPageSize());
        List<Victory> victories = this.victoryMapper.victoryPage(victoryVO);
        return victories;
    }

    /**
     * 账户树查询
     *
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Organization> treeData() {
        Example example = new Example(Organization.class);
        example.createCriteria().andEqualTo("status", StatusEnum.EFFECT.getCode());
        example.orderBy("seq").asc();
        List<Organization> organizations = organizationMapper.selectByExample(example);
        List<Organization> allUsers = this.victoryMapper.findAllUsers();
        organizations.addAll(allUsers);
        return createOrgTree(organizations);
    }

    /**
     * 添加战果汇总
     *
     * @param victoryVO
     * @return
     */
    @Override
    public int insertVictory(VictoryVO victoryVO) {
        victoryVO.setCreateTime(String.valueOf(System.currentTimeMillis()));
        victoryVO.setUpdateTime(String.valueOf(System.currentTimeMillis()));
        victoryVO.setStatus(NumberConstant.ONE);
        int result = this.victoryMapper.insertVictory(victoryVO);
        if(StringUtils.isNotBlank(victoryVO.getUserIds())){
            //查询账户名称
            List<CasePolice> users = this.victoryMapper.findUser(victoryVO);
            this.victoryMapper.insertPolice(users);
        }
        return result;
    }

    /**
     * 修改战果汇总信息
     *
     * @param victoryVO
     * @return
     */
    @Override
    public int updateVictory(VictoryVO victoryVO) {
        victoryVO.setUpdateTime(String.valueOf(System.currentTimeMillis()));
        this.victoryMapper.deletePolice(victoryVO);
        int result = this.victoryMapper.updateVictory(victoryVO);
        if(StringUtils.isNotBlank(victoryVO.getUserIds())){
            //查询账户名称
            List<CasePolice> users = this.victoryMapper.findUser(victoryVO);
            this.victoryMapper.insertPolice(users);
        }
        return result;
    }

    /**
     * 删除战果汇总
     *
     * @param victoryVO
     * @return
     */
    @Override
    public int deleteVictory(VictoryVO victoryVO) {
        this.victoryMapper.deletePolice(victoryVO);
        int result = this.victoryMapper.deleteVictory(victoryVO);
        return result;
    }

    /**
     * 查询战果汇总详情
     *
     * @param victoryVO
     * @return
     */
    @Override
    public Victory victoryDetail(VictoryVO victoryVO) {
        Victory result = this.victoryMapper.victoryDetail(victoryVO);
        return result;
    }

    /**
     * 查询战果总数
     * @return
     */
    @Override
    public VictoryStatistics findCount() {
        int firstWeekCount = this.victoryMapper.findFirstWeekCount();
        int secondWeekCount = this.victoryMapper.findSecondWeekCount();
        int thirdWeekCount = this.victoryMapper.findThirdWeekCount();
        int fourthWeekCount = this.victoryMapper.findFourthWeekCount();
        int fifthWeekCount = this.victoryMapper.findFifthWeekCount();
        int count = this.victoryMapper.findCount();
        int thisWeekCount = this.victoryMapper.findThisWeekCount();
        VictoryStatistics victoryStatistics = new VictoryStatistics();
        victoryStatistics.setFirstWeekCount(firstWeekCount);
        victoryStatistics.setSecondWeekCount(secondWeekCount);
        victoryStatistics.setThirdWeekCount(thirdWeekCount);
        victoryStatistics.setFourthWeekCount(fourthWeekCount);
        victoryStatistics.setFifthWeekCount(fifthWeekCount);
        victoryStatistics.setCount(count);
        victoryStatistics.setThisWeekCount(thisWeekCount);
        return victoryStatistics;
    }

    /**
     * 查询战果排行
     * @return
     */
    @Override
    public List<CasePolice> findRank() {
        List<CasePolice> rank = this.victoryMapper.findRank();
        return rank;
    }

    private List<Organization> createOrgTree(List<Organization> organizationList) {
        if (CollectionUtils.isEmpty(organizationList)) {
            return Collections.emptyList();
        }
        // 创建根节点
        Organization root = new Organization();
        // 组装Map数据
        Map<String, Organization> dataMap = new HashMap<>(16);
        for (Organization organization : organizationList) {
            dataMap.put(organization.getOrgId(), organization);
        }
        // 组装树形结构
        Set<Map.Entry<String, Organization>> entrySet = dataMap.entrySet();
        for (Map.Entry<String, Organization> entry : entrySet) {
            Organization currentNode = entry.getValue();
            if (StringUtils.isEmpty(currentNode.getParentId()) || "0".equals(currentNode.getParentId())) {
                root.getChildren().add(currentNode);
            } else {
                dataMap.get(currentNode.getParentId()).getChildren().add(currentNode);
            }
        }
        return root.getChildren();
    }
}
