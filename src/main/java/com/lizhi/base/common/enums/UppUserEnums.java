package com.lizhi.base.common.enums;/**
 * @program: hbsf
 * @name UppUserEnums
 * @description: 用户管理初始化
 * @author: lizhi
 * @create: 2021-03-09 11:33
 */

import com.lizhi.utils.enums.enumi.EnumItem;

/**
 *@program: hbsf
 *@name UppUserEnums
 *@description: 用户管理初始化
 *@author: lizhi
 *@create: 2021-03-09 11:33
 */
public enum UppUserEnums implements EnumItem {

    User_Admin(1, "管理员",
    "admin", "715575924912824324", "123456", "00", "admin", "1", "system", null,
            "1", null, 0, "", "", "", "", "admin", "",
            "", "", "", "", "");

    private int id;
    private String text;

    private String userAcct;
    private String userId;
    private String userPwd;
    private String userSex;
    private String userNm;
    private String state;
    private String roleIds;
    private String organIds;
    private String psdChgFlg;
    private String lastLoginTm;
    private Integer pwdErrCnt;
    private String pwdEditTm;
    private String stopTm;
    private String lockTm;
    private String userMail;
    private String createUserAcct;
    private String istTm;
    private String updTm;
    private String imgData;
    private String imgTp;
    private String imgLen;
    private String userPhone;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getText() {
        return text;
    }

    public String getUserAcct() {
        return userAcct;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public String getUserNm() {
        return userNm;
    }

    public String getUserSex() {
        return userSex;
    }

    public String getState() {
        return state;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public String getOrganIds() {
        return organIds;
    }

    public String getPsdChgFlg() {
        return psdChgFlg;
    }

    public String getLastLoginTm() {
        return lastLoginTm;
    }

    public Integer getPwdErrCnt() {
        return pwdErrCnt;
    }

    public String getPwdEditTm() {
        return pwdEditTm;
    }

    public String getStopTm() {
        return stopTm;
    }

    public String getLockTm() {
        return lockTm;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserMail() {
        return userMail;
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

    public String getImgData() {
        return imgData;
    }

    public String getImgTp() {
        return imgTp;
    }

    public String getImgLen() {
        return imgLen;
    }

    UppUserEnums(int id, String text, String userAcct, String userId, String userPwd, String userSex, String userNm, String state, String roleIds, String organIds,
                 String psdChgFlg, String lastLoginTm, int pwdErrCnt, String pwdEditTm, String stopTm, String lockTm, String userMail, String createUserAcct,
                 String istTm, String updTm, String imgData, String imgTp, String imgLen, String userPhone){
        this.id = id;
        this.text = text;
        this.userAcct = userAcct;
        this.userId = userId;
        this.userPwd = userPwd;
        this.userNm = userNm;
        this.userSex = userSex;
        this.state = state;
        this.roleIds = roleIds;
        this.organIds = organIds;
        this.psdChgFlg = psdChgFlg;
        this.lastLoginTm = lastLoginTm;
        this.pwdErrCnt = pwdErrCnt;
        this.pwdEditTm = pwdEditTm;
        this.stopTm = stopTm;
        this.lockTm = lockTm;
        this.userPhone = userPhone;
        this.userMail = userMail;
        this.createUserAcct = createUserAcct;
        this.istTm = istTm;
        this.updTm = updTm;
        this.imgData = imgData;
        this.imgTp = imgTp;
        this.imgLen = imgLen;
    }
}
