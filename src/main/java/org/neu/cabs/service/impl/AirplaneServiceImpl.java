package org.neu.cabs.service.impl;

import org.neu.cabs.dao.AirplaneRepository;
import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.AirlineCompany;
import org.neu.cabs.orm.Airplane;
import org.neu.cabs.service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private AirplaneRepository airplaneRepository;

    @Override
    public List<Airplane> getAllAirplane() {
        return airplaneRepository.findAll();
    }

    @Override
    public Page<Airplane> getAirplanes(Pageable pageable) {
        return airplaneRepository.findAll(pageable);
    }

    @Override
    public List<Airplane> getAirplanes(AirlineCompany airlineCompany) {
        return airplaneRepository.findAllByBelongToCompany(airlineCompany);
    }

    @Override
    public Page<Airplane> getAirplanes(AirlineCompany airlineCompany, Pageable pageable) {
        return airplaneRepository.findAllByBelongToCompany(airlineCompany,pageable);
    }

    @Override
    public Airplane getAirplaneById(Long id) {
        return airplaneRepository.findOne(id);
    }

    @Override
    public ServiceResult createAirplane(Airplane airplane) {
        ServiceResult serviceResult;
        Airplane airplane_t = airplaneRepository.save(airplane);
        if (null!=airplane_t){
            serviceResult = new ServiceResult(true,"飞机信息录入成功！");
        }else {
            serviceResult = new ServiceResult(false,"飞机信息录入失败！");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult deleteAirplaneById(Long id) {
        ServiceResult serviceResult;

        if (null!=airplaneRepository.findOne(id)){
            airplaneRepository.delete(id);
            serviceResult = new ServiceResult(true,"飞机信息删除成功！");
        }else {
            serviceResult = new ServiceResult(false,"飞机信息删除失败！");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult modifyAirplane(Airplane airplane) {
        ServiceResult serviceResult;
        Airplane airplane_t = airplaneRepository.save(airplane);
        if (null!=airplane_t){
            serviceResult = new ServiceResult(true,"飞机信息修改成功！");
        }else {
            serviceResult = new ServiceResult(false,"飞机信息修改失败！");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult batchCreateAirplane(List<Airplane> airplanes) {
        ServiceResult serviceResult;
        List<Airplane> airplanes_t = airplaneRepository.save(airplanes);
        if (airplanes_t.isEmpty()){
            serviceResult = new ServiceResult(false,"所有飞机信息均录入失败！");
        }else if(airplanes_t.size()<airplanes.size()){
            serviceResult = new ServiceResult(true,"部分飞机信息录入成功！");
        }else{
            serviceResult = new ServiceResult(true,"所有信息均录入成功！");
        }
        return serviceResult;
    }
}
