package cn.csxy.mybatisplus.crud.mapper;

import cn.csxy.mybatisplus.crud.domain.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmployeeMapper  extends BaseMapper<Employee> {
    @Select("select e.*,d.name d_name, d.sn d_sn  from  t_employee e left join department d  on e.dept_id=d.id")
    @Results({
            @Result(property ="dept.name",column = "d_name"),
            @Result(property = "dept.sn",column = "d_sn"),

    })
    List<Employee> selectEmployeeList();
}
