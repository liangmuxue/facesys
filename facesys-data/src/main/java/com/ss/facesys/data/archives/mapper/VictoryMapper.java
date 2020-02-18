package com.ss.facesys.data.archives.mapper;

import com.ss.facesys.data.archives.common.model.Victory;
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
     * 战果汇总分页查询
     * @param victoryVO
     * @return
     */
    List<Victory> victoryPage(VictoryVO victoryVO);

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
     * 查询账户姓名
     * @param victoryVO
     * @return
     */
    String findUserNames(VictoryVO victoryVO);
}
