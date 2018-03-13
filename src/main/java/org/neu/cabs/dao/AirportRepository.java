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
@Transactional(rollbackFor = Exception.class)
public interface AirportRepository extends JpaRepository<Airport, Long> {
    /**
     * 根据地址获取机场
     * @param province 机场所在省份
     * @param city 机场所在城市
     * @return 机场列表
     */
    List<Airport> findAllByAddress_ProvinceAndAddress_City(String province, String city);

    /**
     * 根据地址，分页获取机场
     * @param province 机场所在省份
     * @param city 机场所在城市
     * @param pageable 分页信息
     * @return 分页机场信息
     */
    Page<Airport> findAllByAddress_ProvinceAndAddress_City(String province, String city, Pageable pageable);
}
