package com.sparrow.util;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * @author 985492783@qq.com
 * @date 2023/11/6 19:39
 */
public class NetUtils {
    
    public static String getAddress() {
        InetAddress inetAddress = findFirstNonLoopbackAddress();
        if (inetAddress == null) {
            return "127.0.0.1";
        }
        return inetAddress.getHostAddress();
    }
    
    private static InetAddress findFirstNonLoopbackAddress() {
        InetAddress result = null;
        
        try {
            int lowest = Integer.MAX_VALUE;
            for (Enumeration<NetworkInterface> nics = NetworkInterface.getNetworkInterfaces();
                    nics.hasMoreElements(); ) {
                NetworkInterface ifc = nics.nextElement();
                if (ifc.isUp()) {
                    if (ifc.getIndex() < lowest || result == null) {
                        lowest = ifc.getIndex();
                    } else {
                        continue;
                    }
                    
                    for (Enumeration<InetAddress> addrs = ifc.getInetAddresses(); addrs.hasMoreElements(); ) {
                        InetAddress address = addrs.nextElement();
                        boolean isLegalIpVersion = address instanceof Inet4Address;
                        if (isLegalIpVersion && !address.isLoopbackAddress()) {
                            result = address;
                        }
                    }
                    
                }
            }
        } catch (Exception ignore) {
        }
        
        if (result != null) {
            return result;
        }
        
        try {
            return InetAddress.getLocalHost();
        } catch (UnknownHostException ignore) {
        }
        
        return null;
        
    }
}
