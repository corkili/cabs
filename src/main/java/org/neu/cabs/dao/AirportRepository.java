package org.neu.cabs.dao;

import org.neu.cabs.orm.Address;
import org.neu.cabs.orm.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 机场数据持久化接口
 * @author 李浩然
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface AirportRepository extends JpaRepository<Airport, Integer> {

    /**
     * 根据地址获取机场
     * @param address 机场地址
     * @return 机场列表
     */
    List<Airport> findAllByAddress(Address address);

    /**
     * 根据地址，分页获取机场
     * @param address 机场地址
     * @param pageable 分页信息
     * @return 机场列表
     */
    Page<Airport> findAllByAddress(Address address, Pageable pageable);



}
