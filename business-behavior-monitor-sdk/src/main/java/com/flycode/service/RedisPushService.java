package com.flycode.service;

import com.flycode.entity.LocalLogMessage;
import com.flycode.listener.MonitorLogListener;
import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author flycode
 * Redis实现发布订阅
 */
public class RedisPushService implements IPush {
    private final Logger logger = LoggerFactory.getLogger(RedisPushService.class);


    private RedissonClient redissonClient;

    @Override
    public void open(String host, int port) {
        if (null != redissonClient && !redissonClient.isShutdown()) {
            return;
        }
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + host + ":" + port)
                .setConnectionPoolSize(64)
                .setConnectionMinimumIdleSize(10)
                .setRetryAttempts(3)
                .setConnectTimeout(3000)
                .setPingConnectionInterval(1000)
                .setKeepAlive(true);

        this.redissonClient = Redisson.create(config);
        logger.info("连接Redis成功");
        RTopic topic = redissonClient.getTopic("business-behavior-monitor-sdk-topic");
        topic.addListener(LocalLogMessage.class, new MonitorLogListener());

    }

    @Override
    public void send(LocalLogMessage localLogMessage) {
        try {
            RTopic topic = redissonClient.getTopic("business-behavior-monitor-sdk-topic");
            topic.publish(localLogMessage);
        } catch (Exception e) {
            logger.error("发布订阅失败{}", e.getMessage());
        }

    }
}
