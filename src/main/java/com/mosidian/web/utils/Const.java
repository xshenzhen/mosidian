package com.mosidian.web.utils;


/**
* @Description: 一些固定值；
* @Param:
* @return:
* @Author: 邢深圳
* @Date: 2020-01-12 15:58
*/
public class Const {
    @SuppressWarnings("all")

    //Redis-Token
    public static final String SESSION_USER_TOKEN_KEY="userToken";
    public static final String SESSION_MEMBER_TOKEN_KEY="memberToken";
    public static final String MEMBER_NO_PREFIX_NAME="MSIC";
    //uploadfiles路径
    public static final String PRODUCTFILE = "uploadfiles/product/";         //用户头像（每个作品集为一个文件夹）
    public static final String PRODUCTCATEGORY = "uploadfiles/productcategory/";         //用户头像（每个作品集为一个文件夹）
    public static final String BRAND = "uploadfiles/brand/";
    public static final String PRODUCTGOODS = "uploadfiles/productgoods/";
    public static final String ACTIVITY = "uploadfiles/activity/";
    //请求成功统一返回信息
    public static final int    ADDTYPE=1;
    public static final int    REMOVETYPE=2;
    public static final int    UPDATETYPE=3;
    public static final String ADDSUCCESS = "添加成功";
    public static final String DELETESUCCESS = "删除成功";
    public static final String UPDATESUCCESS = "操作成功";
    //请求失败统一返回信息
    public static final String ADDFAILURE = "添加失败";
    public static final String DELETEFAILURE = "删除失败";
    public static final String UPDATEFAILURE = "操作失败";
    //参数错误
    public static final String PARAMERROR = "参数错误";

    public interface RedisCache{

        int REDIS_SESSION_EXTIME = 60* 60 * 24 * 7; //一周时间
        int REDIS_SESSION_EXTIME_DAY = 60* 60 * 24; //一天时间
        int REDIS_SESSION_EXTIME_TWENTY_THREE_H = 60* 60 * 23; //一天时间

        int REDIS_SESSION_EXTIME_ONE = 60* 60 ; //一小时间
        int REDIS_SESSION_EXTIME_ONE_X = 60* 5 ; //5min
        int REDIS_WEIXIN_EXPIRES_IN=60*120-10;//
        int REDIS_EXPIRE_MINUTES=60;//1min
        int REDIS_EXPIRE_MIN=6;//6s

    }


}
