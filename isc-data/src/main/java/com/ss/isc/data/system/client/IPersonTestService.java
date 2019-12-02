package com.ss.isc.data.system.client;

import com.ss.isc.data.system.common.model.PersonTest;
import java.util.List;

public interface IPersonTestService {
  void insert(PersonTest paramPersonTest);
  
  List<PersonTest> findAll();
}
