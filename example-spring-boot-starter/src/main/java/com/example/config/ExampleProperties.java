package com.example.config;

import com.example.utils.IpUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.InetAddress;

/**
 * @author ouyangcm
 * create 2024/12/13 10:29
 */
@ConfigurationProperties(prefix = "example")
public class ExampleProperties {

    private String path;
    private InetAddress ip = IpUtils.getInetAddress();

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }
}
