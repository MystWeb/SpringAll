package com.proaim.jpa.repository;

import com.proaim.jpa.entity.Demo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoDaoTest {
    @Autowired
    private DemoDao demoDao;

    //查询所有
    @Test
    public void testFindAll() {
        List<Demo> list = demoDao.findAll();
        list.forEach(demo -> {
            log.info("demo={}", demo);
        });
    }

    //根据ID查询
    @Test
    public void testFindById() {
//        Demo demo = demoDao.getOne(1L); //Error: org.hibernate.LazyInitializationException - no Session
        Demo demo = demoDao.findById(1L).get();
        log.info("demo={}", demo);
    }

    //动态查询。根据某个字段查询
    @Test
    public void testFindByExample() {
        Demo demo = new Demo();
        Example<Demo> example = Example.of(demo);
        demo.setName("ProAIM");
        List<Demo> list = demoDao.findAll(example);
        list.forEach(d -> {
            log.info("demo={}", d);
        });
    }

    @Test
    public void testSave() {
        Demo demo = new Demo();
        demo.setName("测试");
        demo.setCreateTime(new Date());
        demoDao.save(demo);
        List<Demo> list = demoDao.findAll();
        list.forEach(d -> {
            log.info("demo={}", d);
        });
    }

    @Test
    public void testUpdate() {
        Demo demo = new Demo();
        demo.setId(2L);
        demo.setName("ProAIM");
        demoDao.save(demo);
        log.info("demo={}", demoDao.findById(demo.getId()).get());
    }

    @Test
    public void testDelete() {
        Demo demo = new Demo();
        demo.setId(2L);
        demoDao.delete(demo);
//        demoDao.deleteById(1L);
    }

    @Test
    public void testFindByPage() {
        int pageCode = 1; //当前页
        int pageSize = 3; //每页显示10条记录
//        Sort sort = new Sort(Sort.Direction.ASC, "id");
//        Pageable pageable = new PageRequest(pageCode, pageSize, sort);
        PageRequest pageable = PageRequest.of(pageCode, pageSize);
        Page<Demo> page = demoDao.findAll(pageable);
        log.info("总记录数={}", page.getTotalElements());
        log.info("总页数={}", page.getTotalPages());
        log.info("记录={}", page.getContent());
    }
}
