package org.neu.cabs.dao;

import org.neu.cabs.orm.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统角色数据持久化接口
 * @author 李浩然
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
@Transactional(rollbackFor = Exception.class)
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
