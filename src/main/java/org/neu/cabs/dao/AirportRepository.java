package org.neu.cabs.dao;

import org.neu.cabs.orm.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 机场数据持久化接口
 * @author 李浩然
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
@Transactional(rollbackFor = Exception.class)
public interface AirportRepository extends JpaRepository<Airport, Long> {
}
