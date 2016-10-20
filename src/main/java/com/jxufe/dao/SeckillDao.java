package com.jxufe.dao;

import com.jxufe.entity.Seckill;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Date;

/**
 * Created by moonie on 16/5/26.
 */
public interface SeckillDao {

    /**
     * 减库存
     * @param seckillId 商品编号
     * @return
     */
    int reduceNumber(@Param("seckillId") int seckillId);

    /**
     * 根据ID查询秒杀对象
     * @param seckillId
     * @return
     */
    Seckill queryById(int seckillId);

    /**
     * 根据偏移量查询商品列表（可以用于数据分页显示）
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
