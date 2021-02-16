package com.xing.tool;

import com.example.demo.single.SingletonClient;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/6/21 0021.
 */
public class MangetTest {
    public static void main(String[] args) throws Exception{
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2,SSLv3");
//        File file = new File("d:\\fanffaa.txt");
//        BufferedReader reader = null;
//        try {
//            System.out.println("以行为单位读取文件内容，一次读一整行：");
//            reader = new BufferedReader(new FileReader(file));
//            String tempString = null;
//            while ((tempString = reader.readLine()) != null) {
//                String word = tempString;
//
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e1) {
//                }
//            }
//        }
        System.out.println(getMag("GP1072"));
    }

    private static String getMag(String word) throws Exception{
        String url = "https://geeg.xyz/search";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(8000)
                .setConnectTimeout(8000)
                .build();
        HttpPost get = new HttpPost();
        get.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        get.setURI(new URI(url));
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("key", word));
        urlParameters.add(new BasicNameValuePair("searchType", "0"));

        HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
        get.setEntity(postParams);
        get.setHeader("Content-Type","application/x-www-form-urlencoded");
        get.setHeader("Cookie","btcar_cpm_top_adcounter_0=1; btcar_cpm_bottom_adcounter_0=1; c3=53bd59dc84596ad98r4f; Hm_lvt_1953076d62ee35b7103b8d2e4473cc49=1592715240; _ga=GA1.2.1261747407.1592715240; _gid=GA1.2.24462418.1592715240; _gat_gtag_UA_156235729_2=1; btcar_cpc_adcounter_1=1; btcar_cpm_top_adcounter_1=1; btcar_cpm_bottom_adcounter_1=1; btcar_cpc_adcounter_0=2; t3=1592715261; __atuvc=2%7C26; __atuvs=5eeee7e7e30c9d44001; Hm_lpvt_1953076d62ee35b7103b8d2e4473cc49=1592715246");
        get.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36");
        get.setHeader("","");
        get.setHeader("","");
        get.setConfig(requestConfig);
        CloseableHttpResponse response = httpClient.execute(get);
        Header header = response.getFirstHeader("Location");
        System.out.println(header.getValue());
        return header.getValue();
    }
}
