package org.neu.cabs.dao;

import org.neu.cabs.orm.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 管理员数据持久化接口
 * @author 李浩然
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {
    /**
     * 查询所有管理员用户
     * @param superManager 是否是超级管理员
     * @return 管理员列表
     */
    List<Admin> findAllBySuperManager(boolean superManager);
}
