package org.seckill.dao;

import org.apache.ibatis.annotations.*;
import org.seckill.entity.*;

/**
 * Created by pc on 2017/3/5.
 */
public interface SuccessKilledDao {
    /**
     * 插入购买明细，可过滤重复
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);

    /**
     * 根据id查询SuccessKilled，并携带秒杀产品对象实体
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);
}
