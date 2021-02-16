package com.xing.tool;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Administrator on 2020/6/21 0021.
 */
public class LegalTest {
    public static void main(String[] args) throws Exception{
        for(int i=1;i<=20;i++) {
            String content = getContent("d:\\best-videos-2019-"+i+".txt");
            Document document = Jsoup.parse(content);
            Elements elements = document.select("div.thumbnail-title.gradient > a");
            for (Element element : elements) {
//                System.out.println(element.text());
                System.out.println(getLastWord(element.text()));
            }
        }
    }

    private static String getLastWord(String input){
        input = input.substring(input.lastIndexOf(" ")+1,input.length());
        return input;
    }

    private static String getContent(String fileName) throws Exception{
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                sb.append(tempString);
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
        return sb.toString();
    }
}
