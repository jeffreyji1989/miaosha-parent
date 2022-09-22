package com.nuwa.miaosha.ms.facade;

import com.nuwa.miaosha.ms.resp.MsResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 秒杀服务
 */
@FeignClient(name = "ms-service", path = "ms")
public interface MsFacadeService {
    /**
     * 根据主题id查询商品列表
     *
     * @param subjectId
     */
    @PostMapping("list/{subjectId}")
     List<MsResp> listBySubjectId(@PathVariable("subjectId") Long subjectId);

    /**
     * 根据秒杀id 秒杀
     * @param id
     */
    void kill(Long id);
}
