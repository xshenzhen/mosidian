package com.mosidian.web.common.security;


/**
 * 封装API的错误码
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}