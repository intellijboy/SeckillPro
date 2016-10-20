package com.jxufe.service;

import com.jxufe.dto.Exposer;
import com.jxufe.dto.SeckillExecution;
import com.jxufe.entity.Seckill;
import com.jxufe.exception.RepeatKillException;
import com.jxufe.exception.SeckillException;
import com.jxufe.exception.TimeOverException;

import java.util.List;
/**
 * 秒杀业务接口
 * Created by liuburu on 2016/10/16.
 */
public interface SeckillService {

    /**
     * 查看所有的秒杀商品记录
     * @return
     */
    public List<Seckill> getSeckillList();


    /**
     * 查看单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(int seckillId);


    /**
     *暴露商品秒杀地址
     * @param seckillId
     * @return 返回暴露商品秒杀地址相关信息
     */
    Exposer exportSeckillUrl(int seckillId) throws TimeOverException,SeckillException;


    /**
     * 执行秒杀
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws TimeOverException
     * @throws RepeatKillException
     * @throws SeckillException
     */
    SeckillExecution executeSeckill(int seckillId, long userPhone, String md5)
            throws TimeOverException, RepeatKillException, SeckillException;
}
