package org.neu.cabs.service.impl;

import org.neu.cabs.dao.BaseUserRepository;
import org.neu.cabs.orm.BaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Security用户服务自定义实现类
 * @author 李浩然 谭湖东
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private BaseUserRepository baseUserRepository;

    @Autowired
    public void setBaseUserRepository(BaseUserRepository baseUserRepository) {
        this.baseUserRepository = baseUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BaseUser baseUser = baseUserRepository.findByUsername(username);
        if (baseUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return baseUser;
    }
}
