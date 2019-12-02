package com.ss.isc.data.baseinfo.mapper;

import com.ss.isc.data.baseinfo.common.model.UserAuthority;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthorityMapper {
  String findAuthority(@Param("url") String paramString1, @Param("userId") String paramString2);
  
  List<UserAuthority> findUserAuthority();
}
