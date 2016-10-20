package com.jxufe.dto;

import java.util.Date;

/**
 * 接口秒杀相关的数据封装
 */
public class Exposer{
    private boolean exposed;
    private String md5;//加密字符串
    private int seckillId;//秒杀产品编号
    private Date now;//当前秒杀时间
    private Date start;//秒杀开启时间
    private Date end;//秒杀结束时间

    public Exposer() {
    }


    public Exposer(boolean exposed, String md5, int seckillId, Date now, Date start, Date end) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean exposed, Date now, Date start, Date end) {
        this.exposed = exposed;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(int seckillId) {
        this.seckillId = seckillId;
    }

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
