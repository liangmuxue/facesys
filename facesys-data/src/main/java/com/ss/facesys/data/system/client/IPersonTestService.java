package com.ss.facesys.data.system.client;

import com.ss.facesys.data.system.common.model.PersonTest;
import java.util.List;

public interface IPersonTestService {
  void insert(PersonTest paramPersonTest);
  
  List<PersonTest> findAll();
}
