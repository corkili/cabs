package org.neu.cabs.service.impl;

import org.neu.cabs.constant.RoleType;
import org.neu.cabs.constant.Sex;
import org.neu.cabs.dao.AdminRepository;
import org.neu.cabs.dao.BaseUserRepository;
import org.neu.cabs.dao.RoleRepository;
import org.neu.cabs.dao.UserRepository;
import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.*;
import org.neu.cabs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 系统核心逻辑，用户服务接口实现类
 * @author 李浩然 谭湖东
 * @see org.neu.cabs.service.UserService
 */
@Service
public class UserServiceImpl implements UserService {

    private RoleRepository roleRepository;

    private BaseUserRepository baseUserRepository;

    private UserRepository userRepository;

    private AdminRepository adminRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setBaseUserRepository(BaseUserRepository baseUserRepository) {
        this.baseUserRepository = baseUserRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getUserPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<Admin> getAllAdmin() {
        return adminRepository.findAllBySuperManager(false);
    }

    @Override
    public Page<Admin> getAdminPage(Pageable pageable) {
        return adminRepository.findAll(pageable);
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findOne(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Admin getAdminById(Long id) {

        return adminRepository.findOne(id);
    }

    @Override
    public ServiceResult disableUserById(Long id) {

        ServiceResult serviceResult;
        BaseUser baseUser = baseUserRepository.findOne(id);
        if(null!=baseUser){
            baseUser.setAvailable(false);
            baseUserRepository.save(baseUser);
            serviceResult = new ServiceResult(true,"禁用成功！");
        }else {
            serviceResult = new ServiceResult(false,"禁用失败！");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult enableUserById(Long id) {
        ServiceResult serviceResult;
        BaseUser baseUser = baseUserRepository.findOne(id);
        if(null!=baseUser){
            baseUser.setAvailable(true);
            baseUserRepository.save(baseUser);
            serviceResult = new ServiceResult(true,"解封成功！");
        }else {
            serviceResult = new ServiceResult(false,"解封失败！");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult modifyPassword(Long id, String password) {
        ServiceResult serviceResult;
        BaseUser baseUser = baseUserRepository.findOne(id);
        if(null != baseUser){
            baseUser.setPassword(password);
            baseUserRepository.save(baseUser);
            serviceResult = new ServiceResult(true,"密码修改成功！");

        }else{
            serviceResult = new ServiceResult(false,"密码修改失败！");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult modifyUserInformation(User user) {
        ServiceResult serviceResult;
        User modifiedUser = userRepository.save(user);
        if (modifiedUser != null){
            serviceResult = new ServiceResult(true,"信息修改成功！", "user", modifiedUser);
        }else{
            serviceResult = new ServiceResult(false,"信息修改失败！");
        }
        return serviceResult;

    }

    @Override
    public ServiceResult registryUser(User user) {
        ServiceResult serviceResult;
        long count = baseUserRepository.countByUsername(user.getUsername());
        if (count==0){
            // 为0，则不存在该用户
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName(RoleType.ROLE_USER.name()));
            user.setRoles(roles);
            user.setCreateTime(new Date());
            user.setLastLoginTime(new Date());
            user.setSex(Sex.MAN);
            user.setBirthday(new Date());
            user.setAddress(new Address("", "", ""));
            user.setOrders(new HashSet<>());
            user.setPassengers(new HashSet<>());
            user.setAvailable(true);
            User savedUser = baseUserRepository.save(user);
            serviceResult = new ServiceResult(true,"注册成功!", "user", savedUser);
        }else{
            serviceResult = new ServiceResult(false,"用户名已经存在!");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult createAdmin(Admin admin) {
        ServiceResult serviceResult;
        long count = baseUserRepository.countByUsername(admin.getUsername());
        if (count == 0){
            // 为0，则不存在该用户
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName(RoleType.ROLE_ADMIN.name()));
            admin.setRoles(roles);
            Admin savedAdmin = baseUserRepository.save(admin);
            serviceResult = new ServiceResult(true,"管理员账号注册成功!", "admin", savedAdmin);
        }else{
            serviceResult = new ServiceResult(false,"该管理员账号已经存在!");
        }
        return serviceResult;
    }

    @Override
    public void login(String username) {
        BaseUser user = baseUserRepository.findByUsername(username);
        user.setLastLoginTime(new Date());
        baseUserRepository.save(user);
    }

    @Override
    public ServiceResult batchModifyUser(List<User> users) {
        ServiceResult serviceResult;
        List<User> usersT = userRepository.save(users);
        if (usersT.isEmpty()){
            serviceResult = new ServiceResult(false,"修改失败");
        }else if(usersT.size()!=users.size()){
            serviceResult = new ServiceResult(true,"部分修改成功！");
        }else{
            serviceResult = new ServiceResult(true,"全部修改成功！");
        }
        return serviceResult;
    }
}
