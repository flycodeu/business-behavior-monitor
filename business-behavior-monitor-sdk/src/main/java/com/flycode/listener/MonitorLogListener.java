package com.flycode.listener;

import com.alibaba.fastjson2.JSON;
import com.flycode.entity.LocalLogMessage;
import org.redisson.api.listener.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Redis监听器
 *
 * @author flycode
 */
public class MonitorLogListener implements MessageListener<LocalLogMessage> {
    private final Logger logger = LoggerFactory.getLogger(MonitorLogListener.class);

    @Override
    public void onMessage(CharSequence charSequence, LocalLogMessage logMessage) {
//        logger.info("接受消息{}", JSON.toJSONString(logMessage));，不要使用这个logger，会引起无线递归
        System.out.println("接受消息: " + JSON.toJSONString(logMessage));
    }
}
