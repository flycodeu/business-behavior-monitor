package com.flycode.entity;

/**
 * @author flycode
 * Appender需要提供的相关信息配置
 */
public class RecordLogEntity {
    private String systemName;
    /**
     * 只采集部分符合当前groupId的日志信息
     */
    private String groupId;
    /**
     * redis 地址
     */
    private String host;
    /**
     * redis 端口
     */
    private int port;

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
