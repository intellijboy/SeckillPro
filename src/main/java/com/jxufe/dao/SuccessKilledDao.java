package com.jxufe.dao;

import com.jxufe.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by moonie on 16/5/26.
 */
public interface SuccessKilledDao {

    /**
     * 插入秒杀记录
     * @param seckillId 秒杀商品编号
     * @param userPhone 秒杀用户电话
     * @return
     */
    int insertSuccessKilled(@Param("seckillId") int seckillId, @Param("userPhone") long userPhone);

    /**
     * 查看订单详情，同时订单详情中涵盖了秒杀单的信息
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") int seckillId, @Param("userPhone") long userPhone);

}
