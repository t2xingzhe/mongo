package com.xing.tool;

import com.example.demo.single.SingletonClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/7/20 0020.
 */
public class CiLiTest {
    public static void main(String[] args) {
        SingletonClient singletonClient = SingletonClient.getInstance();
        singletonClient.setHeader(GetCiLiTest.getHeader());
        List<String> list = perList();
        List<String> magnet = new ArrayList<>();
        for(String line:list){
            String content = singletonClient.getContent(line);
            Document document = Jsoup.parse(content);
            String href = document.select("div.main > dl.BotInfo > p > a").first().attr("href");
            magnet.add(href);
        }
        for(String mag:magnet){
            System.out.println(mag);
        }
    }

    private static List<String> perList(){
        List<String> list = new ArrayList<>();
        File file = new File("F:\\115download\\cili.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                list.add(tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return list;
    }
}
