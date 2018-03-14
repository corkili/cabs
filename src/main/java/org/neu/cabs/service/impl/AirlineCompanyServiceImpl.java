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

import java.util.ArrayList;
import java.util.List;


/**
 * 系统核心逻辑，航空公司服务接口实现类
 * @author 李浩然 谭湖东
 * @see org.neu.cabs.service.AirlineCompanyService
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AirlineCompanyServiceImpl implements AirlineCompanyService {

    private AirlineCompanyRepository airlineCompanyRepository;

    @Autowired
    public void setAirlineCompanyRepository(AirlineCompanyRepository airlineCompanyRepository) {
        this.airlineCompanyRepository = airlineCompanyRepository;
    }

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
        AirlineCompany savedAirlineCompany = airlineCompanyRepository.save(airlineCompany);
        if (savedAirlineCompany.getId() != null){
            serviceResult = new ServiceResult(true,"成功创建航空公司！", "airlineCompany", savedAirlineCompany);
        }else {
            serviceResult = new ServiceResult(false,"创建航空公司时发生错误！");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult deleteAirlineCompanyById(Integer id) {
        ServiceResult serviceResult;
        airlineCompanyRepository.delete(id);
        AirlineCompany airlineCompany = airlineCompanyRepository.findOne(id);
        if (airlineCompany == null){
            serviceResult = new ServiceResult(true,"成功删除航空公司！");
        }else {
            serviceResult = new ServiceResult(false,"删除航空公司时发生错误！");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult modifyAirlineCompany(AirlineCompany airlineCompany) {
        ServiceResult serviceResult;
        AirlineCompany modifiedAirlineCompany = airlineCompanyRepository.save(airlineCompany);
        if (modifiedAirlineCompany != null){
            serviceResult = new ServiceResult(true,"成功修改航空公司！",
                    "airlineCompany", modifiedAirlineCompany);
        }else {
            serviceResult = new ServiceResult(false,"修改航空公司时发生错误！");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult batchCreateAirlineCompany(List<AirlineCompany> airlineCompanies) {
        ServiceResult serviceResult;
        List<AirlineCompany> returnedAirlineCompanies = airlineCompanyRepository.save(airlineCompanies);
        List<AirlineCompany> savedAirlineCompanies = new ArrayList<>();
        List<AirlineCompany> notSaveAirlineCompanies = new ArrayList<>();
        for (AirlineCompany airlineCompany : returnedAirlineCompanies) {
            if (airlineCompany.getId() != null) {
                savedAirlineCompanies.add(airlineCompany);
            } else {
                notSaveAirlineCompanies.add(airlineCompany);
            }
        }
        if (savedAirlineCompanies.isEmpty()){
            serviceResult = new ServiceResult(false,"所有飞机信息录入失败！");
        }else if(savedAirlineCompanies.size() < airlineCompanies.size()){
            serviceResult = new ServiceResult(true,"部分飞机信息录入成功！");
        }else {
            serviceResult = new ServiceResult(true,"所有飞机信息录入均成功！");
        }
        serviceResult.putExtra("savedAirlineCompanies", savedAirlineCompanies)
                .putExtra("notSaveAirlineCompanies", notSaveAirlineCompanies);
        return serviceResult;
    }
}
