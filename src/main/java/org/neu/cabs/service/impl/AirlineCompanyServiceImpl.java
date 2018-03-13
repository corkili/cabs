package org.neu.cabs.service.impl;

import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.AirlineCompany;
import org.neu.cabs.service.AirlineCompanyService;
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
    @Override
    public List<AirlineCompany> getAllAirlineCompany() {
        return null;
    }

    @Override
    public Page<AirlineCompany> getAirlineCompanys(Pageable pageable) {
        return null;
    }

    @Override
    public AirlineCompany getAirlineCompanyById(Integer id) {
        return null;
    }

    @Override
    public ServiceResult createAirlineCompany(AirlineCompany airlineCompany) {
        return null;
    }

    @Override
    public ServiceResult deleteAirlineCompanyById(Integer id) {
        return null;
    }

    @Override
    public ServiceResult modifyAirlineCompany(AirlineCompany airlineCompany) {
        return null;
    }

    @Override
    public ServiceResult batchCreateAirlineCompany(List<AirlineCompany> airlineCompanies) {
        return null;
    }
}
