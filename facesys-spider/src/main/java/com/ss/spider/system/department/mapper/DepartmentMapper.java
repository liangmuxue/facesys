package com.ss.spider.system.department.mapper;

import com.ss.mapper.SsMapper;
import com.ss.spider.system.department.model.Department;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface DepartmentMapper extends SsMapper<Department> {

    List<Department> gets(Map<String, Object> paramMap);

    List<Department> pages(Department paramDepartment);

    List<Department> list(Department paramDepartment);

    int save(Department paramDepartment);

    int update(Department paramDepartment);

    int remove(Map<String, Object> paramMap);

    int discard(Map<String, Object> paramMap);

}
