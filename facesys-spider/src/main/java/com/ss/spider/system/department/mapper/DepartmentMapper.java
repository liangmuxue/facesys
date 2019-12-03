package com.ss.spider.system.department.mapper;

import com.ss.mapper.SsMapper;
import com.ss.spider.system.department.model.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * DepartmentMapper
 *
 * @author FrancisYs
 * @date 2019/12/3
 * @email yaoshuai@ss-cas.com
 */
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
