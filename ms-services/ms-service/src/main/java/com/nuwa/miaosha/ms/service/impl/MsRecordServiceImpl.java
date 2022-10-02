package com.nuwa.miaosha.ms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuwa.miaosha.ms.entity.MsRecord;
import com.nuwa.miaosha.ms.mapper.MsRecordMapper;
import com.nuwa.miaosha.ms.service.IMsRecordService;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 秒杀表 服务实现类
 * </p>
 *
 * @author jeffrey
 * @since 2022-09-05
 */
@Service
public class MsRecordServiceImpl extends ServiceImpl<MsRecordMapper, MsRecord> implements IMsRecordService {
}
