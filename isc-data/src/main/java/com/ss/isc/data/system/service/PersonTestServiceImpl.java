package com.ss.isc.data.system.service;

import com.ss.isc.data.system.client.IPersonTestService;
import com.ss.isc.data.system.common.model.PersonTest;
import com.ss.isc.data.system.mapper.PersonTestDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

















@Service
public class PersonTestServiceImpl
  implements IPersonTestService
{
  @Autowired
  private PersonTestDao personDao;
  
  public void insert(PersonTest person) {}
  
  public List<PersonTest> findAll() { return null; }
}
