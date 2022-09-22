package com.nuwa.miaosha.ms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nuwa.miaosha.ms.entity.Ms;

import java.util.List;

/**
 * <p>
 * 秒杀表 服务类
 * </p>
 *
 * @author jeffrey
 * @since 2022-09-05
 */
public interface IMsService extends IService<Ms> {
    /**
     * 根据主题id查询秒杀商品列表
     * @param subjectId
     * @return
     */
    List<Ms> listBySubjectId(Long subjectId);
    /**
     * 秒杀接口
     * 1. 校验活动是否在有效期内
     * 2. 校验商品是否在有效期内
     * 3. 校验商品是否还有库存
     * 4. 校验用户token是否在有效期内
     * 4.1 校验是否已经秒杀过该产品
     * 5. 生成订单
     * 6. 扣减库存
     * 7. 增加
     * @param id
     */
    void msKill(Long id);

}
