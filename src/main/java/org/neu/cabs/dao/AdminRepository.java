package org.neu.cabs.dao;

import org.neu.cabs.orm.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理员数据持久化接口
 * @author 李浩然
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
