package cn.csxy.mybatisplus.crud;

import cn.csxy.mybatisplus.crud.domain.Employee;
import cn.csxy.mybatisplus.crud.mapper.EmployeeMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class CrudApplicationTests {
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 查询id=1
     */
    @Test
    void test1() {
        //根据id查询
        Employee employee = employeeMapper.selectById(2);
        System.out.println(employee);
        // 分页查询,无任何条件
        Page<Employee> p = new Page<>(1, 2);
        employeeMapper.selectPage(p, null);

        // 分页按照 查询名字是赵总,age=35的记录
        Page<Employee> employeePage = employeeMapper.selectPage(p, new QueryWrapper<Employee>().eq("name", "赵总").eq("age", 35));
    }


    @Test
    void test2() {
        // update 语句的不使用某个对象来传入想要改变的值
        // 将name等于admin的记录年龄修改为28
        UpdateWrapper<Employee> uwr = new UpdateWrapper<>();
        uwr.eq("name", "admin");
        uwr.set("age", 28);
        employeeMapper.update(null, uwr);
    }

    @Test
    void testQery() {
        //查询年龄=25的员工信息
        QueryWrapper<Employee> qwr = new QueryWrapper<>();
        qwr.eq("age", 25);
        // 将结果返回一个list<T>类型
        List<Employee> employees = employeeMapper.selectList(qwr);
        System.out.println(employees);

        // 将查询结果返回一个List<Map>类型
        List<Map<String, Object>> maps = employeeMapper.selectMaps(qwr);
        for (Map<String, Object> map : maps) {
            Object name = map.get("name");
            System.out.println(name);
        }
    }

    @Test
    void testLambdaUpdate() {
        //需求：修改name=赵一鸣的 age=19
        LambdaUpdateWrapper<Employee> lwr = new LambdaUpdateWrapper<>();
        lwr.eq(Employee::getName, "赵一明");
        lwr.set(Employee::getT_age, 19);
        employeeMapper.update(null, lwr);
    }

    @Test
    void testLambdaSelect() {
        //需求：查询名字为赵一明并且年龄为19
        LambdaQueryWrapper<Employee> lwr = new LambdaQueryWrapper<>();
        //lwr.eq(Employee::getName,"赵一明").eq(Employee::getAge,19);
        lwr.eq(Employee::getName, "赵一明").eq(Employee::getT_age, 19);
        employeeMapper.selectList(lwr);
    }


    //    获得Wrapper对象的方式
    public void testWrapper() {

        UpdateWrapper<Object> objectUpdateWrapper = new UpdateWrapper<>();
        QueryWrapper<Object> objectQueryWrapper = new QueryWrapper<>();

        // 使用工具类
        UpdateWrapper<Object> update = Wrappers.update();
        QueryWrapper<Object> query = Wrappers.query();

        // 使用updateWrapper  和queryWrapper 对象来获取对应的lambda对象
        LambdaUpdateWrapper<Object> lambda = objectUpdateWrapper.lambda();
        LambdaQueryWrapper<Object> lambda1 = objectQueryWrapper.lambda();

        LambdaUpdateWrapper<Object> objectLambdaUpdateWrapper = Wrappers.lambdaUpdate();
        LambdaQueryWrapper<Object> objectLambdaQueryWrapper = Wrappers.lambdaQuery();

    }


    //条件构造器：allEq()
    @Test
    public void testAllEq() {
        // 需求：查询名称是 赵一明 部门id =2
        UpdateWrapper<Employee> objectUpdateWrapper = new UpdateWrapper<>();
        Map maps = new HashMap();
        maps.put("name", "赵一明");
        maps.put("dept_id", 2);
        maps.put("password", null);
//        objectUpdateWrapper.allEq(maps);
//        List<Employee> employees = employeeMapper.selectList(objectUpdateWrapper);
//        System.out.println(employees);

        // 直接通过selectByMap
        employeeMapper.selectByMap(maps);

        // null2IsNull参数
        objectUpdateWrapper.allEq(maps, false);
        List<Employee> employees = employeeMapper.selectList(objectUpdateWrapper);
        System.out.println(employees);
    }


    @Test
    public void testSelect() {
        // 需求：查询名称 年龄  邮件 所有的员工的信息，
        QueryWrapper<Employee> objectQueryWrapper = new QueryWrapper<>();
        // 方式1
        // objectQueryWrapper.select("name","age","email");
        // 方式2
        objectQueryWrapper.select("name t_name,age t_age,email t_email");
        List<Employee> employees = employeeMapper.selectList(objectQueryWrapper);
        System.out.println(employees);
    }


    @Test
    public void testOrderBy() {
        // 查询 所有员工信息  按照年龄 升序/降序 排列
        //  List<Employee> employees = employeeMapper.selectList(new QueryWrapper<Employee>().orderByAsc("age"));
        List<Employee> employees = employeeMapper.selectList(new QueryWrapper<Employee>().orderByDesc("age"));
        System.out.println(employees);
    }

    @Test
    public void testNe() {
        // 查询 年龄不等于 25岁的员工信息
        QueryWrapper<Employee> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.ne("age", 25);
        List<Employee> employees = employeeMapper.selectList(objectQueryWrapper);
        System.out.println(employees);
    }


    @Test
    public void testBetween() {
        // 查询 年龄 18-25岁之间的员工信息
        QueryWrapper<Employee> objectQueryWrapper = new QueryWrapper<>();
        //  objectQueryWrapper.between("age",18,19);
        objectQueryWrapper.notBetween("age", 18, 19);
        // 查询年龄 不在18-19之间的员工信息
        List<Employee> employees = employeeMapper.selectList(objectQueryWrapper);
        System.out.println(employees);
    }

    @Test
    public void testIsNUll() {
        // 查询 部门 id = null 的员工信息
        QueryWrapper<Employee> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.isNull("dept_id");
        List<Employee> employees = employeeMapper.selectList(objectQueryWrapper);
        System.out.println(employees);
    }


    @Test
    public void testIn() {
        // 查询 部门 age 18 19 28的员工信息
        QueryWrapper<Employee> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.in("age", "18", "19", "22");
        List<Employee> employees = employeeMapper.selectList(objectQueryWrapper);
        System.out.println(employees);
    }


    @Test
    public void testInSql() {
        // sql片段
        QueryWrapper<Employee> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.inSql("age", "select age from t_employee where dept_id = 5");
        List<Employee> employees = employeeMapper.selectList(objectQueryWrapper);
        System.out.println(employees);
    }

    @Test
    public void testGroupBy() {
        // 需求：按照部门对员工进行分组，并且查询出每个部门员工的个数，最终筛选部门人数多余3人的部门
        // sql select dept_id,count(dept_id)  from  t_employee group by dept_id having count(id) >3
        QueryWrapper<Employee> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.select("dept_id,count(id)");
        objectQueryWrapper.groupBy("dept_id");
        objectQueryWrapper.having("count(id)>{0}", 3);
        List<Employee> employees = employeeMapper.selectList(objectQueryWrapper);
        System.out.println(employees);
    }


    @Test
    public void testLike() {
        //模糊查询 名字中有张的
        QueryWrapper<Employee> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.like("name","李");  // like 默认添加%val%
        List<Employee> employees = employeeMapper.selectList(objectQueryWrapper);
        System.out.println(employees);

    }


    @Test
    public void testAndOr() {
        //需求:查询名字中有'a' 或者邮件中'a' 并且部门id=2
        // 对应的sql select  name , email,dept_id from  t_employee  where (name like '%a%' or email like '%a%') and dept_id =2;
        //
        //# mysql 中 and 的优先级高于or 所以，以上的sql语句执行的顺序是 email like '%a%' and dept_id =2; 在是  name like
        //# '%a%'，所以避免这个错误加上()
        QueryWrapper<Employee> qr = new QueryWrapper<>();
        qr.and(i->i.like("name","a").or().like("email","a")).eq("dept_id","2");
        List<Employee> employees = employeeMapper.selectList(qr);
        System.out.println(employees);
    }


    @Test
    public void testEmployee() {
    // 查询所有的员工信息以及部门信息
        List<Employee> employees = employeeMapper.selectEmployeeList();
    }

}