package org.neu.cabs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 机场管理控制器
 * @author 李浩然
 */
@Controller
@Transactional(rollbackFor = Exception.class)
@RequestMapping(value = { "/manage/airport" })
public class AirportManageController {

    @RequestMapping(value = { "", "/", "/index" }, method = { RequestMethod.GET, RequestMethod.POST })
    public String index() {
        return "manage/airport_manage";
    }
}
