package org.neu.cabs.controller;

import org.neu.cabs.dto.OrderForm;
import org.neu.cabs.dto.ResponseResult;
import org.neu.cabs.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 订单管理控制器
 * @author 李浩然
 */
@Controller
@RequestMapping(value = { "/manage/order" })
public class OrderManageController {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = { "", "/", "/index" }, method = { RequestMethod.GET, RequestMethod.POST })
    public String index() {
        return "manage/order_manage";
    }

    @RequestMapping(value = { "/{orderId}" }, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public OrderForm getOrderInfo(@PathVariable("orderId") long orderId) {
        return OrderForm.from(orderService.getOrderById(orderId));
    }
}
