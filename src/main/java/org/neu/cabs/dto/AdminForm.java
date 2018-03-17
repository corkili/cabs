package org.neu.cabs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neu.cabs.orm.Admin;

import java.util.Date;

/**
 * 管理员表单
 * @author 李浩然
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminForm {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

    /**
     * 是否可用
     */
    private boolean available;

    /**
     * 是否是超级管理员
     */
    private boolean superManager;

    public static AdminForm from(Admin admin) {
        AdminForm form = new AdminForm();
        form.setId(admin.getId());
        form.setUsername(admin.getUsername());
        form.setPassword("#");
        form.setCreateTime(admin.getCreateTime());
        form.setLastLoginTime(admin.getLastLoginTime());
        form.setAvailable(admin.isAvailable());
        form.setSuperManager(admin.isSuperManager());
        return form;
    }

    public static Admin to(AdminForm form) {
        Admin admin = new Admin();
        admin.setId(form.getId());
        admin.setUsername(form.getUsername());
        admin.setPassword(form.getPassword());
        admin.setCreateTime(form.getCreateTime());
        admin.setLastLoginTime(form.getLastLoginTime());
        admin.setAvailable(form.isAvailable());
        admin.setSuperManager(form.isSuperManager());
        return admin;
    }

    public static Admin to(AdminForm form, Admin admin) {
        admin.setUsername(form.getUsername());
        admin.setPassword(form.getPassword());
        admin.setCreateTime(form.getCreateTime());
        admin.setLastLoginTime(form.getLastLoginTime());
        admin.setAvailable(form.isAvailable());
        admin.setSuperManager(form.isSuperManager());
        return admin;
    }
}
