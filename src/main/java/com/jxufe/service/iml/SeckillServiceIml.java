package com.jxufe.service.iml;


import com.jxufe.dao.SeckillDao;
import com.jxufe.dao.SuccessKilledDao;
import com.jxufe.dto.Exposer;
import com.jxufe.dto.SeckillExecution;
import com.jxufe.entity.Seckill;
import com.jxufe.entity.SuccessKilled;
import com.jxufe.enums.SeckillStatEnum;
import com.jxufe.exception.*;
import com.jxufe.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * 业务层应该是要去处理所有的业务逻辑，而不是单单的
 * 处理一个业务逻辑，需要考虑所有的条件因素。
 * 总之：业务层时用来处理业务逻辑的，
 * 输出：是抛出异常，
 * 输入：是各种形式的数据
 * Created by liuburu on 2016/10/16.
 */
@Service
public class SeckillServiceIml implements SeckillService{
    private String salt="afdsafd245245^*HKH";//盐值
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    public List<Seckill> getSeckillList() {
        List<Seckill> seckills = seckillDao.queryAll(0,10);
        return seckills;
    }

    public Seckill getById(int seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        return seckill;
    }

    public Exposer exportSeckillUrl(int seckillId)  {
        Seckill seckill = seckillDao.queryById(seckillId);
        if(seckill==null){
            return null;//表示获取的秒杀产品不存在
        }else{
            //还需要判断当前用户时间是否符合要求
            Date curTime = new Date();
            Date startTime = seckill.getStartTime();
            Date endTime = seckill.getEndTime();
            if(curTime.before(startTime)){//秒杀时间未开启
                return new Exposer(false,curTime,startTime,endTime);
            }else if(curTime.after(endTime)){//秒杀时间正确
               throw  new TimeOverException(SeckillStatEnum.TIME_OVER_EXCEPTION.getStateInfo());
            }else{
                String md5 = getMD5(seckillId);
                return new Exposer(true,md5,seckillId,curTime,startTime,endTime);
            }
        }
    }

    private String getMD5(int seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        System.out.println("");
        return md5;
    }

    @Transactional
    public SeckillExecution executeSeckill(int seckillId, long userPhone, String md5){
        //1.首先判断md5值是否合法
        if(!md5.equals(getMD5(seckillId))){
           throw new IlleagerUrlException(SeckillStatEnum.ILLEAGLE_EXCEPTION.getStateInfo());
        }
        //2.检查秒杀是否有存在重复秒杀，或者秒杀关闭现象(先减库存，再插入购买明细)
        Date nowDate = new Date();
        try {
            int insertRow = successKilledDao.insertSuccessKilled(seckillId,userPhone);
            if(insertRow>0){//插入成功
                int updateRow = seckillDao.reduceNumber(seckillId);
                Seckill seckill = seckillDao.queryById(seckillId);
                if(nowDate.after(seckill.getEndTime())){//1.秒杀库存为空  2.秒杀产品不存在
                    throw new TimeOverException(SeckillStatEnum.TIME_OVER_EXCEPTION.getStateInfo());
                }
                if(updateRow==0){
                    throw new StoreEmptyException(SeckillStatEnum.STORE_EMPTY_EXCEPTION.getStateInfo());
                }
                if(updateRow>0){//秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId,userPhone);
                    return new SeckillExecution(seckillId,SeckillStatEnum.SUCCESS_KILL,successKilled);
                }
            }else {//重复秒杀
                throw new RepeatKillException(SeckillStatEnum.REPEAT_EXCEPTION.getStateInfo());
            }
        } catch (RepeatKillException e1) {
            throw e1;
        }catch (TimeOverException e2) {
            throw  e2;
        }catch (StoreEmptyException e4) {
            throw  e4;
        }catch (SeckillException e5) {
            throw  e5;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            throw  new SeckillException(SeckillStatEnum.INNER_ERROR.getStateInfo());
        }
        return null;//没有查到任何数据
    }


}
