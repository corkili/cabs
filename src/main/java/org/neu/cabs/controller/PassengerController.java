package org.neu.cabs.controller;

import org.neu.cabs.constant.RoleType;
import org.neu.cabs.orm.Flight;
import org.neu.cabs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    public String index(Model model, HttpSession session) {
        Set<String> roles = AuthorityUtils.authorityListToSet(SecurityContextHolder
                .getContext().getAuthentication().getAuthorities());
        if (roles.contains(RoleType.ROLE_ADMIN.name())) {
            return "redirect:/manage/index";
        } else {
            session.removeAttribute("ENDORSE_ORDER");
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
    public String personal(Model model, HttpSession session) {
        session.removeAttribute("ENDORSE_ORDER");
        model.addAttribute("error", "用户名或密码错误！请重试");
        return "personal_info";
    }

    @RequestMapping(value = { "search" }, method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("flights", new ArrayList<Flight>());
        model.addAttribute("cabin", "");
        return "search_flight";
    }

    @RequestMapping(value = { "about" }, method = RequestMethod.GET)
    public String about(HttpSession session) {
        session.removeAttribute("ENDORSE_ORDER");
        return "about_us";
    }

}
