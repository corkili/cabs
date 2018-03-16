package org.neu.cabs.service;

import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.AirlineCompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统核心逻辑，航空公司服务接口
 * @author 李浩然
 */
public interface AirlineCompanyService {
    /**
     * 获取所有的航空公司
     * @return 航空公司列表
     */
    List<AirlineCompany> getAllAirlineCompany();

    /**
     * 分页获取所有的航空公司
     * @param pageable 分页信息
     * @return 分页航空公司信息
     */
    Page<AirlineCompany> getAirlineCompanys(Pageable pageable);

    /**
     * 根据Id，获取一个航空公司的信息
     * @param id 航空公司ID
     * @return 航空公司实体
     */
    AirlineCompany getAirlineCompanyById(Integer id);

    /**
     * 创建一个新的航空公司
     * @param airlineCompany 航空公司实体
     * @return 服务结果，包含创建成功的航空公司实体(airlineCompany: AirlineCompany)
     */
    ServiceResult createAirlineCompany(AirlineCompany airlineCompany);

    /**
     * 根据ID，删除一个航空公司
     * @param id airlineCompany 航空公司
     * @return 服务结果
     */
    ServiceResult deleteAirlineCompanyById(Integer id);

    /**
     * 修改航空公司信息
     * @param airlineCompany 航空公司实体
     * @return 服务结果，包含修改成功的航空公司实体(airlineCompany: AirlineCompany)
     */
    ServiceResult modifyAirlineCompany(AirlineCompany airlineCompany);

    /**
     * 批量创建航空公司
     * @param airlineCompanies 航空公司列表
     * @return 服务结果
     */
    ServiceResult batchCreateAirlineCompany(List<AirlineCompany> airlineCompanies);

}
