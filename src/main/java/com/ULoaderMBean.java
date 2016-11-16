package com;

import java.io.IOException;

/**
 * Created by Main Server on 16.11.2016.
 */
public interface ULoaderMBean {

    String getUrlStr();

    void setUrlStr(String urlStr);

    String getFileStr();

    void setFileStr(String fileStr);

    public void downloadFile(String urlStr, String fileStr) throws IOException;

    public String getFileName(String fileName);

}
