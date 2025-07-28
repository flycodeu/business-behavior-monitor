package com.flycode.service;

import com.flycode.entity.LocalLogMessage;

/**
 * 发布订阅
 *
 * @author flycode
 */
public interface IPush {

    /**
     * 开启Redis连接
     * @param host
     * @param port
     */
    void open(String host,int port);

    /**
     * 发送日志
     * @param localLogMessage
     */
    void send(LocalLogMessage localLogMessage);
}
