package com.ss.facesys.data.archives.client;

import com.ss.facesys.data.archives.common.model.CasePolice;
import com.ss.facesys.data.archives.common.model.Victory;
import com.ss.facesys.data.archives.common.model.VictoryStatistics;
import com.ss.facesys.data.archives.common.web.VictoryVO;
import com.ss.spider.system.organization.model.Organization;

import java.util.List;
/**
* 战果汇总
* @author chao
* @create 2020/2/17
* @email lishuangchao@ss-cas.com
**/
public interface IVictoryService {
  /**
   * 战果汇总列表查询
   * @param victoryVO
   * @return
   */
  List<Victory> victoryList(VictoryVO victoryVO);

  /**
   * 账户树查询
   * @return
   */
  List<Organization> treeData();

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
   * 查询战果统计
   * @return
   */
  VictoryStatistics findCount();

  /**
   * 查询战果排行
   * @return
   */
  List<CasePolice> findRank();
}
