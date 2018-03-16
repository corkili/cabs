package org.neu.cabs.dao;

import org.neu.cabs.orm.AirlineCompany;
import org.neu.cabs.orm.Airplane;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 飞机数据持久化接口
 * @author 李浩然
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
    /**
     * 查询某航空公司的所有飞机
     * @param airlineCompany 航空公司
     * @return 飞机列表
     */
    List<Airplane> findAllByBelongToCompany(AirlineCompany airlineCompany);
    /**
     * 分页查询某航空公司的飞机
     * @param airlineCompany 航空公司
     * @param pageable 分页列表
     * @return 分页飞机信息
     */
    Page<Airplane> findAllByBelongToCompany(AirlineCompany airlineCompany, Pageable pageable);
}
