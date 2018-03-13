package org.neu.cabs.constant;

/**
 * 系统角色类型
 */
public enum RoleType {
    /**
     * 超级管理员
     */
    ROLE_ROOT("ROOT"),
    /**
     * 管理员
     */
    ROLE_ADMIN("ADMIN"),
    /**
     * 用户
     */
    ROLE_USER("USER"),
    /**
     * 游客
     */
    ROLE_PASSENGER("PASSENGER");

    private String role;

    RoleType(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
