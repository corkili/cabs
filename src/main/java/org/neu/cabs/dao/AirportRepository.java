package org.neu.cabs.dao;

import org.neu.cabs.orm.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 机场数据持久化接口
 * @author 李浩然
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface AirportRepository extends JpaRepository<Airport, Long> {
}
