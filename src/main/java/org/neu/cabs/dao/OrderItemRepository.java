package org.neu.cabs.dao;

import org.neu.cabs.orm.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单项数据持久化接口
 * @author 李浩然
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    /**
     * 根据旅客的证件号查找订单项
     * @param passengerCertificateNumber 旅客证件号
     * @return 订单项列表
     */
    List<OrderItem> findOrderItemsByPassengerCertificateNumber(String passengerCertificateNumber);
}
