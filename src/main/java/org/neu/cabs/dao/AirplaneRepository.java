package org.neu.cabs.dao;

import org.neu.cabs.orm.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 飞机数据持久化接口
 * @author 李浩然
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
}
