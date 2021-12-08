package com.lizhi.base.common.enums;/**
 * @program: hbsf
 * @name UppMenuEnum
 * @description: 初始化菜单枚举
 * @author: lizhi
 * @create: 2021-03-04 09:27
 */

import com.lizhi.utils.enums.enumi.EnumItem;

/**
 *@program: hbsf
 *@name UppMenuEnum
 *@description: 初始化菜单枚举
 *@author: lizhi
 *@create: 2021-03-04 09:27
 */
public enum UppMenuEnums implements EnumItem {
    Menu_1(1, "系统首页",
            "1", "系统首页", "el-icon-s-home", "manage", 1, "",
            1, "admin", "2021-03-04 09:44:33", ""),
    /***************************************************系统管理**********************************************************************/
    Meun_2(2, "系统管理",
            "2", "系统管理", "el-icon-s-tools", "system", 2, "",
            1, "admin", "2021-03-04 09:44:33", ""),
    Meun_21(21, "用户管理",
            "21", "用户管理", "", "user", 1, "2",
            2, "admin", "2021-03-04 09:44:33", ""),
    Meun_22(22, "角色管理",
            "22", "角色管理", "", "role", 2, "2",
            2, "admin", "2021-03-04 09:44:33", ""),
    Meun_23(23, "菜单管理",
            "23", "菜单管理", "", "menus", 3, "2",
            2, "admin", "2021-03-04 09:44:33", ""),
    ;


    private int id;
    private String text;

    private String menuId;
    private String menuTitle;
    private String menuIcon;
    private String menuIndex;
    private Integer menuOdr;
    private String parentMenuId;
    private Integer menuLevel;
    private String createUserAcct;
    private String istTm;
    private String updTm;

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getText() {
        return this.text;
    }

    public String getMenuId() {
        return menuId;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public String getMenuIndex() {
        return menuIndex;
    }

    public Integer getMenuOdr() {
        return menuOdr;
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public String getCreateUserAcct() {
        return createUserAcct;
    }

    public String getIstTm() {
        return istTm;
    }

    public String getUpdTm() {
        return updTm;
    }

    UppMenuEnums(int id, String text, String menuId, String menuTitle, String menuIcon, String menuIndex, Integer menuOdr, String parentMenuId,
                 Integer menuLevel, String createUserAcct, String istTm, String updTm){
        this.id = id;
        this.text = text;
        this.menuId = menuId;
        this.menuTitle = menuTitle;
        this.menuIcon = menuIcon;
        this.menuIndex = menuIndex;
        this.menuOdr = menuOdr;
        this.parentMenuId = parentMenuId;
        this.menuLevel = menuLevel;
        this.createUserAcct = createUserAcct;
        this.istTm = istTm;
        this.updTm = updTm;
    }
}
