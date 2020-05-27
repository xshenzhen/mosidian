package com.mosidian.web.common.redis;



public interface RedisService {

    /**
     * 写入缓存设置有效时间
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    boolean save(String key, String value, int expireTime);

    /**
     * 删除对应的value
     * @param key
     */
    void remove(String key);

    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    boolean exists(String key);

    /**
     * 读取缓存
     * @param key
     * @return
     */
    String get(String key);


}
