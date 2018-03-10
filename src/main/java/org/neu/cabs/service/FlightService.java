package org.neu.cabs.service;

import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.AirlineCompany;
import org.neu.cabs.orm.Airport;
import org.neu.cabs.orm.City;
import org.neu.cabs.orm.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 系统核心逻辑，游客服务接口
 * @author 李浩然
 */
@Transactional(rollbackFor = Exception.class)
public interface FlightService {
    /**
     * 搜索航班（条件搜索）
     * @param from 出发地
     * @param to 目的地
     * @param date date
     * @return 航班列表
     */
    List<Flight> searchFlight(City from, City to, Date date);

    /**
     * 搜索航班（条件搜索）
     * @param from 出发机场
     * @param to 到达机场
     * @param date date
     * @return 航班列表
     */
    List<Flight> searchFlight(Airport from, Airport to, Date date);

    /**
     * 通过航班号搜索航班
     * @param flightNumber 航班号
     * @return 航班实体
     */
    Flight searchFlightByFlightNumber(String flightNumber);

    /**
     * 新增航班
     * @param flight 航班实体
     * @return 服务结果，包含创建成功的航班(flight: Flight)
     */
    ServiceResult createFlight(Flight flight);

    /**
     * 获取所有航班
     * @return 航班列表
     */
    List<Flight> getAllFlight();

    /**
     * 分页获取航班
     * @param pageable 分页信息
     * @return 分页航班信息
     */
    Page<Flight> getFlights(Pageable pageable);

    /**
     * 获取时间范围内的航班
     * @param start 开始时间
     * @param end 结束时间
     * @return 航班列表
     */
    List<Flight> getFlights(Date start, Date end);

    /**
     * 分页获取时间范围内的航班
     * @param start 开始时间
     * @param end 结束时间
     * @param pageable 分页信息
     * @return 分页航班信息
     */
    Page<Flight> getFlights(Date start, Date end, Pageable pageable);

    /**
     * 获取时间范围内某航空公司运营的航班
     * @param start 开始时间
     * @param end 结束航班
     * @param airlineCompany 运营的航空公司
     * @return 航班列表
     */
    List<Flight> getFlights(Date start, Date end, AirlineCompany airlineCompany);

    /**
     * 分页获取时间范围内某航空公司运营的航班
     * @param start 开始时间
     * @param end 结束时间
     * @param airlineCompany 运营的航空公司
     * @param pageable 分页信息
     * @return 分页航班信息
     */
    Page<Flight> getFlights(Date start, Date end, AirlineCompany airlineCompany, Pageable pageable);

    /**
     * 获取时间范围内,从出发机场到到达机场的航班
     * @param start 开始时间
     * @param end 结束航班
     * @param departureAirport 出发机场
     * @param arrivalAirport 到达机场
     * @return 航班列表
     */
    List<Flight> getFlights(Date start, Date end, Airport departureAirport, Airport arrivalAirport);


    /**
     * 分页获取时间范围内,从出发机场到到达机场的航班
     * @param start 开始时间
     * @param end 结束航班
     * @param departureAirport 出发机场
     * @param arrivalAirport 到达机场
     * @return 分页航班信息
     */
    Page<Flight> getFlights(Date start, Date end, Airport departureAirport, Airport arrivalAirport, Pageable pageable);

    /**
     * 根据ID，获取一个航班
     * @param id 航班ID
     * @return 航班实体
     */
    Flight getFlight(Long id);

    /**
     * 修改航班信息
     * @param flight 航班实体
     * @return 服务结果
     */
    ServiceResult modifyFlight(Flight flight);

    /**
     * 根据ID，删除航班
     * @param id 航班ID
     * @return 服务结果
     */
    ServiceResult deleteFlightById(Long id);
}
