package com.lizhi.core.entity;/**
 * @program: msb
 * @name AbstractEntity
 * @description: 公用
 * @author: lizhi
 * @create: 2021-12-08 10:43
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 *@program: msb
 *@name AbstractEntity
 *@description: 公用
 *@author: lizhi
 *@create: 2021-12-08 10:43
 */
@Data
@ToString
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Column(columnDefinition="varchar(19) COMMENT '创建时间'")
    @ApiModelProperty(value = "创建时间")
    public String createdTime;

    @Column(columnDefinition="varchar(200) COMMENT '创建人账号'")
    @ApiModelProperty(value = "创建人账号")
    public String createdAccount;

    @Column(columnDefinition="varchar(19) COMMENT '修改时间'")
    @ApiModelProperty(value = "修改时间")
    public String updatedTime;

    @Column(columnDefinition="varchar(200) COMMENT '修改人账号'")
    @ApiModelProperty(value = "修改人账号")
    public String updatedAccount;

    @Column(columnDefinition="varchar(200) COMMENT '删除人账号'")
    @ApiModelProperty(value = "删除人账号")
    public String delAccount;

    /**
     * @see com.lizhi.common.enums.DeleteEnum
     */
    @Column(columnDefinition="varchar(2) COMMENT '是否删除'")
    @ApiModelProperty(value = "是否删除")
    public String delStatus;

    @Column(columnDefinition="varchar(19) COMMENT '删除时间'")
    @ApiModelProperty(value = "删除时间")
    public String delTime;

    /**
     * @see com.lizhi.common.enums.DataUpStateEnum
     */
    /*数据上传*/
    @Column(columnDefinition="varchar(20) COMMENT '上传云管的状态(00-未上传，01-已上传)'")
    @ApiModelProperty(value = "上传云管的状态(00-未上传，01-已上传)")
    public String dataUpState;

    @Transient
    @ApiModelProperty(value = "上传云管的状态")
    public String dataUpStateText;

    @Column(columnDefinition="varchar(19) COMMENT '上传到云管的时间'")
    @ApiModelProperty(value = "上传到云管的时间")
    public String dataUpTime;

    /*云管需要的数据*/
    @Column(columnDefinition="varchar(30) COMMENT '医院ID'")
    @ApiModelProperty(value = "医院ID")
    public String hospitalId;

    @Column(columnDefinition="varchar(30) COMMENT '系统ID'")
    @ApiModelProperty(value = "系统ID")
    public String hospitalSystemId;
}
