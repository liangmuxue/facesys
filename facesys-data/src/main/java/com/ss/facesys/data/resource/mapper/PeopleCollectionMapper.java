package com.ss.facesys.data.resource.mapper;

import com.ss.facesys.data.resource.common.model.PeopleCollection;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 实有人口收藏mapper
 * @author FrancisYs
 * @date 2019/08/09
 * @email yaoshuai@ss-cas.com
 */
@Repository
@Mapper
public interface PeopleCollectionMapper extends SsMapper<PeopleCollection> {

    /**
     * 查询实有人口收藏列表
     * @param peopleCollection 参数对象
     * @return 实有人口收藏关系集合
     */
    List<PeopleCollection> peopleCollectionList(PeopleCollection peopleCollection);

    /**
     * 批量取消收藏实有人口
     * @param peopleCollection 参数对象
     */
    void batchCancelCollect(PeopleCollection peopleCollection);

    /**
     * 根据用户编号和人口编号查询唯一对象
     * @param peopleCollection 参数对象
     * @return 返回收藏对象
     */
    PeopleCollection selectByUserIdAndPeopleId(PeopleCollection peopleCollection);

}
