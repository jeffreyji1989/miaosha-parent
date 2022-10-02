package com.nuwa.miaosha.ms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuwa.miaosha.ms.cache.MsCache;
import com.nuwa.miaosha.ms.entity.Subject;
import com.nuwa.miaosha.ms.mapper.SubjectMapper;
import com.nuwa.miaosha.ms.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 主题活动表 服务实现类
 * </p>
 *
 * @author jeffrey
 * @since 2022-09-05
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements ISubjectService {
    @Autowired
    private MsCache msCache;

    /**
     * 根据id查询主题活动 带缓存
     *
     * @param id
     * @return
     */
    @Override
    public Subject getCacheById(Long id) {
        Subject subject = msCache.getSubject(id);
        if (null == subject) {
            subject = getById(id);
            msCache.saveSubject(subject);
        }
        return subject;
    }
}
