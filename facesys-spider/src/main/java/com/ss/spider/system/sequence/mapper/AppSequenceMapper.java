package com.ss.spider.system.sequence.mapper;

import com.ss.mapper.SsMapper;
import com.ss.spider.system.sequence.model.AppSequence;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AppSequenceMapper extends SsMapper<AppSequence> {

    AppSequence get(String paramString);

    int nextVal(AppSequence paramAppSequence);

    int save(AppSequence paramAppSequence);

    int update(AppSequence paramAppSequence);

    int remove(String paramString);

}
