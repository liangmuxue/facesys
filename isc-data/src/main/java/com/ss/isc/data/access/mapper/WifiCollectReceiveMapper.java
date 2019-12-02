package com.ss.isc.data.access.mapper;

import com.ss.isc.data.access.common.web.WifiCollectReceive;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WifiCollectReceiveMapper {

    int batchInsert(List<WifiCollectReceive> paramList);

}
