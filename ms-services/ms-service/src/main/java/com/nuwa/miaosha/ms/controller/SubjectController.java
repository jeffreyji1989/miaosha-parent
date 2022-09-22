package com.nuwa.miaosha.ms.controller;


import com.nuwa.miaosha.common.web.bean.LoginUser;
import com.nuwa.miaosha.common.web.context.UserContext;
import com.nuwa.miaosha.ms.entity.Subject;
import com.nuwa.miaosha.ms.service.ISubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 主题活动表 前端控制器
 * </p>
 *
 * @author jeffrey
 * @since 2022-09-05
 */
@RestController
@RequestMapping("/subject")
@Slf4j
public class SubjectController {
    @Autowired
    private ISubjectService subjectService;

    @PostMapping("listAll")
    public List<Subject> listAll() {
        LoginUser user = UserContext.getUser();
        log.info("当前用户:{}",user);
        return subjectService.list();
    }

}
