package com.proaimltd.kafka.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: ziming.xing
 * @date: 2019/8/26 20:55
 */
@Data
public class Programmer implements Serializable {
    private static final long serialVersionUID = -5264195053015972900L;

    private String name;
    private int age;
    private float money;
    private Date birthday;

    public Programmer(String name, int age, float money, Date birthday) {
        this.name = name;
        this.age = age;
        this.money = money;
        this.birthday = birthday;
    }
}
