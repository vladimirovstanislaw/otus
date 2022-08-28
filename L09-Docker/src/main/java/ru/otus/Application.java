package ru.otus;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.net.InetAddress;
import java.net.UnknownHostException;


@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        com.sun.management.OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean)
                java.lang.management.ManagementFactory.getOperatingSystemMXBean();

        System.out.println("availableProcessors:" + Runtime.getRuntime().availableProcessors());
        System.out.println("TotalMemorySize, mb:" + os.getTotalMemorySize() / 1024 / 1024);
        System.out.println("maxMemory, mb:" + Runtime.getRuntime().maxMemory() / 1024 / 1024);
        System.out.println("freeMemory, mb:" + Runtime.getRuntime().freeMemory() / 1024 / 1024);

        InetAddress ip;
        String hostname;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            var ipIp = ip.getAddress().toString();
            System.out.println("ipIp:" + ipIp);
            System.out.println("Your current IP address : " + ip);
            System.out.println("Your current Hostname : " + hostname);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        new SpringApplicationBuilder().sources(Application.class).run(args);
    }
}
