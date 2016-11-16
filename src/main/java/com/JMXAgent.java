package com;

import javax.management.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.Properties;

/**
 * Created by Main Server on 16.11.2016.
 */
public class JMXAgent {
    public static void main(String[] args){
        try {
            MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
            ULoaderMBean uloaderMBean = new ULoader();
            ObjectName objectName = new ObjectName("test-mbean:name=first-mbean");
            platformMBeanServer.registerMBean(uloaderMBean, objectName);

            System.out.println("Default URL: " + uloaderMBean.getUrlStr());
            System.out.println("Default directory of destination: " + uloaderMBean.getFileStr());
            System.out.println("Waiting.....................");

            Thread.sleep(100000);

            System.out.println("==============================================================");
            System.out.println("Actual URL: " + uloaderMBean.getUrlStr());
            System.out.println("Actual directory of destination: " + uloaderMBean.getFileStr());

            String filename = uloaderMBean.getFileName(uloaderMBean.getUrlStr());
            String filepath = uloaderMBean.getFileStr();
            System.out.println("Please confirm file name: " + filename);
            System.out.println("Please enter YES or enter new file name with extension");
            System.out.println("Waiting for you option........................");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String reply = reader.readLine();
            reply.trim();
            if (reply.equals("YES")) {
                uloaderMBean.setFileStr(filepath + filename);
            } else {
                uloaderMBean.setFileStr(filepath + reply);
            }

            uloaderMBean.downloadFile(uloaderMBean.getUrlStr(), uloaderMBean.getFileStr());

        } catch (Exception e) {
            System.out.println("Something went wrong");
            System.out.println("Please try again");
            System.exit(0);
        }

    }
}
