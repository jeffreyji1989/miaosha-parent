package com.nuwa.miaosha.ms.controller;


import com.nuwa.miaosha.common.util.bean.BeanUtils;
import com.nuwa.miaosha.ms.entity.Ms;
import com.nuwa.miaosha.ms.facade.MsFacadeService;
import com.nuwa.miaosha.ms.resp.MsResp;
import com.nuwa.miaosha.ms.service.IMsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 秒杀表 前端控制器
 * </p>
 *
 * @author jeffrey
 * @since 2022-09-05
 */
@RestController
@RequestMapping("/ms")
public class MsController implements MsFacadeService {

    @Autowired
    private IMsService msService;

    /**
     * 根据主题id查询商品列表
     *
     * @param subjectId
     */
    @Override
    @PostMapping("list/{subjectId}")
    public List<MsResp> listBySubjectId(@PathVariable("subjectId") Long subjectId) {
        List<Ms> msList = msService.listBySubjectId(subjectId);
        if (null != msList && !msList.isEmpty()) {
            return BeanUtils.conversionList(msList, MsResp.class);
        }
        return null;
    }

    /**
     * 根据秒杀id 秒杀
     *
     * @param id
     */
    @Override
    public void kill(Long id) {
        msService.msKill(id);
    }
}
