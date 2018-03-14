package org.neu.cabs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 航班管理控制器
 * @author 李浩然
 */
@Controller
@Transactional(rollbackFor = Exception.class)
@RequestMapping(value = { "/manage/flight" })
public class FlightManageController {

    @RequestMapping(value = { "", "/", "/index" }, method = { RequestMethod.GET, RequestMethod.POST })
    public String index() {
        return "manage/flight_manage";
    }
}
