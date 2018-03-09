package org.neu.cabs.config;

import org.neu.cabs.constant.CertificateType;
import org.neu.cabs.constant.Sex;
import org.neu.cabs.dao.RoleRepository;
import org.neu.cabs.dao.UserRepository;
import org.neu.cabs.orm.Address;
import org.neu.cabs.orm.Admin;
import org.neu.cabs.orm.User;
import org.neu.cabs.orm.Role;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * 程序初始化监听器
 * 在容器化初始化完毕后执行相应方法，初始化数据库中的数据
 * @author 李浩然
 */
@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            UserRepository userRepository = event.getApplicationContext().getBean(UserRepository.class);
            RoleRepository roleRepository = event.getApplicationContext().getBean(RoleRepository.class);
            if (roleRepository.count() == 0) {
                List<Role> roleList = new ArrayList<>();
                roleList.add(new Role("ROLE_ADMIN"));
                roleList.add(new Role("ROLE_USER"));
                roleList.add(new Role("ROLE_PASSENGER"));
                roleRepository.save(roleList);
            }
            List<Role> allRoles = roleRepository.findAll();
            if (userRepository.countByUsername("admin") == 0) {
                Admin admin = new Admin();
                admin.setUsername("admin");
                admin.setPassword("admin");
                admin.setSuperManager(true);
                admin.setAvailable(true);
                admin.setCreateTime(new Date());
                Set<Role> roles = new HashSet<>();
                for (Role role : allRoles) {
                    if ("ROLE_ADMIN".equals(role.getName().toUpperCase())) {
                        roles.add(role);
                    }
                }
                admin.setRoles(roles);
                userRepository.save(admin);
            }
            if (userRepository.countByUsername("user") == 0) {
                User user = new User();
                user.setUsername("user");
                user.setPassword("user");
                user.setCreateTime(new Date());
                user.setAvailable(true);
                Set<Role> roles = new HashSet<>();
                for (Role role : allRoles) {
                    if ("ROLE_PASSENGER".equals(role.getName().toUpperCase())
                            || "ROLE_USER".equals(role.getName().toUpperCase())) {
                        roles.add(role);
                    }
                }
                user.setRoles(roles);
                user.setRealName("李浩然");
                user.setCertificateType(CertificateType.CHINA_IDENTITY_CARD);
                user.setCertificateNumber("522123199701050014");
                user.setSex(Sex.MAN);
                Calendar calendar = Calendar.getInstance();
                calendar.set(1997, Calendar.FEBRUARY, 5);
                user.setBirthday(calendar.getTime());
                user.setPhone("15528235793");
                user.setEmail("15528235793@163.com");
                Address address = new Address("四川省", "成都市", "双流县");
                user.setAddress(address);
                user.setOrders(new HashSet<>());
                userRepository.save(user);
            }
        }
    }
}
