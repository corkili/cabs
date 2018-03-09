package org.neu.cabs.service;

import org.neu.cabs.dao.UserRepository;
import org.neu.cabs.orm.BaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Security用户服务自定义实现类
 * @author 李浩然
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BaseUser baseUser = userRepository.findByUsername(username);
        if (baseUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return baseUser;
    }
}
