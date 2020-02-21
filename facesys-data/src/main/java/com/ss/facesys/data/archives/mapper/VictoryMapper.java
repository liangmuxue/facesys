package com.ss.facesys.data.archives.mapper;

import com.ss.facesys.data.archives.common.model.CasePolice;
import com.ss.facesys.data.archives.common.model.Victory;
import com.ss.facesys.data.archives.common.model.VictoryStatistics;
import com.ss.facesys.data.archives.common.web.VictoryVO;
import com.ss.mapper.SsMapper;
import com.ss.spider.system.organization.model.Organization;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
/**
* 战果汇总
* @author chao
* @create 2020/2/17
* @email lishuangchao@ss-cas.com
**/
@Component
@Mapper
public interface VictoryMapper extends SsMapper<Victory> {
    /**
     * 战果汇总列表查询
     * @param victoryVO
     * @return
     */
    List<Victory> victoryList(VictoryVO victoryVO);

    /**
     * 查询所有账户
     * @return
     */
    List<Organization> findAllUsers();

    /**
     * 添加战果汇总
     * @param victoryVO
     * @return
     */
    int insertVictory(VictoryVO victoryVO);

    /**
     * 修改战果汇总信息
     * @param victoryVO
     * @return
     */
    int updateVictory(VictoryVO victoryVO);

    /**
     * 删除战果汇总
     * @param victoryVO
     * @return
     */
    int deleteVictory(VictoryVO victoryVO);

    /**
     * 查询战果汇总详情
     * @param victoryVO
     * @return
     */
    Victory victoryDetail(VictoryVO victoryVO);

    /**
     * 查询账户
     * @param victoryVO
     * @return
     */
    List<CasePolice> findUser(VictoryVO victoryVO);

    /**
     * 添加破案民警
     * @param list
     * @return
     */
    int insertPolice(List<CasePolice> list);

    /**
     * 删除破案民警
     * @param victoryVO
     * @return
     */
    int deletePolice(VictoryVO victoryVO);

    /**
     * 查询本周战果总数
     * @return
     */
    int findThisWeekCount();

    /**
     * 查询上周战果总数
     * @return
     */
    int findFirstWeekCount();

    /**
     * 查询上上周战果总数
     * @return
     */
    int findSecondWeekCount();

    /**
     * 查询上上上周战果总数
     * @return
     */
    int findThirdWeekCount();

    /**
     * 查询上上上上周战果总数
     * @return
     */
    int findFourthWeekCount();

    /**
     * 查询上上上上上周战果总数
     * @return
     */
    int findFifthWeekCount();

    /**
     * 查询战果总数
     * @return
     */
    int findCount();

    /**
     * 查询战果排行
     * @return
     */
    List<CasePolice> findRank();
}
