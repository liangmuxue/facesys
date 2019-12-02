package com.ss.isc.data.collect.client;

import com.ss.isc.data.collect.common.model.AddPersonCollect;
import java.util.List;

public interface IAddPersonCollectService {
  List<AddPersonCollect> queryCollectList(List<String> paramList);
}
