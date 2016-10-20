package com.jxufe.service;

import com.alibaba.fastjson.JSON;
import com.jxufe.dao.SeckillDao;
import com.jxufe.dto.Exposer;
import com.jxufe.dto.SeckillExecution;
import com.jxufe.entity.Seckill;
import com.jxufe.exception.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;


/**
 * Created by liuburu on 2016/10/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private Logger logger  = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SeckillService seckillService;
    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> seckillList = seckillService.getSeckillList();
        for (Seckill seckill:seckillList){
            System.out.println(seckill);
        }
    }
    @Test
    public void getById() throws Exception {
        int seckillId = 2;
        Seckill seckill = seckillService.getById(seckillId);
        System.out.println(seckill);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        try {
            Exposer exposer = seckillService.exportSeckillUrl(3);
            System.out.println("商品地址:"+JSON.toJSONString(exposer));
        }catch (TimeOverException e) {
            logger.error(e.getMessage(),e);
        }catch (SeckillException e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Test
    public void executeSeckill() throws Exception {
        try {
            int seckillId = 2;
            Long userPhone = 12345673333L;
            String md5 = "191c86852a7abee5d9438fd78873bfaf";
            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
            System.out.println(JSON.toJSONString(JSON.toJSONString(seckillExecution)));
        } catch (StoreEmptyException e) {
            logger.error(e.getMessage(),e);
        }catch (RepeatKillException e) {
            logger.error(e.getMessage(),e);
        }catch (TimeOverException e) {
            logger.error(e.getMessage(),e);
        }catch (SeckillException e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Test
    public void testDate(){

        Seckill seckill = seckillDao.queryById(2);
        Date date  = new Date();
        System.out.println(date.after(seckill.getEndTime()));
    }

}