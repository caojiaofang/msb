package com.lizhi.base;/**
 * @program: spring-boot
 * @name BaseHandler
 * @description:
 * @author: lizhi
 * @create: 2020-04-11 16:13
 */

import com.lizhi.common.entity.ListResponseResult;
import com.lizhi.mybatis.interceptor.PageBounds;
import com.lizhi.mybatis.page.Pager;
import com.lizhi.utils.StringUtils;
import com.lizhi.utils.tools.BeanCopyUtil;
import com.lizhi.utils.tools.CollectionUtil;
import com.lizhi.utils.tools.NumberUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *@program: spring-boot
 *@name BaseHandler
 *@description:
 *@author: lizhi
 *@create: 2020-04-11 16:13
 */
@Slf4j
public abstract class BaseHandler {

    public <T> void convertDo2Vo(Pager pager, List<T> listVo) {

        if (CollectionUtil.isNEmpty(pager.getResult())) {
            for (int i = 0; i < pager.getResult().size(); i++) {
                BeanCopyUtil.copy(pager.getResult().get(i), listVo.get(i));
            }
        }
        pager.setResult(listVo);
    }

    public <T> List<T> convertDo2Vo(List<?> theDOList, Class<T> t) {

        List<T> list = new LinkedList<T>();

        for (int i = 0; i < theDOList.size(); i++) {
            T dest = null;
            try {
                dest = t.newInstance();
            } catch (Exception e) {
                throw new IllegalArgumentException("创建对象失败", e);
            }
            BeanCopyUtil.copy(theDOList.get(i), dest);
            list.add(dest);
        }
        return list;

    }

    public <T> PagerView<T> getPagerView(int total, List<?> theDOList, Class<T> t) {
        List<T> list = this.convertDo2Vo(theDOList, t);
        PagerView<T> pagerView = new PagerView<T>();
        pagerView.setTotal(total);
        pagerView.setRows(list);
        return pagerView;
    }

    public PageBounds getPageBounds(Pager pager) {
        PageBounds pageBounds = new PageBounds(pager.getRowStart(), pager.getPageSize());
        return pageBounds;
    }


    public Pager getPager(Map<String, Object> params) {
        Pager pager = new Pager();
        pager.setPageIndex(NumberUtil.parseInt(params.get("pageIndex"), 1));
        pager.setPageSize(NumberUtil.parseInt(params.get("pageSize"), 10));
        return pager;
    }

    public PageBounds getPageBounds(Map<String, Object> params) {
        Pager pager = getPager(params);
        PageBounds pageBounds = new PageBounds(pager.getRowStart(), pager.getPageSize());
        return pageBounds;
    }

    /*
     *功能描述 适用于前后端分离
     * @author lizhi
     * @date 2020/6/3
     * @param null:
     * @return
     */
    public ListResponseResult getListResult(int total, List<?> theDOList, Map<String, Object> map){
        ListResponseResult listResponseResult = new ListResponseResult();
        listResponseResult.setData(theDOList);
        listResponseResult.setTotal(total);
        listResponseResult.setSuccess(true);
        if (map != null && map.containsKey("pageIndex")){
            listResponseResult.setCurrent(Integer.valueOf((String) map.get("pageIndex")));
        }
        if (map != null && map.containsKey("pageSize")){
            listResponseResult.setPageSize((String) map.get("pageSize"));
        }
        return listResponseResult;
    }

    /**
     * 返回的是对象
     * @param object
     * @return
     */
    public ListResponseResult getListResult(Object object){
        ListResponseResult listResponseResult = new ListResponseResult();
        if (StringUtils.isEmpty(object)){
            listResponseResult.setData(null);
        } else {
            listResponseResult.setData(object);
        }
        listResponseResult.setSuccess(true);
        return listResponseResult;
    }

    public ListResponseResult getListResult(Object object, boolean success){
        ListResponseResult listResponseResult = new ListResponseResult();
        if (StringUtils.isEmpty(object)){
            listResponseResult.setData(null);
        } else {
            listResponseResult.setData(object);
        }
        listResponseResult.setSuccess(success);
        return listResponseResult;
    }

    public ListResponseResult getListResult(Object object, String status){
        ListResponseResult listResponseResult = new ListResponseResult();
        if (StringUtils.isEmpty(object)){
            listResponseResult.setData(null);
        } else {
            listResponseResult.setData(object);
        }
        listResponseResult.setStatus(status);
        listResponseResult.setSuccess(true);
        return listResponseResult;
    }
}
