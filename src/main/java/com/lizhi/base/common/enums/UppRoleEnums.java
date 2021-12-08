package com.lizhi.base.common.enums;/**
 * @program: hbsf
 * @name UppRoleEnums
 * @description: 初始化权限枚举
 * @author: lizhi
 * @create: 2021-03-09 11:05
 */

import com.lizhi.utils.enums.enumi.EnumItem;

/**
 *@program: hbsf
 *@name UppRoleEnums
 *@description: 初始化权限枚举
 *@author: lizhi
 *@create: 2021-03-09 11:05
 */
public enum UppRoleEnums implements EnumItem {
    Role_system(1, "系统管理员",
            "system", "系统管理员", "1", null, "admin", "2020-05-08 10:19:47", null, "系统管理员"),
    ;

    private int id;
    private String text;

    private String roleId;
    private String roleNm;
    private String state;
    private String stopTm;
    private String createUserAcct;
    private String istTm;
    private String updTm;
    private String roleRmk;

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

    public String getRoleNm() {
        return roleNm;
    }

    public String getState() {
        return state;
    }

    public String getStopTm() {
        return stopTm;
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

    public String getRoleRmk() {
        return roleRmk;
    }

    UppRoleEnums(int id, String text, String roleId, String roleNm, String state, String stopTm, String createUserAcct, String istTm, String updTm, String roleRmk){
        this.id = id;
        this.text = text;
        this.roleId = roleId;
        this.roleNm = roleNm;
        this.state = state;
        this.stopTm = stopTm;
        this.createUserAcct = createUserAcct;
        this.istTm = istTm;
        this.updTm = updTm;
        this.roleRmk = roleRmk;
    }

}
