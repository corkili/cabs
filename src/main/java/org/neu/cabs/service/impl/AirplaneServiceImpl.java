package org.neu.cabs.service.impl;

import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.AirlineCompany;
import org.neu.cabs.orm.Airplane;
import org.neu.cabs.service.AirplaneService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统核心逻辑，飞机服务接口实现类
 * @author 李浩然
 */
@Transactional(rollbackFor = Exception.class)
public class AirplaneServiceImpl implements AirplaneService {
    @Override
    public List<Airplane> getAllAirplane() {
        return null;
    }

    @Override
    public Page<Airplane> getAirplanes(Pageable pageable) {
        return null;
    }

    @Override
    public List<Airplane> getAirplanes(AirlineCompany airlineCompany) {
        return null;
    }

    @Override
    public Page<Airplane> getAirplanes(AirlineCompany airlineCompany, Pageable pageable) {
        return null;
    }

    @Override
    public Airplane getAirplaneById(Long id) {
        return null;
    }

    @Override
    public ServiceResult createAirplane(Airplane airplane) {
        return null;
    }

    @Override
    public ServiceResult deleteAirplaneById(Integer id) {
        return null;
    }

    @Override
    public ServiceResult modifyAirplane(Airplane airplane) {
        return null;
    }

    @Override
    public ServiceResult batchCreateAirplane(List<Airplane> airplanes) {
        return null;
    }
}
