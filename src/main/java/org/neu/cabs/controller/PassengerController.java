package org.neu.cabs.controller;

import org.neu.cabs.constant.RoleType;
import org.neu.cabs.dto.Msg;
import org.neu.cabs.orm.BaseUser;
import org.neu.cabs.orm.User;
import org.neu.cabs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Set;

/**
 * 匿名用户控制器
 * @author 李浩然
 */
@Controller
public class PassengerController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = { "/", "index" })
    public String index(Model model) {
        Set<String> roles = AuthorityUtils.authorityListToSet(SecurityContextHolder
                .getContext().getAuthentication().getAuthorities());
        if (roles.contains(RoleType.ROLE_ADMIN.name())) {
            return "redirect:/manage/index";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/dispatch")
    public String dispatch() {
        Set<String> roles = AuthorityUtils.authorityListToSet(
                SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.login(username);
        if (roles.contains(RoleType.ROLE_ADMIN.name())) {
            return "redirect:/manage/index";
        } else {
            return "redirect:/index";
        }
    }

    @RequestMapping(value = { "personal" }, method = RequestMethod.GET)
    public String personal() {
        return "personal_info";
    }

    @RequestMapping(value = { "search" }, method = RequestMethod.GET)
    public String search() {
        return "search_flight";
    }

    @RequestMapping(value = { "about" }, method = RequestMethod.GET)
    public String about() {
        return "about_us";
    }
}
