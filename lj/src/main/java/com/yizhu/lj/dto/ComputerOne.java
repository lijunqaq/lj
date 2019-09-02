package com.yizhu.lj.dto;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Properties;

/**
 * 功能描述
 *
 * @author lijun
 * @date 2019/2/14
 */
public class ComputerOne {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 计算机名
     */
    private String computerName;

    /**
     * 计算机域名
     */
    private String userDomain;

    /**
     * 本地ip地址
     */
    private String ip;

    /**
     * 本地主机名
     */
    private String hostName;

    /**
     * JVM可以使用的总内存
     */
    private String totalMemory;
    /**
     * JVM可以使用的剩余内存
     */
    private String freeMemory;
    /**
     * JVM可以使用的处理器个数
     */
    private String availableProcessors;
    /**
     * 操作系统的版本
     */
    private String propertyVersion;
    /**
     * 操作系统的名称
     */
    private String propertyName;

    public ComputerOne(String userName, String ip) {
        this.userName = userName;
        this.ip = ip;
    }

    public ComputerOne() throws UnknownHostException {

        Runtime r = Runtime.getRuntime();
        Properties props = System.getProperties();
        InetAddress addr;
        addr = InetAddress.getLocalHost();
        String ip = addr.getHostAddress();
        Map<String, String> map = System.getenv();
        String userName = map.get("USERNAME");// 获取用户名
        String computerName = map.get("COMPUTERNAME");// 获取计算机名
        String userDomain = map.get("USERDOMAIN");// 获取计算机域名
        this.userName = userName;
        this.computerName = computerName;
        this.userDomain = userDomain;
        this.ip = ip;
        this.hostName = addr.getHostName();
        this.totalMemory = r.totalMemory()+"";
        this.freeMemory = r.freeMemory()+"";
        this.availableProcessors = r.availableProcessors()+"";
        this.propertyVersion =  props.getProperty("os.version");
        this.propertyName = props.getProperty("os.name");
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getUserDomain() {
        return userDomain;
    }

    public void setUserDomain(String userDomain) {
        this.userDomain = userDomain;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(String totalMemory) {
        this.totalMemory = totalMemory;
    }

    public String getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(String freeMemory) {
        this.freeMemory = freeMemory;
    }

    public String getAvailableProcessors() {
        return availableProcessors;
    }

    public void setAvailableProcessors(String availableProcessors) {
        this.availableProcessors = availableProcessors;
    }

    public String getPropertyVersion() {
        return propertyVersion;
    }

    public void setPropertyVersion(String propertyVersion) {
        this.propertyVersion = propertyVersion;
    }

    public String getPropertyName() {
        return propertyName;
    }


    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
}
