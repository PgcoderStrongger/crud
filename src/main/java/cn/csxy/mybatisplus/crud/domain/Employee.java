package cn.csxy.mybatisplus.crud.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_employee")
public class Employee {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String password;
    private String name;
    private String email;
    @TableField("age")
    private Integer t_age;
    private boolean  admin;
    @TableField(exist = false)
    private Department dept;

}
