package com.proaim.jpa.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tb_demo")
public class Demo implements Serializable {

    private static final long serialVersionUID = 4266231446431906500L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    @Transient
    private List list;
}
