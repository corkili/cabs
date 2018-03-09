package org.neu.cabs.dao;

import org.neu.cabs.orm.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单数据持久化接口
 * @author 李浩然
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
