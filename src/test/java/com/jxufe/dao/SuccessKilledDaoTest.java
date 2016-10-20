package com.jxufe.dao;

import com.jxufe.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by liuburu on 2016/10/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {
    @Autowired
    private SuccessKilledDao successKilledDao ;
    @Test
    public void insertSuccessKilled() throws Exception {
        int seckillId = 2;
        long userPhone = 13870777375L;
        int row = successKilledDao.insertSuccessKilled(seckillId,userPhone);
        System.out.println("row=" + row);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        int seckillId = 2;
        long userPhone = 15270998540L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId,userPhone);
        System.out.println(successKilled);
    }

}