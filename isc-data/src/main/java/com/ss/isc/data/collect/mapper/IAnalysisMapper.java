package com.ss.isc.data.collect.mapper;

import com.ss.isc.data.collect.common.dto.FrequencyNightDTO;
import com.ss.isc.data.collect.common.dto.LongtimeStayDTO;
import com.ss.isc.data.collect.common.dto.SensitiveTrafficDTO;
import com.ss.isc.data.collect.common.web.FrequencyNightQuery;
import com.ss.isc.data.collect.common.web.LongtimeStayQuery;
import com.ss.isc.data.collect.common.web.SensitiveTrafficQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Mapper
public interface IAnalysisMapper {

  List<FrequencyNightDTO> frequencyNightPage(FrequencyNightQuery paramFrequencyNightQuery);

  List<LongtimeStayDTO> longtimeStayPage(LongtimeStayQuery paramLongtimeStayQuery);

  List<SensitiveTrafficDTO> sensitiveTrafficPage(SensitiveTrafficQuery paramLongtimeStayQuery);

}
