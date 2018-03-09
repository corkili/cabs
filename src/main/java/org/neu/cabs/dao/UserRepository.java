package org.neu.cabs.dao;

import org.neu.cabs.orm.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户数据持久化接口
 * @author 李浩然
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface UserRepository extends JpaRepository<BaseUser, Long> {
    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户
     */
    BaseUser findByUsername(String username);

    /**
     * 统计用户名为username的用户数量
     * @param username 用户名
     * @return 用户数量
     */
    long countByUsername(String username);
}
