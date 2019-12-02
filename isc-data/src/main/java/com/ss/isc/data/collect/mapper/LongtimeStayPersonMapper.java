package com.ss.isc.data.collect.mapper;

import com.ss.isc.data.collect.common.model.LongtimeStayPerson;
import com.ss.isc.data.collect.common.model.SensitivePerson;
import com.ss.mapper.CWMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* 长时间逗留数据操作
* @author chao
* @create 2019/9/11
* @email lishuangchao@ss-cas.com
**/
@Repository
@Mapper
public interface LongtimeStayPersonMapper extends CWMapper<LongtimeStayPerson> {

  Integer update(LongtimeStayPerson paramLongtimeStayPerson);

  LongtimeStayPerson findLongtimeStayPersonResult(@Param("id") String id);

}
