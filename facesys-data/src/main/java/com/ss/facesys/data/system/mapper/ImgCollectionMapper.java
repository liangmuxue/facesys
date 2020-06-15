package com.ss.facesys.data.system.mapper;

import com.ss.facesys.data.system.common.dto.ImgCollectionResultDTO;
import com.ss.facesys.data.system.common.model.ImgCollection;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImgCollectionMapper extends SsMapper<ImgCollection> {

    List<ImgCollectionResultDTO> pages(ImgCollection query);

}
