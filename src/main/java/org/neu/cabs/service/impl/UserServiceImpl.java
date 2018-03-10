package org.neu.cabs.service.impl;

import org.neu.cabs.dao.AdminRepository;
import org.neu.cabs.dao.BaseUserRepository;
import org.neu.cabs.dao.UserRepository;
import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.Admin;
import org.neu.cabs.orm.User;
import org.neu.cabs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统核心逻辑，用户服务接口实现类
 * @author 李浩然
 * @see org.neu.cabs.service.UserService
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private BaseUserRepository baseUserRepository;

    private UserRepository userRepository;

    private AdminRepository adminRepository;

    @Autowired
    public void setBaseUserRepository(BaseUserRepository baseUserRepository) {
        this.baseUserRepository = baseUserRepository;
    }

    @Autowired
    public void setBaseUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public Page<User> getUserPage(Pageable pageable) {
        return null;
    }

    @Override
    public List<Admin> getAllAdmin() {
        return null;
    }

    @Override
    public Page<Admin> getAdminPage(Pageable pageable) {
        return null;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public Admin getAdminById(Long id) {
        return null;
    }

    @Override
    public ServiceResult disableUserById(Long id) {
        return null;
    }

    @Override
    public ServiceResult enableUserById(Long id) {
        return null;
    }

    @Override
    public ServiceResult modifyPassword(Long id, String password) {
        return null;
    }

    @Override
    public ServiceResult modifyUserInformation(User user) {
        return null;
    }

    @Override
    public ServiceResult registryUser(User user) {
        return null;
    }

    @Override
    public ServiceResult createAdmin(Admin admin) {
        return null;
    }
}
