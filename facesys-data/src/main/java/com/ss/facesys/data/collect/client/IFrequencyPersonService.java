package com.ss.facesys.data.collect.client;

import com.ss.facesys.data.collect.common.model.FrequencyPerson;
import java.util.List;

public interface IFrequencyPersonService {
  int batchCompareFrequencyPerson(List<FrequencyPerson> paramList);
  
  int updateFrequencyPerson(FrequencyPerson paramFrequencyPerson);
}
