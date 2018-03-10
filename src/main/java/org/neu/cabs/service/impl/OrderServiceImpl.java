package org.neu.cabs.service.impl;

import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.Order;
import org.neu.cabs.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

/**
 * 系统核心逻辑，订单服务接口实现类
 * @author 李浩然
 * @see org.neu.cabs.service.OrderService
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {
    @Override
    public ServiceResult createOrder(Order order) {
        return null;
    }

    @Override
    public ServiceResult payOrder(Long orderId) {
        return null;
    }

    @Override
    public Order getOrderById(Long orderId) {
        return null;
    }

    @Override
    public ServiceResult endorseOrder(Order order) {
        return null;
    }

    @Override
    public ServiceResult refundOrder(Long orderId) {
        return null;
    }

    @Override
    public List<Order> getOrderByDateRange(Date start, Date end) {
        return null;
    }

    @Override
    public List<Order> getOrderByDateRange(Date start, Date end, Pageable pageable) {
        return null;
    }

    @Override
    public ServiceResult modifyOrder(Order order) {
        return null;
    }
}
