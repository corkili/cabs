package org.neu.cabs.controller;

import lombok.extern.log4j.Log4j;
import org.neu.cabs.dto.AdminForm;
import org.neu.cabs.dto.ResponseResult;
import org.neu.cabs.dto.ServiceResult;
import org.neu.cabs.orm.Admin;
import org.neu.cabs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 管理员管理控制器
 * @author 李浩然
 */
@Controller
@RequestMapping(value = { "/manage/admin" })
@Log4j
public class AdminManageController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = { "", "/", "/index" }, method = { RequestMethod.GET, RequestMethod.POST })
    public String index(Model model) {
        List<Admin> admins = userService.getAllAdmin();
        model.addAttribute("admins", admins);
        return "manage/admin_manage";
    }

    @RequestMapping(value = { "/createAdmin" }, method = { RequestMethod.POST })
    @ResponseBody
    public ResponseResult<AdminForm> createAdmin(@RequestBody AdminForm adminForm) {
        log.info(adminForm);
        Admin admin = AdminForm.to(adminForm);
        admin.setId(null);
        admin.setCreateTime(new Date());
        admin.setLastLoginTime(new Date());
        admin.setAvailable(true);
        admin.setSuperManager(false);
        ServiceResult serviceResult = userService.createAdmin(admin);
        return ResponseResult.from(serviceResult, AdminForm.from((Admin) serviceResult.getExtra("admin")));
    }
    
    @RequestMapping(value = { "/resetPwd" }, method = { RequestMethod.POST })
    @ResponseBody
    public ResponseResult<Object> resetPassword(@RequestBody AdminForm adminForm) {
        log.info(adminForm);
        ServiceResult serviceResult = userService.modifyPassword(adminForm.getId(), adminForm.getPassword());
        return ResponseResult.from(serviceResult);
    }

    @RequestMapping(value = { "/disableAdmin" }, method = { RequestMethod.POST })
    @ResponseBody
    public ResponseResult<Object> disableAdmin(@RequestParam("adminId") long adminId) {
        log.info(adminId);
        ServiceResult serviceResult = userService.disableUserById(adminId);
        return ResponseResult.from(serviceResult);
    }

    @RequestMapping(value = { "/enableAdmin" }, method = { RequestMethod.POST })
    @ResponseBody
    public ResponseResult<Object> enableAdmin(@RequestParam("adminId") long adminId) {
        log.info(adminId);
        ServiceResult serviceResult = userService.enableUserById(adminId);
        return ResponseResult.from(serviceResult);
    }
}
