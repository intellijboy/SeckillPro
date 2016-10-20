package com.jxufe.dao;

import com.jxufe.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by liuburu on 2016/10/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void reduceNumber() throws Exception {
        int seckillId = 2;
        Date nowTime = new Date();
        seckillDao.reduceNumber(seckillId);
    }

    @Test
    public void queryById() throws Exception {
        Seckill seckill = seckillDao.queryById(2);
        System.out.println(seckill);
    }

    @Test
    public void queryAll() throws Exception {
       List<Seckill> seckills =  seckillDao.queryAll(0,5);
        for(Seckill seckill:seckills){
            System.out.println(seckill);
        }
    }

}