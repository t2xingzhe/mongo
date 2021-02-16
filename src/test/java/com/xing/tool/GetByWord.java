package com.xing.tool;

import com.example.demo.single.SingletonClient;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/20 0020.
 */
public class GetByWord {
    public static void main(String[] args) throws Exception{
//        printMagnet("91大神",10);

    }

    private static String printMagnet(String id,int page) throws Exception{
        SingletonClient singletonClient = SingletonClient.getInstance();
        String magnet = id;

        HttpPost httpPost = new HttpPost("http://cnbtkitty.org/");
        httpPost.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpPost.setHeader("Cookie","__cfduid=ddd3d53928860abd444a2a5a1a40c8e9d1525531929; UM_distinctid=16330c9dcde747-05578bfe26f2a3-3c3c5905-1fa400-16330c9dcdf1072; Hm_lvt_f75b813e9c1ef4fb27eaa613c9f307b2=1525531926,1525582201; Hm_lvt_6eb26205c9e475cb419dfd4e70935911=1525531926,1525582201; CNZZDATA1261857827=66866560-1525527469-%7C1525585666; CNZZDATA1261841250=869123900-1525527714-%7C1525585171; __atuvc=1%7C19");
        httpPost.setHeader("Host","cnbtkitty.org");
        httpPost.setHeader("Origin","http://cnbtkitty.org");
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
        List<NameValuePair> formParams = new ArrayList<>();
        formParams.add(new BasicNameValuePair("keyword", id));
        formParams.add(new BasicNameValuePair("hidden", "true"));
        UrlEncodedFormEntity entityPost = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
        httpPost.setEntity(entityPost);
        CloseableHttpResponse response = singletonClient.getHttpClient().execute(httpPost);
        String url = response.getFirstHeader("Location").getValue();
        System.out.println("磁力链接url=" + url);
        response.close();
        if(page != 1){
            url = url.split("1/0/0")[0];
            for(int i=1;i<=page;i++) {
                String urls = url + i + "/0/0.html";
                System.out.println(urls);
                String content = singletonClient.getContent4Ketty(urls);
                Document document = Jsoup.parse(content);
                Elements elements = document.select("dd.option");
                for (Element element : elements) {
                    String size = element.getElementsByTag("span").get(2).text();
                    if (size.contains("GB") || size.contains("MB")) {
                        magnet = element.getElementsByTag("span").first().getElementsByTag("a").first().attr("href");
                        System.out.println(magnet);
                    }
                }
            }
        }

        return magnet;
    }

    private static String printMagnet(String id) throws Exception{
        SingletonClient singletonClient = SingletonClient.getInstance();
        String magnet = id;

        HttpPost httpPost = new HttpPost("http://cnbtkitty.org/");
        httpPost.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpPost.setHeader("Cookie","__cfduid=ddd3d53928860abd444a2a5a1a40c8e9d1525531929; UM_distinctid=16330c9dcde747-05578bfe26f2a3-3c3c5905-1fa400-16330c9dcdf1072; Hm_lvt_f75b813e9c1ef4fb27eaa613c9f307b2=1525531926,1525582201; Hm_lvt_6eb26205c9e475cb419dfd4e70935911=1525531926,1525582201; CNZZDATA1261857827=66866560-1525527469-%7C1525585666; CNZZDATA1261841250=869123900-1525527714-%7C1525585171; __atuvc=1%7C19");
        httpPost.setHeader("Host","cnbtkitty.org");
        httpPost.setHeader("Origin","http://cnbtkitty.org");
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
        List<NameValuePair> formParams = new ArrayList<>();
        formParams.add(new BasicNameValuePair("keyword", id));
        formParams.add(new BasicNameValuePair("hidden", "true"));
        UrlEncodedFormEntity entityPost = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
        httpPost.setEntity(entityPost);
        CloseableHttpResponse response = singletonClient.getHttpClient().execute(httpPost);
        String url = response.getFirstHeader("Location").getValue();
        System.out.println("磁力链接url=" + url);
        response.close();
        String content = singletonClient.getContent4Ketty(url);
        Document document = Jsoup.parse(content);
        Elements elements = document.select("dd.option");
        for(Element element:elements) {
            String size = element.getElementsByTag("span").get(2).text();
            if (size.contains("GB")|| size.contains("MB")) {
                magnet = element.getElementsByTag("span").first().getElementsByTag("a").first().attr("href");
                System.out.println(magnet);
            }
        }

        return magnet;
    }
}
