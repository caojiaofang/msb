package com.lizhi.base.common.enums;/**
 * @program: lim
 * @name LimErrorEnum
 * @description: 错误信息枚举
 * @author: lizhi
 * @create: 2021-11-18 09:31
 */

import com.lizhi.utils.enums.enumi.IEnum;

/**
 *@program: lim
 *@name LimErrorEnum
 *@description: 错误信息枚举
 *@author: lizhi
 *@create: 2021-11-18 09:31
 */
public enum ErrorMessageEnum implements IEnum<String> {

    Error_0000001("000001", "数据不能为空"),
    Error_0000002("000002", "操作失败，请重试"),
    ;

    private String enName;
    private String enValue;

    @Override
    public String getEnName() {
        return enName;
    }

    @Override
    public String getEnValue() {
        return enValue;
    }

    private ErrorMessageEnum(String enName, String enValue){
        this.enName = enName;
        this.enValue = enValue;
    }
}
