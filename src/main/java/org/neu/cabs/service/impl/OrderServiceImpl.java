package org.neu.cabs.service.impl;

import org.neu.cabs.dao.OrderRepository;
import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.Order;
import org.neu.cabs.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;


import static org.neu.cabs.constant.OrderState.ALREADY_PAID;
import static org.neu.cabs.constant.OrderState.CANCELLED;
import static org.neu.cabs.constant.OrderState.UNPAID;

/**
 * 系统核心逻辑，订单服务接口实现类
 * @author 李浩然 谭湖东
 * @see org.neu.cabs.service.OrderService
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public ServiceResult createOrder(Order order) {
        ServiceResult serviceResult;

        // 在数据库查找是否已经存在该订单
        Order orderT = orderRepository.findOne(order.getId());
        if (null==orderT){
            // 不存在
            orderRepository.save(order);
            serviceResult  = new ServiceResult(true,"创建订单成功！");
        }else{ //存在
            serviceResult = new ServiceResult(false,"该订单已经存在！");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult payOrder(Long orderId) {
        ServiceResult serviceResult;
        // 获取整个订单
        Order order = orderRepository.findOne(orderId);
        // 通过订单状态，来判断是否能够支付
        if(order.getState()==UNPAID){
            // 将状态改成已支付，表示支付成功
            order.setState(ALREADY_PAID);
            serviceResult = new ServiceResult(true,"支付成功！");
        }else{
            serviceResult = new ServiceResult(false,"支付失败！");
        }
        return serviceResult;
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findOne(orderId);
    }

    @Override
    public ServiceResult endorseOrder(Order order) {
        ServiceResult serviceResult;
        // 修改后的订单
        Order orderT = orderRepository.save(order);
        if (null!=orderT){
            serviceResult = new ServiceResult(true,"改签成功！");
        }else{
            serviceResult = new ServiceResult(false,"改签失败！");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult refundOrder(Long orderId) {
        ServiceResult serviceResult;
        // 获取订单
        Order order = orderRepository.findOne(orderId);
        // 通过订单的状态来判断是否可以退票
        if (order.getState()!=CANCELLED){
            // 原本状态不是已退票
            order.setState(CANCELLED);
            orderRepository.save(order);
            serviceResult = new ServiceResult(true,"退票成功！");
        }else {
            serviceResult = new ServiceResult(false,"退票失败!");
        }
        return serviceResult;
    }

    @Override
    public List<Order> getOrderByDateRange(Date start, Date end) {
        return orderRepository.findAllByCreateTimeBetween(start,end);
    }

    @Override
    public Page<Order> getOrderByDateRange(Date start, Date end, Pageable pageable) {

        return orderRepository.findAllByCreateTimeBetween(start,end,pageable);
    }

    @Override
    public ServiceResult modifyOrder(Order order) {
        ServiceResult serviceResult;
        Order orderT = orderRepository.save(order);
        if (null!=orderT){
            serviceResult = new ServiceResult(true,"订单修改成功！");
        }else{
            serviceResult = new ServiceResult(false,"订单修改失败！");
        }
        return serviceResult;
    }
}
