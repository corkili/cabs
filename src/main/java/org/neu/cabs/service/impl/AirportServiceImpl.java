package org.neu.cabs.service.impl;

import org.neu.cabs.dao.AirportRepository;
import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.Address;
import org.neu.cabs.orm.Airport;
import org.neu.cabs.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统核心逻辑，机场服务接口实现类
 * @author 李浩然 谭湖东
 * @see org.neu.cabs.service.AirportService
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AirportServiceImpl implements AirportService {

    private AirportRepository airportRepository;

    @Autowired
    public void setAirportRepository(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public List<Airport> getAllAirport() {
        return airportRepository.findAll();
    }

    @Override
    public Page<Airport> getAirports(Pageable pageable) {
        return airportRepository.findAll(pageable);
    }

    @Override
    public List<Airport> getAirport(Address address) {
        return airportRepository.findAllByAddress(address);
    }

    @Override
    public Page<Airport> getAirports(Address address, Pageable pageable) {
        return airportRepository.findAllByAddress(address, pageable);
    }

    @Override
    public Airport getAirportById(Integer id) {
        return airportRepository.findOne(id);
    }

    @Override
    public ServiceResult deleteAirportById(Integer id) {
        Airport airport = airportRepository.findOne(id);
        ServiceResult serviceResult;
        if (null!=airport){
            airportRepository.delete(airport);
            serviceResult = new ServiceResult(true,"机场信息删除成功！");
        }else {
            serviceResult = new ServiceResult(false,"机场信息删除失败！");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult modifyAirport(Airport airport) {
        ServiceResult serviceResult;
        Airport airportT = airportRepository.save(airport);
        if (null!=airportT){
            serviceResult = new ServiceResult(true,"机场信息修改成功！");
        }else {
            serviceResult = new ServiceResult(false,"机场信息修改失败！");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult createAirport(Airport airport) {
        ServiceResult serviceResult;

        if (null==airportRepository.findOne(airport.getId())){
            airportRepository.save(airport);
            serviceResult = new ServiceResult(true,"机场信息添加成功！");
        }else {
            serviceResult = new ServiceResult(false,"机场信息添加失败！");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult batchCreateAirport(List<Airport> airports) {
        ServiceResult serviceResult;
        List<Airport> airportsT = airportRepository.save(airports);
        if (airportsT.isEmpty()){
            serviceResult = new ServiceResult(false,"所有航班信息修改均失败！");
        }else if(airportsT.size()<airports.size()){
            serviceResult = new ServiceResult(true,"部分航班信息修改成功！");
        }else {
            serviceResult = new ServiceResult(true,"所有航班信息修改均成功！");
        }
        return serviceResult;
    }
}
