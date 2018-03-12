package org.neu.cabs.service;

import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.AirlineCompany;
import org.neu.cabs.orm.Airplane;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统核心逻辑，飞机服务接口
 * @author 李浩然
 */
@Transactional(rollbackFor = Exception.class)
public interface AirplaneService {
    /**
     * 获取所有飞机
     * @return 飞机列表
     */
    List<Airplane> getAllAirplane();

    /**
     * 分页获取飞机
     * @param pageable 分页列表
     * @return 分页飞机信息
     */
    Page<Airplane> getAirplanes(Pageable pageable);

    /**
     * 获取某航空公司的所有飞机
     * @param airlineCompany 航空公司
     * @return 飞机列表
     */
    List<Airplane> getAirplanes(AirlineCompany airlineCompany);

    /**
     * 分页获取某航空公司的飞机
     * @param airlineCompany 航空公司
     * @param pageable 分页列表
     * @return 分页飞机信息
     */
    Page<Airplane> getAirplanes(AirlineCompany airlineCompany, Pageable pageable);

    /**
     * 根据ID，获取飞机
     * @param id 飞机ID
     * @return 飞机实体
     */
    Airplane getAirplaneById(Long id);

    /**
     * 创建一个新的飞机
     * @param airplane 飞机实体
     * @return 服务结果，包含创建成功的飞机实体(airplane: Airplane)
     */
    ServiceResult createAirplane(Airplane airplane);

    /**
     * 根据ID，删除一个飞机
     * @param id airplane 飞机
     * @return 服务结果
     */
    ServiceResult deleteAirplaneById(Long id);

    /**
     * 修改飞机信息
     * @param airplane 飞机实体
     * @return 服务结果
     */
    ServiceResult modifyAirplane(Airplane airplane);

    /**
     * 批量创建飞机
     * @param airplanes 飞机列表
     * @return 服务结果
     */
    ServiceResult batchCreateAirplane(List<Airplane> airplanes);
}
