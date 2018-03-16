package org.neu.cabs.service;

import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.Address;
import org.neu.cabs.orm.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统核心逻辑，机场服务接口
 * @author 李浩然
 */
public interface AirportService {
    /**
     * 获取所有的机场
     * @return 机场列表
     */
    List<Airport> getAllAirport();

    /**
     * 分页获取所有机场
     * @param pageable 分页信息
     * @return 分页机场数据
     */
    Page<Airport> getAirports(Pageable pageable);

    /**
     * 获取某地的所有机场
     * @param address 地址
     * @return 机场列表
     */
    List<Airport> getAirport(Address address);

    /**
     * 分页获取某地的机场
     * @param address 机场地址
     * @param pageable 分页信息
     * @return 分页机场数据
     */
    Page<Airport> getAirports(Address address, Pageable pageable);

    /**
     * 根据ID，获取机场
     * @param id 机场ID
     * @return 机场信息
     */
    Airport getAirportById(Integer id);

    /**
     * 根据ID，删除机场
     * @param id 机场ID
     * @return 服务结果
     */
    ServiceResult deleteAirportById(Integer id);

    /**
     * 修改机场信息
     * @param airport 机场实体
     * @return 服务结果
     */
    ServiceResult modifyAirport(Airport airport);

    /**
     * 创建机场
     * @param airport 机场实体
     * @return 服务结果，包含创建成功的机场(airport: Airport)
     */
    ServiceResult createAirport(Airport airport);

    /**
     * 批量创建机场
     * @param airports 机场列表
     * @return 服务结果
     */
    ServiceResult batchCreateAirport(List<Airport> airports);
}
