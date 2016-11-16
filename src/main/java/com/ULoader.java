package com;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by Main Server on 16.11.2016.
 */
public class ULoader implements ULoaderMBean {

    private String urlStr;
    private String fileStr;

    public ULoader()  {
       Init();
    }

    public void Init(){
        try {
            FileInputStream fis;
            fis = new FileInputStream("src/main/resources/config.properties");
            Properties properties = new Properties();
            properties.load(fis);
            urlStr = properties.getProperty("urlStr");
            fileStr = properties.getProperty("fileStr");
        } catch (IOException e) {
            System.out.println("Init error........ ");
            System.out.println("Pleas try again");
            System.exit(0);
        }

    }

    public String getUrlStr() {
        return urlStr;
    }

    public void setUrlStr(String urlStr) {
        this.urlStr = urlStr;
    }

    public String getFileStr() {
        return fileStr;
    }

    public void setFileStr(String fileStr) {
        this.fileStr = fileStr;
    }


    public void downloadFile(String urlStr, String fileStr) {
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            System.out.println("URL address is wrong. File was not downloaded.");
            System.out.println("Please try again");
            System.exit(0);
           }
        File file = new File(fileStr);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error during creating of file");
                System.out.println("Please try again");
                System.exit(0);
            }
        } else {
            System.out.println("This file already downloaded");
            System.exit(0);
        }
        try (BufferedInputStream inputStream = new BufferedInputStream(url.openStream());
             FileOutputStream outputStream = new FileOutputStream(fileStr)) {
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = inputStream.read(buffer, 0, 1024)) != -1) {
                outputStream.write(buffer, 0, count);
            }

        } catch (IOException e) {
            System.out.println("File not found");
            System.out.println("Please try again");
            System.exit(0);
        }

        System.out.println("File was successfully downloaded");
        System.out.println("Final file  path: " + fileStr);

    }

    public String getFileName(String fileName) {

        if (fileName.lastIndexOf("/") != -1) return fileName.substring(fileName.lastIndexOf("/") + 1);

        else return "";
    }
}
