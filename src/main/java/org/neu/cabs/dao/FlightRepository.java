package org.neu.cabs.dao;

import org.neu.cabs.orm.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 航班数据持久化接口
 * @author 李浩然
 */
@Transactional(rollbackFor = Exception.class)
public interface FlightRepository extends JpaRepository<Flight, Long> {
    /**
     * 查询某天从出发城市到目的地城市的航班
     * @param departureCity 出发城市
     * @param arrivalCity 目的地城市
     * @param takeoffDate 出发日期
     * @return 航班列表
     */
    List<Flight> findAllByDepartureCityAndArrivalCityAndTakeoffDate(
            City departureCity,
            City arrivalCity,
            Date takeoffDate
    );

    /**
     * 查询某天从出发机场到到达机场的航班
     * @param departureAirport 出发机场
     * @param arrivalAirport 到达机场
     * @param takeoffDate 出发日期
     * @return 航班列表
     */
    List<Flight> findAllByDepartureAirportAndArrivalAirportAndTakeoffDate(
            Airport departureAirport,
            Airport arrivalAirport,
            Date takeoffDate
    );

    /**
     * 获取时间范围内的航班
     * @param start 开始时间
     * @param end 结束时间
     * @return 航班列表
     */
    List<Flight> findAllByTakeoffDateBetween(Date start, Date end);

    /**
     * 分页获取时间范围内的航班
     * @param start 开始时间
     * @param end 结束时间
     * @param pageable 分页信息
     * @return 分页航班信息
     */
    Page<Flight> findAllByTakeoffDateBetween(Date start, Date end, Pageable pageable);

    /**
     * 获取时间范围内某航空公司运营的航班
     * @param start 开始时间
     * @param end 结束航班
     * @param airlineCompany 运营的航空公司
     * @return 航班列表
     */
    List<Flight> findAllByTakeoffDateBetweenAndAirlineCompany(
            Date start,
            Date end,
            AirlineCompany airlineCompany
    );

    /**
     * 分页获取时间范围内某航空公司运营的航班
     * @param start 开始时间
     * @param end 结束时间
     * @param airlineCompany 运营的航空公司
     * @param pageable 分页信息
     * @return 分页航班信息
     */
    Page<Flight> findAllByTakeoffDateBetweenAndAirlineCompany(
            Date start,
            Date end,
            AirlineCompany airlineCompany,
            Pageable pageable
    );


    /**
     * 获取时间范围内,从出发机场到到达机场的航班
     * @param start 开始时间
     * @param end 结束航班
     * @param departureAirport 出发机场
     * @param arrivalAirport 到达机场
     * @return 航班列表
     */
    List<Flight> findAllByTakeoffDateBetweenAndDepartureAirportAndArrivalAirport(
            Date start,
            Date end,
            Airport departureAirport,
            Airport arrivalAirport
    );


    /**
     * 分页获取时间范围内,从出发机场到到达机场的航班
     * @param start 开始时间
     * @param end 结束航班
     * @param departureAirport 出发机场
     * @param arrivalAirport 到达机场
     * @return 分页航班信息
     */
    Page<Flight> findAllByTakeoffDateBetweenAndDepartureAirportAndArrivalAirport(
            Date start,
            Date end,
            Airport departureAirport,
            Airport arrivalAirport,
            Pageable pageable
    );

    /**
     * 通过航班号获取航班
     * @param flightNumber 航班号
     * @return 航班
     */
    Flight findByFlightNumber(String flightNumber);
}
