package cn.csxy.mybatisplus.crud;

import cn.csxy.mybatisplus.crud.domain.Employee;
import cn.csxy.mybatisplus.crud.service.EmployeeServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EmployeeServiceTest {
    @Autowired
    private EmployeeServiceImpl employeeService;
     @Test
    void test1(){
         // 查询所有员工
         List<Employee> list = employeeService.list();
         System.out.println(list);
     }

     @Test
     void test2(){
         // 查询所有的年龄=25的员工
         List<Employee> employees = employeeService.list(new QueryWrapper<Employee>().eq("age", 25));
         System.out.println(employees);
     }

    @Test
    void test3(){
        // 查询所有的年龄=25的员工
        List<Employee> employeeAndDepartment = employeeService.getEmployeeAndDepartment();
        System.out.println(employeeAndDepartment);
    }

}
