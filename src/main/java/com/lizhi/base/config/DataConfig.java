package com.lizhi.base.config;/**
 * @program: hbsf
 * @name DataConfig
 * @description: 数据库初始化数据
 * @author: lizhi
 * @create: 2021-03-04 10:08
 */

import com.lizhi.base.common.enums.*;
import com.lizhi.db.upp.dao.*;
import com.lizhi.db.upp.pojo.*;
import com.lizhi.utils.DateUtils;
import com.lizhi.utils.PropertiesUtil;
import com.lizhi.utils.StringUtils;
import com.lizhi.utils.tools.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 *@program: hbsf
 *@name DataConfig
 *@description: 数据库初始化数据
 *@author: lizhi
 *@create: 2021-03-04 10:08
 */
@Slf4j
@Configuration
public class DataConfig {

    @Resource
    private UppMenuDao uppMenuDao;

    @Resource
    private UppRoleDao uppRoleDao;

    @Resource
    private UppRoleMenuDao uppRoleMenuDao;

    @Resource
    private UppUserDao uppUserDao;

    @Resource
    private UppParametersDao uppParametersDao;

    @PostConstruct
    public void initDatas(){
        String runMode = PropertiesUtil.getProperties("startup.run.mode", "system");
        if ("dev".equals(runMode)){
            log.info("------------begin initializing data------------");
            /*开始初始化菜单表*/
            UppMenuEnums[] uppMenuEnums = UppMenuEnums.class.getEnumConstants();
            if (uppMenuEnums.length > 0){
                for (UppMenuEnums item : uppMenuEnums){
                    UppMenuPOJO uppMenuPOJO = new UppMenuPOJO();
                    uppMenuPOJO.setMenuId(item.getMenuId());
                    uppMenuPOJO.setMenuTitle(item.getMenuTitle());
                    uppMenuPOJO.setMenuIcon(item.getMenuIcon());
                    uppMenuPOJO.setMenuIndex(item.getMenuIndex());
                    uppMenuPOJO.setMenuOdr(item.getMenuOdr());
                    uppMenuPOJO.setParentMenuId(item.getParentMenuId());
                    uppMenuPOJO.setMenuLevel(item.getMenuLevel());
                    uppMenuPOJO.setCreateUserAcct(item.getCreateUserAcct());
                    uppMenuPOJO.setIstTm(item.getIstTm());
                    uppMenuPOJO.setUpdTm(item.getUpdTm());
                    UppMenuPOJO menuPOJO = uppMenuDao.selectById(uppMenuPOJO.getMenuId());
                    if (StringUtils.isEmpty(menuPOJO)){
                        uppMenuDao.insertRecord(uppMenuPOJO);
                    }
                }
            }

            /*开始初始化权限表*/
            UppRoleEnums[] uppRoleEnums = UppRoleEnums.class.getEnumConstants();
            if (uppRoleEnums.length > 0){
                for (UppRoleEnums item : uppRoleEnums){
                    UppRolePOJO uppRolePOJO = new UppRolePOJO();
                    uppRolePOJO.setRoleId(item.getRoleId());
                    uppRolePOJO.setRoleNm(item.getRoleNm());
                    uppRolePOJO.setState(item.getState());
                    uppRolePOJO.setStopTm(item.getStopTm());
                    uppRolePOJO.setCreateUserAcct(item.getCreateUserAcct());
                    uppRolePOJO.setIstTm(item.getIstTm());
                    uppRolePOJO.setUpdTm(item.getUpdTm());
                    uppRolePOJO.setRoleRmk(item.getRoleRmk());
                    UppRolePOJO rolePOJO = uppRoleDao.selectById(uppRolePOJO.getRoleId());
                    if (StringUtils.isEmpty(rolePOJO)){
                        uppRoleDao.insertRecord(uppRolePOJO);
                    }
                }
            }

            /*开始初始化权限菜单关联表*/
            UppRoleMenuEnums[] uppRoleMenuEnums = UppRoleMenuEnums.class.getEnumConstants();
            if (uppRoleMenuEnums.length > 0){
                for (UppRoleMenuEnums item : uppRoleMenuEnums){
                    UppRoleMenuPOJO uppRoleMenuPOJO = new UppRoleMenuPOJO();
                    uppRoleMenuPOJO.setRoleId(item.getRoleId());
                    uppRoleMenuPOJO.setMenuId(item.getMenuId());
                    uppRoleMenuPOJO.setButtonIds(item.getButtonIds());
                    uppRoleMenuPOJO.setIstTm(item.getIstTm());
                    UppRoleMenuPOJO roleMenuPOJO = uppRoleMenuDao.selectById(uppRoleMenuPOJO.getRoleId(), uppRoleMenuPOJO.getMenuId());
                    if (StringUtils.isEmpty(roleMenuPOJO)){
                        uppRoleMenuDao.insertRecord(uppRoleMenuPOJO);
                    }
                }
            }

            /*开始初始化用户管理*/
            UppUserEnums[] uppUserEnums = UppUserEnums.class.getEnumConstants();
            if (uppUserEnums.length > 0){
                for (UppUserEnums item : uppUserEnums){
                    UppUserPOJO uppUserPOJO = new UppUserPOJO();
                    uppUserPOJO.setUserAcct(item.getUserAcct());
                    uppUserPOJO.setUserId(item.getUserId());
                    uppUserPOJO.setUserPwd(MD5Util.getMD5(item.getUserPwd()));
                    uppUserPOJO.setUserSex(item.getUserSex());
                    uppUserPOJO.setUserNm(item.getUserNm());
                    uppUserPOJO.setState(item.getState());
                    uppUserPOJO.setRoleIds(item.getRoleIds());
                    uppUserPOJO.setOrganIds(item.getOrganIds());
                    uppUserPOJO.setPsdChgFlg(item.getPsdChgFlg());
                    uppUserPOJO.setLastLoginTm(item.getLastLoginTm());
                    uppUserPOJO.setPwdErrCnt(item.getPwdErrCnt());
                    uppUserPOJO.setPwdEditTm(item.getPwdEditTm());
                    uppUserPOJO.setStopTm(item.getStopTm());
                    uppUserPOJO.setLockTm(item.getLockTm());
                    uppUserPOJO.setUserMail(item.getUserMail());
                    uppUserPOJO.setCreateUserAcct(item.getCreateUserAcct());
                    uppUserPOJO.setIstTm(DateUtils.getNowDate());
                    uppUserPOJO.setUpdTm(item.getUpdTm());
                    uppUserPOJO.setImgData(item.getImgData());
                    uppUserPOJO.setImgTp(item.getImgTp());
                    uppUserPOJO.setImgLen(item.getImgLen());
                    uppUserPOJO.setUserPhone(item.getUserPhone());
                    UppUserPOJO userPOJO = uppUserDao.selectById(uppUserPOJO.getUserAcct());
                    if (StringUtils.isEmpty(userPOJO)){
                        uppUserDao.insertRecord(uppUserPOJO);
                    }
                }
            }

            /*初始化配置表*/
            UppParameterEnums[] uppParameterEnums = UppParameterEnums.class.getEnumConstants();
            if (uppParameterEnums.length > 0){
                for (UppParameterEnums item : uppParameterEnums){
                    UppParametersPOJO uppParametersPOJO = new UppParametersPOJO();
                    uppParametersPOJO.setCode(item.getCode());
                    uppParametersPOJO.setName(item.getName());
                    uppParametersPOJO.setVal(item.getVal());
                    uppParametersPOJO.setRmk(item.getRmk());
                    uppParametersPOJO.setInsertTime(item.getInsertTime());
                    uppParametersPOJO.setUpdateTime(item.getUpdateTime());
                    UppParametersPOJO parametersPOJO = uppParametersDao.selectById(uppParametersPOJO.getCode());
                    if (StringUtils.isEmpty(parametersPOJO)){
                        uppParametersDao.insertRecord(uppParametersPOJO);
                    }
                }
            }

            log.info("------------end initializing data------------");
//            PropertiesUtil.setValue("startup.run.mode", "prod", "system");
        }
    }
}
