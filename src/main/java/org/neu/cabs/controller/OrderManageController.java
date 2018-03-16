package org.neu.cabs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 订单管理控制器
 * @author 李浩然
 */
@Controller
@RequestMapping(value = { "/manage/order" })
public class OrderManageController {

    @RequestMapping(value = { "", "/", "/index" }, method = { RequestMethod.GET, RequestMethod.POST })
    public String index() {
        return "manage/order_manage";
    }
}
