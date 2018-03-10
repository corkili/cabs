package org.neu.cabs.dao;

import org.neu.cabs.orm.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 订单数据持久化接口
 * @author 李浩然
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
@Transactional(rollbackFor = Exception.class)
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCreateTimeBetween(Date start, Date end);

    List<Order> findAllByCreateTimeBetween(Date start, Date end, Pageable pageable);

}
