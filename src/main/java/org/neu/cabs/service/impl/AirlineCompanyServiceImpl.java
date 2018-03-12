package org.neu.cabs.service.impl;

import org.neu.cabs.dao.AirlineCompanyRepository;
import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.AirlineCompany;
import org.neu.cabs.service.AirlineCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 系统核心逻辑，航空公司服务接口实现类
 * @author 李浩然
 * @see org.neu.cabs.service.AirlineCompanyService
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AirlineCompanyServiceImpl implements AirlineCompanyService {
    @Autowired
    private AirlineCompanyRepository airlineCompanyRepository;

    @Override
    public List<AirlineCompany> getAllAirlineCompany() {
        return airlineCompanyRepository.findAll();
    }

    @Override
    public Page<AirlineCompany> getAirlineCompanys(Pageable pageable) {
        return airlineCompanyRepository.findAll(pageable);
    }

    @Override
    public AirlineCompany getAirlineCompanyById(Integer id) {
        return airlineCompanyRepository.getOne(id);
    }

    @Override
    public ServiceResult createAirlineCompany(AirlineCompany airlineCompany) {
        ServiceResult serviceResult;
        AirlineCompany airlineCompany_t = airlineCompanyRepository.save(airlineCompany);
        if (null!=airlineCompany_t){
            serviceResult = new ServiceResult(true,"飞机公司信息添加成功！");
        }else {
            serviceResult = new ServiceResult(false,"飞机公司信息添加失败！");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult deleteAirlineCompanyById(Integer id) {
        ServiceResult serviceResult;
        AirlineCompany airlineCompany = airlineCompanyRepository.findOne(id);
        if (null!=airlineCompany){
            airlineCompanyRepository.delete(id);
            serviceResult = new ServiceResult(true,"飞机公司信息删除成功！");
        }else {
            serviceResult = new ServiceResult(false,"飞机公司信息删除失败！");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult modifyAirlineCompany(AirlineCompany airlineCompany) {
        ServiceResult serviceResult;
        AirlineCompany airlineCompany_t = airlineCompanyRepository.save(airlineCompany);
        if (null!=airlineCompany_t){
            serviceResult = new ServiceResult(true,"飞机信息公司修改成功！");
        }else {
            serviceResult = new ServiceResult(false,"飞机信息公司修改失败！");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult batchCreateAirlineCompany(List<AirlineCompany> airlineCompanies) {
        ServiceResult serviceResult;
        List<AirlineCompany> airlineCompanies_t = airlineCompanyRepository.save(airlineCompanies);
        if (airlineCompanies_t.isEmpty()){
            serviceResult = new ServiceResult(false,"所有飞机信息录入失败！");
        }else if(airlineCompanies_t.size() < airlineCompanies.size()){
            serviceResult = new ServiceResult(true,"部分飞机信息录入成功！");
        }else {
            serviceResult = new ServiceResult(true,"所有飞机信息录入均成功！");
        }
        return serviceResult;
    }
}
