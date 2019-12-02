package com.ss.facesys.data.collect.client;

import com.ss.facesys.data.collect.common.model.AddPersonCollect;
import java.util.List;

public interface IAddPersonCollectService {
  List<AddPersonCollect> queryCollectList(List<String> paramList);
}
