package cn.csxy.mybatisplus.crud.service;

import cn.csxy.mybatisplus.crud.domain.Employee;

import java.util.List;

public interface ImEmployeeService {
    //查询所有的员工的信息，包括部门信息
    List<Employee> getEmployeeAndDepartment();
}
