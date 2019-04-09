package com.proaim.jpa.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

// 使用JPA注解配置映射关系
@Data
@Entity // 告诉JPA这是一个实体类（数据表映射的类）
@Table(name = "tb_user") // @Table来指定和哪个数据表对应；如果省略默认表名就是以类名填充 user
public class User implements Serializable {
    private static final long serialVersionUID = 639651099622693345L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "text")
    private String description;
}
