package com.lizhi.base.common.enums;/**
 * @program: hbsf
 * @name UppParameterEnums
 * @description: 配置表初始化数据
 * @author: lizhi
 * @create: 2021-03-22 10:04
 */

import com.lizhi.utils.enums.enumi.EnumItem;

/**
 *@program: hbsf
 *@name UppParameterEnums
 *@description: 配置表初始化数据
 *@author: lizhi
 *@create: 2021-03-22 10:04
 */
public enum UppParameterEnums implements EnumItem {

    ;

    private int id;
    private String text;

    private String code;
    private String name;
    private String val;
    private String rmk;
    private String insertTime;
    private String updateTime;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getRmk() {
        return rmk;
    }

    public void setRmk(String rmk) {
        this.rmk = rmk;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    UppParameterEnums(int id, String text, String code, String name, String val, String rmk, String insertTime, String updateTime){
        this.id = id;
        this.text = text;
        this.code = code;
        this.name = name;
        this.val = val;
        this.rmk = rmk;
        this.insertTime = insertTime;
        this.updateTime = updateTime;
    }
}
