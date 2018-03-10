package org.neu.cabs.dao;

import org.neu.cabs.orm.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 航班数据持久化接口
 * @author 李浩然
 */
@Transactional(rollbackFor = Exception.class)
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
