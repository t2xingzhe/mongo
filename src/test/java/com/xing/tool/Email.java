package com.xing.tool;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by Administrator on 2018/6/19 0019.
 */
public class Email {
    public static void main(String[] args) {
        String content = "[アンソロジー] 連鎖病棟 [中国翻訳] [風與黑暗掃圖].zip";
        System.out.println(content.substring(0,content.indexOf("[中国翻訳]")));
    }
}
