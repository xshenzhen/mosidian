package com.mosidian.web.controller;



import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.mosidian.web.utils.Const;
import com.mosidian.web.utils.Logger;
import com.mosidian.web.utils.PageData;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;


public class BaseController {


    protected Logger logger = Logger.getLogger(this.getClass());


    private static final long serialVersionUID = 6357869213649815390L;


    public int pageIndex() {
        String pageNum = this.getRequest().getParameter("pageNum");
        if (StrUtil.isEmpty(pageNum)){
            return 1;
        }else {
           return Convert.toInt(pageNum);
        }
    }

    public int pageSize() {
       // int pz = Convert.toInt(this.getRequest().getParameter("pageSize")); pz > 0 ? pz :
        return 20;

    }




    /**
     * new PageData对象
     *
     * @return
     */
    public PageData getPageData() {
        return new PageData(this.getRequest());
    }

    /**
     * 得到ModelAndView
     *
     * @return
     */
    public ModelAndView getModelAndView() {
        return new ModelAndView();
    }

    /**
     * 得到request对象
     *
     * @return
     */
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }



    public static void logBefore(Logger logger, String interfaceName) {
        logger.info("");
        logger.info("start");
        logger.info(interfaceName);
    }

    public static void logAfter(Logger logger) {
        logger.info("end");
        logger.info("");
    }

    /**
     * 获取参数的表达式
     *
     * @param pd
     * @param key    para
     * @param isLike 是否是模糊查询
     */
    public void SetFieldExpression(PageData pd, String key, String para, boolean isLike) {
        if (isLike) {
            if (key != null && key.length() > 0) {
                key = "%" + key + "%";
                pd.put(para, key);
            }
        } else {
            if (key != null && key.length() > 0) {
                pd.put(para, key);
            }
        }
    }

    public void SetFieldExpressionEx(PageData pd, String key, String para, String field) {
        if (key != null && key.length() > 0) {
            key = field + " like '%," + key + ",%' or " + field + " like '%," + key + "' or " + field + " like '%" + key + "%' or " + field + " like '" + key + ",%'";
            pd.put(para, key);
        }
    }



    /**
     * @param message 成功信息
     * @return
     */
    public Object success(String message) {
        return this.success(null, message, null);
    }

    /**
     * @param message 成功信息
     * @return
     */
    public ModelAndView successView(String message) {
        return this.successView(null, message, null);
    }

    public ModelAndView successView(Object data) {
        return this.successView(data, "success", null);
    }

    /**
     * @param message  成功信息
     * @param redirict 跳转链接
     * @return
     */
    public Object success(String message, String redirict) {
        return this.success(null, message, redirict);
    }

    /**
     * @param message  成功信息
     * @param redirict 跳转链接
     * @return
     */
    public ModelAndView successView(String message, String redirict) {
        return this.successView(null, message, redirict);
    }


    /**
     * @param data     返回数据
     * @param message  成功信息
     * @param redirect 跳转链接
     * @return
     */
    public Object success(Object data, String message, String redirect) {
        redirect = (redirect == null || redirect.equals("")) ? "" : redirect;

        HashMap<String, Object> mData = new HashMap<String, Object>();

        mData.put("info", message);
        mData.put("status", 1);
        mData.put("url", redirect);
        mData.put("referer", redirect);
        mData.put("state", "success");

        if (data != null) {
            mData.put("data", data);
        }

        return mData;
    }

    /**
     * @param data     返回数据
     * @param message  成功信息
     * @param redirect 跳转链接
     * @return
     */
    public ModelAndView successView(Object data, String message, String redirect) {
        ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
        redirect = (redirect == null || redirect.equals("")) ? "" : redirect;
        mv.addObject("info", message);
        mv.addObject("status", 1);
        mv.addObject("url", redirect);
        mv.addObject("referer", redirect);
        mv.addObject("state", "success");
        if (data != null) {
            mv.addObject("data", data);
        }
        return mv;
    }

    /**
     * @param
     * @return
     */
    public Object errorPermission() {
        return this.error(null, "暂无授权访问", null);
    }

    /**
     * @param message 错误信息
     * @return
     */
    public Object error(String message) {
        return this.error(null, message, null);
    }


    /**
     * @param message 错误信息
     * @return
     */
    public ModelAndView errorView(String message) {
        return this.errorView(null, message, null);
    }

    /**
     * @param message  错误信息
     * @param redirect 跳转链接
     * @return
     */
    public Object error(String message, String redirect) {
        return this.error(null, message, redirect);
    }

    /**
     * @param message  错误信息
     * @param redirect 跳转链接
     * @return
     */
    public ModelAndView errorView(String message, String redirect) {
        return this.errorView(null, message, redirect);
    }

    /**
     * @param data     返回数据
     * @param message  错误信息
     * @param redirect 跳转链接
     * @return
     */
    public Object error(Object data, String message, String redirect) {
        redirect = (redirect == null || redirect.equals("")) ? "" : redirect;
        HashMap<String, Object> mData = new HashMap<String, Object>();
        mData.put("info", message);
        mData.put("status", "0");
        mData.put("url", redirect);
        mData.put("referer", redirect);
        mData.put("state", "fail");

        if (data != null) {
            mData.put("data", data);
        } else {
            mData.put("data", "{}");
        }

        return mData;
    }

    /**
     * @param data     返回数据
     * @param message  错误信息
     * @param redirect 跳转链接
     * @return
     */
    public ModelAndView errorView(Object data, String message, String redirect) {

        redirect = (redirect == null || redirect.equals("")) ? "" : redirect;
        ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
        mv.addObject("info", message);
        mv.addObject("status", "0");
        mv.addObject("url", redirect);
        mv.addObject("referer", redirect);
        mv.addObject("state", "fail");

        if (data != null) {
            mv.addObject("data", data);
        } else {
            mv.addObject("data", "{}");
        }
        return mv;
    }

    /**
     * @param errors 错误信息
     * @return
     */
    public Object error(List<FieldError> errors) {
        return this.error(null, errors, null);
    }

    /**
     * @param errors   错误信息
     * @param redirect 跳转链接
     * @return
     */
    public Object error(List<FieldError> errors, String redirect) {
        return this.error(null, errors, redirect);
    }

    /**
     * @param data     返回数据
     * @param errors   错误信息
     * @param redirect 跳转链接
     * @return
     */
    public Object error(Object data, List<FieldError> errors, String redirect) {
        String message = "";
        HashMap<String, Object> mErrors = null;
        if (errors.size() > 0) {
            message = errors.get(0).getDefaultMessage();
            mErrors = new HashMap<>();
            for (FieldError error : errors) {
                mErrors.put(error.getField(), error.getDefaultMessage());
            }
        }
        redirect = (redirect == null || redirect.equals("")) ? "" : redirect;
        HashMap<String, Object> mData = new HashMap<String, Object>();
        mData.put("info", message);
        mData.put("status", "0");
        mData.put("url", redirect);
        mData.put("referer", redirect);
        mData.put("state", "fail");
        if (mErrors != null) {
            mData.put("errors", mErrors);
        }

        if (data != null) {
            mData.put("data", data);
        }

        return mData;
    }

    /**
    * @Description: 增删改操作，提示信息
    * @Param:  [type: 1：增加；2：删除；3：修改；state：操作状态]
    * @return:
    * @Author: 邢深圳
    * @Date: 2020-01-14 17:01
    */
    public  Object getMessage(int type,boolean state){
        switch (type){
            case 1:
                if (state){
                    return this.success(Const.ADDSUCCESS);
                }else {
                    return this.error(Const.ADDFAILURE);
                }
            case 2:
                if (state){
                    return this.success(Const.DELETESUCCESS);
                }else {
                    return this.error(Const.DELETEFAILURE);
                }
            case 3:
                if (state){
                    return this.success(Const.UPDATESUCCESS);
                }else {
                    return this.error(Const.UPDATEFAILURE);
                }
        }
        return null;
    }
}
