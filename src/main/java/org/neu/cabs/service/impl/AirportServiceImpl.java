package org.neu.cabs.service.impl;

import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.Address;
import org.neu.cabs.orm.Airport;
import org.neu.cabs.service.AirportService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统核心逻辑，机场服务接口实现类
 * @author 李浩然
 * @see org.neu.cabs.service.AirportService
 */
@Transactional(rollbackFor = Exception.class)
public class AirportServiceImpl implements AirportService {
    @Override
    public List<Airport> getAllAirport() {
        return null;
    }

    @Override
    public Page<Airport> getAirports(Pageable pageable) {
        return null;
    }

    @Override
    public List<Airport> getAirport(Address address) {
        return null;
    }

    @Override
    public Page<Airport> getAirports(Address address, Pageable pageable) {
        return null;
    }

    @Override
    public Airport getAirportById(Integer id) {
        return null;
    }

    @Override
    public ServiceResult deleteAirportById(Integer id) {
        return null;
    }

    @Override
    public ServiceResult modifyAirport(Airport airport) {
        return null;
    }

    @Override
    public ServiceResult createAirport(Airport airport) {
        return null;
    }

    @Override
    public ServiceResult batchCreateAirport(List<Airport> airports) {
        return null;
    }
}
