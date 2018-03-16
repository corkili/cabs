package org.neu.cabs.dao;

import org.neu.cabs.orm.AirlineCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


/**
 * 航空公司数据持久化接口
 * @author 李浩然
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface AirlineCompanyRepository extends JpaRepository<AirlineCompany, Integer> {

}
