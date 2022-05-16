package cn.csxy.mybatisplus.crud.service;

import cn.csxy.mybatisplus.crud.domain.Employee;
import cn.csxy.mybatisplus.crud.mapper.EmployeeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements ImEmployeeService{
  @Autowired
   private EmployeeMapper employeeMapper;
    @Override
    public List<Employee> getEmployeeAndDepartment() {

        return employeeMapper.selectEmployeeList();
    }
}
