package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.model.*;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
* 敏感通行数据操作
* @author chao
* @create 2019/9/11
* @email lishuangchao@ss-cas.com
**/
@Repository
@Mapper
public interface SensitivePersonMapper extends SsMapper<SensitivePerson> {

  List<SensitivePerson> querySensitiveList();

  SensitivePerson findSensitivePerson(SensitivePerson param);

  int batchCompareSensitivePerson(List<SensitivePerson> paramList);

  int batchInsertSensitivePersonDetail(List<SensitivePersonDetail> paramList);

  List<String> querySensitivePersonDetCaptureIds(@Param("sensitivePersonId") String sensitivePersonId);

  Integer update(SensitivePerson paramSensitivePerson);

  SensitivePerson findSensitivePersonResult(@Param("id") String id);

}
