package com.proaim.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

// 使用JPA注解配置映射关系
@Data
@Entity // 告诉JPA这是一个实体类（数据表映射的类）
@Table(name = "tb_employee") // @Table来指定和哪个数据表对应；如果省略默认表名就是以类名填充 user
public class Employee implements Serializable {

    private static final long serialVersionUID = 794476331762551987L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// 自增主键
    private Integer id;
    @Column(name = "last_name", length = 50) // 这是和数据表对应的一个列
    private String lastName;
    @Column // 省略，默认列明就是属性名
    private String email;
}
