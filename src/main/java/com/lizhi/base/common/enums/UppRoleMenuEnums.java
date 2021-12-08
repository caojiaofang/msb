package com.lizhi.base.common.enums;/**
 * @program: hbsf
 * @name UppRoleMenuEnums
 * @description: 权限菜单关联表
 * @author: lizhi
 * @create: 2021-03-09 11:16
 */

import com.lizhi.utils.enums.enumi.EnumItem;

/**
 *@program: hbsf
 *@name UppRoleMenuEnums
 *@description: 初始化权限菜单关联数据枚举
 *@author: lizhi
 *@create: 2021-03-09 11:16
 */
public enum UppRoleMenuEnums implements EnumItem {
    /*系统管理员*/
    ROLE_MENU_1(1, "首页",
            "system", "1", "00", "2020-06-23 16:52:30"),
    ROLE_MENU_2(2, "系统管理",
            "system", "2", "01", "2020-06-23 16:52:30"),
    ROLE_MENU_21(21, "用户管理",
            "system", "21", "00", "2020-06-23 16:52:30"),
    ROLE_MENU_22(22, "角色管理",
            "system", "22", "00", "2020-06-23 16:52:30"),
    ROLE_MENU_23(23, "菜单管理",
            "system", "23", "00", "2020-06-23 16:52:30"),
    ;

    private int id;
    private String text;

    private String roleId;
    private String menuId;
    private String buttonIds;
    private String istTm;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getText() {
        return text;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public String getButtonIds() {
        return buttonIds;
    }

    public String getIstTm() {
        return istTm;
    }

    UppRoleMenuEnums(int id, String text, String roleId, String menuId, String buttonIds, String istTm){
        this.id = id;
        this.text = text;
        this.roleId = roleId;
        this.menuId = menuId;
        this.buttonIds = buttonIds;
        this.istTm = istTm;
    }

}
