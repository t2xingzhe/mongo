package com.xing.tool;

import com.example.demo.single.SingletonClient;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/3 0003.
 */
public class BtTest {
    public static void main(String[] args) throws Exception{
        String id = "ddt-286";
        SingletonClient singletonClient = SingletonClient.getInstance();
        String magnet = id;

        HttpPost httpPost = new HttpPost("http://cnbtkitty.org/");
        httpPost.setHeader("User-agent","Baiduspider");
        List<NameValuePair> formParams = new ArrayList<>();
        formParams.add(new BasicNameValuePair("keyword", id));
        formParams.add(new BasicNameValuePair("hidden", "true"));
        UrlEncodedFormEntity entityPost = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
        httpPost.setEntity(entityPost);
        CloseableHttpResponse response = singletonClient.getHttpClient().execute(httpPost);
        String url = response.getFirstHeader("Location").getValue();
        HttpGet get = new HttpGet(new URI(url));
        get.setHeader("User-agent","Baiduspider");
        response = singletonClient.getHttpClient().execute(get);
        String content = EntityUtils.toString(response.getEntity(),"utf-8");
        if(StringUtils.isEmpty(content)){
            System.out.println("Empty!");
            System.exit(0);
        }
        Document document = Jsoup.parse(content);
        Elements elements = document.select("dl.list-con > dt > a");
        String title = elements.first().text();
        if(title.contains(id) || title.contains(id.toUpperCase())){
            String urlDetail = document.select("dd.option > span > a").first().attr("href");
            System.out.println(urlDetail);
            get.reset();
            get.setURI(new URI(urlDetail));
            get.setHeader("User-agent","Baiduspider");
            response = singletonClient.getHttpClient().execute(get);
            content = EntityUtils.toString(response.getEntity(),"utf-8");
            document = Jsoup.parse(content);
            magnet = document.select("dd.magnet > a").first().attr("href");
            System.out.println(magnet);
        }
    }
}
