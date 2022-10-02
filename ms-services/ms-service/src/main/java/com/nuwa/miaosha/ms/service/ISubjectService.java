package com.nuwa.miaosha.ms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nuwa.miaosha.ms.entity.Subject;

/**
 * <p>
 * 主题活动表 服务类
 * </p>
 *
 * @author jeffrey
 * @since 2022-09-05
 */
public interface ISubjectService extends IService<Subject> {
    /**
     * 根据id查询主题活动 带缓存
     * @param id
     * @return
     */
    Subject getCacheById(Long id);

}
