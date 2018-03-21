package org.neu.cabs.service;

import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.Admin;
import org.neu.cabs.orm.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统核心逻辑，用户服务接口
 * @author 李浩然
 */
public interface UserService {
    /**
     * 获取所有的用户，只允许管理员调用
     * @return 所有用户的列表
     */
    List<User> getAllUsers();

    /**
     * 获取分页的用户数据，只允许管理员调用
     * @param pageable 分页信息
     * @return 分页的用户数据
     */
    Page<User> getUserPage(Pageable pageable);

    /**
     * 获取所有的管理员，只允许超级管理员调用
     * @return 所有管理员的列表
     */
    List<Admin> getAllAdmin();

    /**
     * 获取分页的管理员数据，只允许超级管理员调用
     * @param pageable 分页信息
     * @return 分页的管理员数据
     */
    Page<Admin> getAdminPage(Pageable pageable);

    /**
     * 获取一个ID为id的用户的信息
     * @param id 用户ID
     * @return ID为id的用户信息
     */
    User getUserById(Long id);

    /**
     * 获取用户名为username的用户
     * @param username 用户名
     * @return 用户
     */
    User getUserByUsername(String username);

    /**
     * 获取一个ID为id的管理员的信息
     * @param id 用户ID
     * @return ID为id的管理员信息
     */
    Admin getAdminById(Long id);

    /**
     * 禁用一个ID为id的用户
     * @param id 用户ID
     * @return 服务结果
     */
    ServiceResult disableUserById(Long id);

    /**
     * 激活一个ID为id的用户
     * @param id 用户ID
     * @return 服务结果
     */
    ServiceResult enableUserById(Long id);

    /**
     * 修改ID为id的用户的用户密码
     * @param id 用户ID
     * @param password 新密码
     * @return 服务结果
     */
    ServiceResult modifyPassword(Long id, String password);

    /**
     * 修改用户信息
     * @param user 用户实体
     * @return 服务结果
     */
    ServiceResult modifyUserInformation(User user);

    /**
     * 注册用户信息
     * @param user 用户实体
     * @return 服务结果
     */
    ServiceResult registryUser(User user);

    /**
     * 添加管理员
     * @param admin 管理员实体
     * @return 服务结果
     */
    ServiceResult createAdmin(Admin admin);

    /**
     * 更新用户登录后的信息
     * @param username 用户名
     */
    void login(String username);

    /**
     * 批量修改用户信息
     * @param users 用户列表
     * @return 服务结果
     */
    ServiceResult batchModifyUser(List<User> users);
}
