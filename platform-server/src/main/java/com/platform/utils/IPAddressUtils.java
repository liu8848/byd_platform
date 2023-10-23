package com.platform.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;

@Slf4j
public class IPAddressUtils {
    public static String getIpAddress(HttpServletRequest request){
        String ipAddress=request.getHeader("X-Forwarded-For");
        if(ipAddress==null|| ipAddress.isEmpty() ||"unknown".equalsIgnoreCase(ipAddress)){
            ipAddress=request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress==null||ipAddress.isEmpty()||"unknown".equalsIgnoreCase(ipAddress)){
            ipAddress=request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress==null||ipAddress.isEmpty()||"unknown".equalsIgnoreCase(ipAddress)){
            ipAddress=request.getRemoteAddr();
            if("127.0.0.1".equals(ipAddress)||"0:0:0:0:0:0:0:1".equals(ipAddress)){
                InetAddress inet=null;
                try{
                    inet=InetAddress.getLocalHost();
                }catch (Exception e){
                    log.error("根据网卡获取本机配置的IP异常=>,");
                }
                if(inet.getHostAddress()!=null){
                    ipAddress=inet.getHostAddress();
                }
            }
        }

        if(ipAddress!=null&&ipAddress.length()>15){
            if(ipAddress.indexOf(",")>0){
                ipAddress=ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
