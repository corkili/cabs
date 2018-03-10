package org.neu.cabs.dao;

import org.neu.cabs.orm.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单数据持久化接口
 * @author 李浩然
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
@Transactional(rollbackFor = Exception.class)
public interface OrderRepository extends JpaRepository<Order, Long> {
}
