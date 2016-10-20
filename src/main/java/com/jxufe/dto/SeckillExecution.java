package com.jxufe.dto;

import com.jxufe.entity.Seckill;
import com.jxufe.entity.SuccessKilled;
import com.jxufe.enums.SeckillStatEnum;

/**
 * Created by liuburu on 2016/10/16.
 */
public class SeckillExecution {
//    private int statusNum;//状态码
//    private String stateInfo;//状态信息
    private int seckillId;//秒杀产品ID
    private SeckillStatEnum seckillStatEnum;
    private SuccessKilled successKilled;

    //成功秒杀的数据封装构造

    public SeckillExecution(SeckillStatEnum seckillStatEnum) {
        this.seckillStatEnum = seckillStatEnum;
    }

    public SeckillExecution(int seckillId, SeckillStatEnum seckillStatEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.seckillStatEnum = seckillStatEnum;
        this.successKilled = successKilled;
    }

    //失败秒杀的数据封装构造

    public SeckillExecution(int seckillId, SeckillStatEnum seckillStatEnum) {
        this.seckillId = seckillId;
        this.seckillStatEnum = seckillStatEnum;
    }

    public int getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(int seckillId) {
        this.seckillId = seckillId;
    }

    public SeckillStatEnum getSeckillStatEnum() {
        return seckillStatEnum;
    }

    public void setSeckillStatEnum(SeckillStatEnum seckillStatEnum) {
        this.seckillStatEnum = seckillStatEnum;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }
}
