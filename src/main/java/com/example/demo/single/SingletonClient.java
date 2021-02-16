package com.example.demo.single;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.util.EntityUtils;

import java.net.URI;

/**
 * Created by Administrator on 2018/2/22 0022.
 */
public class SingletonClient {
    private CloseableHttpClient httpClient = null;
    private CloseableHttpClient httpProxyClient = null;
    private HttpGet get = null;
    private SingletonClient(){
        httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(8000)
                .setConnectTimeout(8000)
                .build();
        get = new HttpGet();
        get.setConfig(requestConfig);
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String getContentWithProxy(String url){
        HttpHost proxy = new HttpHost("106.186.27.62", 80);
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
        httpProxyClient = HttpClients.custom()
                .setRoutePlanner(routePlanner)
                .build();
        return getContentProxy(url);
    }

    public String getContentProxy(String url){
        try {
            get.setURI(new URI(url));
            CloseableHttpResponse response = httpProxyClient.execute(get);
            HttpEntity entity = response.getEntity();
            if(response.getStatusLine().getStatusCode() != 200){
                System.out.println("status != 200 " + url);
            }
            return EntityUtils.toString(entity,"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setHeader(Header[] headers){
        get.setHeaders(headers);
    }

    public String getContent(String url){
        try {
            get.setURI(new URI(url));
            CloseableHttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            System.out.println("line: " + response.getStatusLine().toString());
            return EntityUtils.toString(entity,"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getContent4Ketty(String url){
        try {
            get.setURI(new URI(url));
            get.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            get.setHeader("Cookie","__cfduid=ddd3d53928860abd444a2a5a1a40c8e9d1525531929; UM_distinctid=16330c9dcde747-05578bfe26f2a3-3c3c5905-1fa400-16330c9dcdf1072; Hm_lvt_f75b813e9c1ef4fb27eaa613c9f307b2=1525531926,1525582201; Hm_lvt_6eb26205c9e475cb419dfd4e70935911=1525531926,1525582201; CNZZDATA1261857827=66866560-1525527469-%7C1525585666; CNZZDATA1261841250=869123900-1525527714-%7C1525585171; __atuvc=1%7C19; cf_clearance=a13de52ced5e539522f9bad59a775e2ea1dfba4b-1527524967-1800");
            get.setHeader("Host","cnbtkitty.org");
            get.setHeader("Origin","http://cnbtkitty.org");
            get.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
            CloseableHttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            System.out.println("line: " + response.getStatusLine().toString());
            return EntityUtils.toString(entity,"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class SingletonHolder{
        static SingletonClient singletonClient = new SingletonClient();
    }

    public static SingletonClient getInstance(){
        return SingletonHolder.singletonClient;
    }
}
