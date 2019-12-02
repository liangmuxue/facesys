package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.model.LongtimeStayPerson;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* 长时间逗留数据操作
* @author chao
* @create 2019/9/11
* @email lishuangchao@ss-cas.com
**/
@Repository
@Mapper
public interface LongtimeStayPersonMapper extends SsMapper<LongtimeStayPerson> {

  Integer update(LongtimeStayPerson paramLongtimeStayPerson);

  LongtimeStayPerson findLongtimeStayPersonResult(@Param("id") String id);

}
