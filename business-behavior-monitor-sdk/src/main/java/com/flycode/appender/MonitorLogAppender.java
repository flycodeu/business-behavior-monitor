package com.flycode.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.flycode.entity.LocalLogMessage;
import com.flycode.entity.RecordLogEntity;
import com.flycode.service.IPush;
import com.flycode.service.RedisPushService;
import org.springframework.core.log.LogMessage;

import java.util.Arrays;

/**
 * @author flycode
 * 日志监控组件SDK
 */
public class MonitorLogAppender<E> extends AppenderBase<E> {
    private final RecordLogEntity recordLogEntity = new RecordLogEntity();

    public void setSystemName(String systemName) {
        recordLogEntity.setSystemName(systemName);
    }

    public void setGroupId(String groupId) {
        recordLogEntity.setGroupId(groupId);
    }

    public void setHost(String host) {
        recordLogEntity.setHost(host);
    }

    public void setPort(int port) {
        recordLogEntity.setPort(port);
    }

    private final IPush push = new RedisPushService();

    @Override
    protected synchronized void append(E e) {
        // 开启推送
        push.open(recordLogEntity.getHost(), recordLogEntity.getPort());

        // 1. 只处理log日志
        if (e instanceof ILoggingEvent) {
            ILoggingEvent loggingEvent = (ILoggingEvent) e;

            String className = "unknown";
            String methodName = "unknown";
            // 2. 读取基本配置信息,例如类名、方法名
            StackTraceElement[] callerData = loggingEvent.getCallerData();
            if (callerData != null && callerData.length > 0) {
                StackTraceElement caller = callerData[0];
                className = caller.getClassName();
                methodName = caller.getMethodName();
            }

            // 3. 判断是否包含在指定工程里面
            if (!className.startsWith(recordLogEntity.getGroupId())) {
                return;
            }

            // 4. 获取其他日志信息
            String formattedMessage = ((ILoggingEvent) e).getFormattedMessage();
            LocalLogMessage logMessage = new LocalLogMessage(recordLogEntity.getSystemName(), className, methodName, Arrays.asList(formattedMessage.split(" ")));
            System.out.println("当前日志信息：" + logMessage);

            // 推送日志
            push.send(logMessage);
        }
    }
}
