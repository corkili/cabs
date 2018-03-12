package org.neu.cabs.service.impl;

import org.neu.cabs.dao.OrderItemRepository;
import org.neu.cabs.orm.OrderItem;
import org.neu.cabs.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统核心逻辑，订单项（机票）服务接口实现类
 * @author 李浩然
 * @see org.neu.cabs.service.OrderItemService
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> searchOrderItemsByPassengerCertificateNumber(String passengerCertificateNumber) {
        return orderItemRepository.findOrderItemsByPassengerCertificateNumber(passengerCertificateNumber);
    }
}
