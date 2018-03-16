package org.neu.cabs.dao;

import org.neu.cabs.orm.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户数据持久化接口
 * @author 李浩然
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
