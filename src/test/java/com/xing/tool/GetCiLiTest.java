package com.xing.tool;

import com.example.demo.single.SingletonClient;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.*;

/**
 * Created by Administrator on 2019/7/20 0020.
 */
public class GetCiLiTest {
    public static void main(String[] args) {
        File file = new File("F:\\115download");
        String[] fileList = file.list();
        Set<String> has = new HashSet<>();
        for(String list:fileList){
            if(list.trim().indexOf("[") == 0){
                has.add(list.trim());
            }
        }
        SingletonClient singletonClient = SingletonClient.getInstance();
        Map<String,String> down = new HashMap<>();
        for(int i=21;i<=40;i++) {
            singletonClient.setHeader(getHeader());
            String content = singletonClient.getContent("https://btdb.cilimm.xyz/list/%E9%BB%91%E6%9A%97%E6%8E%83%E5%9C%96/"+i+"/time_d");
            Document document = Jsoup.parse(content);
            Elements elements = document.select("div.main > ul.mlist > li>div.T1>a");
            for(Element element:elements){
                String title = element.text();
                String handleTitle = "";
                if(title.indexOf("[中国翻訳]")>0) {
                    handleTitle = title.substring(0, title.indexOf("[中国翻訳]")).trim();
                }
                if(handleTitle.indexOf("[Chinese]")>0) {
                    handleTitle = handleTitle.substring(0, handleTitle.indexOf("[Chinese]")).trim();
                }
                handleTitle = handleTitle.replace("(成年コミック)","").trim();
                String url = "https://btdb.cilimm.xyz/" +   element.attr("href");
                boolean had = false;
                for(String list:has){
                    if(list.contains(handleTitle)){
                        had = true;
                        break;
                    }
                }
                if(had){
                    System.out.println("已有:" + title);
                }else{
                    System.out.println("可下:" + title);
                    has.add(title);
                    down.put(handleTitle, url);
                }
            }
        }
        for(String key:down.keySet()){
            System.out.println(key);
        }
        for(String key:down.keySet()){
            System.out.println(down.get(key));
        }
    }

    public static Header[] getHeader(){
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3"));
        headers.add(new BasicHeader("cookie","__cfduid=d1d822478b02661070b220388c9de928e1563611603; __cfduid=d7986e050637180351408436bbaebf3e91563611602"));
        headers.add(new BasicHeader("referer","https://btdb.cilimm.xyz/list/%E9%BB%91%E6%9A%97%E6%8E%83%E5%9C%96/6/time_d"));
        headers.add(new BasicHeader("upgrade-insecure-requests","1"));
        headers.add(new BasicHeader("user-agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36"));
        Header[] list = new Header[headers.size()];
        return headers.toArray(list);
    }
}
