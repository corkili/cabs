package org.neu.cabs.controller;

import lombok.extern.log4j.Log4j;
import org.neu.cabs.dto.ResponseResult;
import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.User;
import org.neu.cabs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户管理控制器
 * @author 李浩然
 */
@Controller
@RequestMapping(value = { "/manage/user" })
@Log4j
public class UserManageController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = { "", "/", "/index" }, method = { RequestMethod.GET, RequestMethod.POST })
    public String index(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "manage/user_manage";
    }

    @RequestMapping(value = { "/disableUser" }, method = { RequestMethod.POST })
    @ResponseBody
    public ResponseResult<Object> disableUser(@RequestParam("userId") long userId) {
        log.info(userId);
        ServiceResult serviceResult = userService.disableUserById(userId);
        return ResponseResult.from(serviceResult);
    }

    @RequestMapping(value = { "/enableUser" }, method = { RequestMethod.POST })
    @ResponseBody
    public ResponseResult<Object> enableUser(@RequestParam("userId") long userId) {
        log.info(userId);
        ServiceResult serviceResult = userService.enableUserById(userId);
        return ResponseResult.from(serviceResult);
    }
}
