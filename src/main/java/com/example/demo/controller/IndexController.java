package com.example.demo.controller;

import com.example.demo.domain.Item;
import com.example.demo.single.SingletonClient;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/4/4 0004.
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {
    @RequestMapping(value = "/act")
    public ModelAndView act(ModelAndView modelAndView,@RequestParam(defaultValue = "1",required = false) String name,@RequestParam(defaultValue = "1",required = false) int page,@RequestParam(defaultValue = "3152",required = false,name = "id") String sid,@RequestParam(defaultValue = "maker",required = false,name = "article")String article){
        modelAndView.setViewName("index");
        List<Item> itemList = new ArrayList<>();
        String url = "";
        if(name.equals("show")){
            url = "http://www.dmm.co.jp/mono/dvd/-/list/=/article=actress/id="+sid+"/n1=DgRJTglEBQ4GpoD6,YyI,qs_/";
        }else{
            url = "http://www.dmm.co.jp/digital/videoa/-/list/=/article=maker/device=tv/id="+sid+"/limit=30/n1="+name+"/n2=DgRJTglEBQ4GpoD6,YyI,qs_/sort=release_date/";
        }

        System.out.println(url);
        SingletonClient singletonClient = SingletonClient.getInstance();
        String content = singletonClient.getContentWithProxy(url);
        Document document = Jsoup.parse(content);
        Elements elements = document.select("ul#list > li");
        for (Element element : elements) {
            Item item = new Item();
            String fan = element.select("p.tmb > a").first().attr("href");
            String title = element.select("span.txt").first().text();
            String id = wishFan(fan);
            if(!id.contains("-")){
                Pattern p = Pattern.compile("([a-z]*)(\\d*)");
                Matcher m = p.matcher(id);
                if(m.find()){
                    id = m.group(1) + "-" + m.group(2);
                }
            }
            if (!StringUtils.isEmpty(id) && !id.startsWith("h")) {
                System.out.println(title + " - " + id);
                String img = element.select("span.img>img").first().attr("src");
                img = img.replace("pt.jpg", "pl.jpg");
                item.setId(id);
                item.setImg(img);
                item.setTitle(title);
                itemList.add(item);
            }
        }
        modelAndView.addObject("page", page);
        modelAndView.addObject("id",sid);
        modelAndView.addObject("total",1);
        modelAndView.addObject("itemList", itemList);
        modelAndView.addObject("article", article);
        return modelAndView;
    }

    @RequestMapping(value = "/input2")
    public ModelAndView input2(ModelAndView modelAndView,String url,String sort) throws Exception{
        modelAndView.setViewName("input2");
        if(!StringUtils.isEmpty(url)){
            System.out.println("url is: " + url);

            if(!url.contains("sort") && !StringUtils.isEmpty(sort)){
                url = url + "sort=" + sort;
            }
            List<Item> itemList = new ArrayList<>();
            SingletonClient singletonClient = SingletonClient.getInstance();
            String content = singletonClient.getContentWithProxy(url);
            Document document = Jsoup.parse(content);
            Elements elements = document.select("ul#list > li");
            for (Element element : elements) {
                Item item = new Item();
                String fan = element.select("p.tmb > a").first().attr("href");
                String title = element.select("span.txt").first().text();
                Elements acts = element.select("p.sublink > span > a");
                String act = "---";
                String actUrl = "";
                if(acts != null && acts.size()>0) {
                    act = acts.first().text();
                    actUrl = acts.first().attr("href");
                }
                String id = wishFan(fan);
                if(!id.contains("-")){
                    Pattern p = Pattern.compile("([a-z]*)(\\d*)");
                    Matcher m = p.matcher(id);
                    if(m.find()){
                        id = m.group(1) + "-" + m.group(2);
                    }
                }
                if (!StringUtils.isEmpty(id) && !id.startsWith("h")) {
                    String img = element.select("span.img>img").first().attr("src");
                    if(img.contains("pt.jpg")){
                        img = img.replace("pt.jpg", "pl.jpg");
                    }else if(img.contains("ps.jpg")){
                        img = img.replace("ps.jpg", "pl.jpg");
                    }
                    item.setId(id);
                    item.setImg(img);
                    item.setTitle(title);
                    item.setActUrl(getAct(actUrl));
                    item.setAct(act);
                    item.setsId(getId(actUrl));
                    itemList.add(item);
                }
            }
            modelAndView.addObject("itemList", itemList);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/input")
    public ModelAndView index2(ModelAndView modelAndView,String url,String sort) throws Exception{
        modelAndView.setViewName("input");
        if(!StringUtils.isEmpty(url)){
            System.out.println("url is: " + url);

            if(!url.contains("sort") && !StringUtils.isEmpty(sort)){
                url = url + "sort=" + sort;
            }
            List<Item> itemList = new ArrayList<>();
            SingletonClient singletonClient = SingletonClient.getInstance();
            String content = singletonClient.getContentWithProxy(url);
            Document document = Jsoup.parse(content);
            Elements elements = document.select("ul#list > li");
            for (Element element : elements) {
                Item item = new Item();
                String fan = element.select("p.tmb > a").first().attr("href");
                String title = element.select("span.txt").first().text();
                Elements acts = element.select("p.sublink > span > a");
                String act = "---";
                String actUrl = "";
                if(acts != null && acts.size()>0) {
                    act = acts.first().text();
                    actUrl = acts.first().attr("href");
                }
                String id = wishFan(fan);
                if(!id.contains("-")){
                    Pattern p = Pattern.compile("([a-z]*)(\\d*)");
                    Matcher m = p.matcher(id);
                    if(m.find()){
                        id = m.group(1) + "-" + m.group(2);
                    }
                }
                if (!StringUtils.isEmpty(id) && !id.startsWith("h")) {
                    String img = element.select("span.img>img").first().attr("src");
                    img = img.replace("pt.jpg", "pl.jpg");
                    item.setId(id);
                    item.setImg(img);
                    item.setTitle(title);
                    item.setActUrl(getAct(actUrl));
                    item.setAct(act);
                    item.setsId(getId(actUrl));
                    itemList.add(item);
                }
            }
            modelAndView.addObject("itemList", itemList);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/index")
    public ModelAndView index(ModelAndView modelAndView,@RequestParam(defaultValue = "1",required = false) int page,@RequestParam(defaultValue = "3152",required = false,name = "id") String sid,@RequestParam(defaultValue = "maker",required = false,name = "article")String article) throws Exception {
        modelAndView.setViewName("index");
        List<Item> itemList = new ArrayList<>();
        String url = "http://www.dmm.co.jp/digital/videoa/-/list/=/article="+article+"/limit=30/device=tv/id="+sid+"/sort=release_date/page="+page+"/";
        System.out.println(url);
        SingletonClient singletonClient = SingletonClient.getInstance();
        String content = singletonClient.getContentWithProxy(url);
        Document document = Jsoup.parse(content);
        Elements elements = document.select("ul#list > li");
        String total = document.select("li.terminal > a").get(1).attr("href");
        total = total.substring(total.indexOf("page=")+5,total.length()-1);
        for (Element element : elements) {
            Item item = new Item();
            String fan = element.select("p.tmb > a").first().attr("href");
            String title = element.select("span.txt").first().text();
            Elements acts = element.select("p.sublink > span > a");
            String act = "---";
            String actUrl = "";
            if(acts != null && acts.size()>0) {
                act = acts.first().text();
                actUrl = acts.first().attr("href");
            }
            String id = wishFan(fan);
            if(!id.contains("-")){
                Pattern p = Pattern.compile("([a-z]*)(\\d*)");
                Matcher m = p.matcher(id);
                if(m.find()){
                    id = m.group(1) + "-" + m.group(2);
                }
            }
            if (!StringUtils.isEmpty(id) && !id.startsWith("h")) {
                String img = element.select("span.img>img").first().attr("src");
                img = img.replace("pt.jpg", "pl.jpg");
                item.setId(id);
                item.setImg(img);
                item.setTitle(title);
                item.setActUrl(getAct(actUrl));
                item.setAct(act);
                item.setsId(getId(actUrl));
                itemList.add(item);
            }
        }
        modelAndView.addObject("page", page);
        modelAndView.addObject("id",sid);
        modelAndView.addObject("total",Integer.parseInt(total));
        modelAndView.addObject("itemList", itemList);
        modelAndView.addObject("article", article);
        return modelAndView;
    }

    private String getId(String url){
        if(StringUtils.isEmpty(url)){
            return "---";
        }
        url = url.substring(0,url.length()-9);
        String temp = url.substring(url.indexOf("id=")+3,url.lastIndexOf("/limit"));
        return temp;
    }

    private String getAct(String url){
        if(StringUtils.isEmpty(url)){
            return "---";
        }
        String temp = url.substring(url.indexOf("n1")+3,url.lastIndexOf("/sort"));
        return temp;
    }

    @RequestMapping(value = "/json")
    @ResponseBody
    public String json(String id) throws Exception {
        System.out.println("json:"+id);
        String result = printMagnet(id);
        System.out.println(result);
        return result;
    }

    private static String printMagnet(String id) throws Exception{
        String cook = "__cfduid=ddd3d53928860abd444a2a5a1a40c8e9d1525531929; UM_distinctid=16330c9dcde747-05578bfe26f2a3-3c3c5905-1fa400-16330c9dcdf1072; Hm_lvt_f75b813e9c1ef4fb27eaa613c9f307b2=1525531926,1525582201; Hm_lvt_6eb26205c9e475cb419dfd4e70935911=1525531926,1525582201; CNZZDATA1261857827=66866560-1525527469-%7C1525585666; CNZZDATA1261841250=869123900-1525527714-%7C1525585171; __atuvc=1%7C19; cf_clearance=4024bb35a27f9eb7c65e0e52686c45556e1d0490-1528123181-1800";
        String magnet = id;

        HttpPost httpPost = new HttpPost("http://cnbtkitty.org/");
        httpPost.setHeader("User-agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
        httpPost.setHeader("Cookie",cook);
        List<NameValuePair> formParams = new ArrayList<>();
        formParams.add(new BasicNameValuePair("keyword", id));
        formParams.add(new BasicNameValuePair("hidden", "true"));
        UrlEncodedFormEntity entityPost = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
        httpPost.setEntity(entityPost);
        CloseableHttpResponse response = HttpClients.createDefault().execute(httpPost);
        if(response.getStatusLine().getStatusCode() != 302){
            System.out.println(response.getStatusLine().getStatusCode());
            return "";
        }
        String url = response.getFirstHeader("Location").getValue();
        System.out.println("磁力链接url=" + url);

        HttpGet get = new HttpGet(new URI(url));
        get.setHeader("User-agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
        get.setHeader("Cookie",cook);
        response = HttpClients.createDefault().execute(get);

        String content = EntityUtils.toString(response.getEntity(),"utf-8");
        if(StringUtils.isEmpty(content) || content.contains("没有找到与关键词")){
            System.out.println("Empty!没有");
            return "";
        }
        Document document = Jsoup.parse(content);
        Elements elements = document.select("dl.list-con");
        System.out.println("总共记录数" + elements.size());
        for(Element element:elements) {
            String title = element.select("dt > a").first().text();
            System.out.println(title);
            if ((title.contains(id) || title.contains(id.toUpperCase())) &&  !title.contains("第一會所") && !title.contains("sis001") && !title.contains("第一会所") && !title.contains("SIS001") && !title.contains("@")) {
                String urlDetail = element.select("dd.option > span > a").first().attr("href");
                System.out.println(urlDetail);
                get.reset();
                get.setURI(new URI(urlDetail));
                get.setHeader("User-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
                get.setHeader("Cookie",cook);
                response = HttpClients.createDefault().execute(get);
                content = EntityUtils.toString(response.getEntity(), "utf-8");
                if(!content.contains("第一會所") && !content.contains("草榴")) {
                    document = Jsoup.parse(content);
                    magnet = document.select("dd.magnet > a").first().attr("href");
                    return magnet;
                }
            }
        }
//        response.close();
//        String content = singletonClient.getContent4Ketty(url);
//        Document document = Jsoup.parse(content);
//        Elements elements = document.select("dd.option");
//        for(Element element:elements) {
//            String size = element.getElementsByTag("span").get(2).text();
//            if (size.contains("GB")) {
//                magnet = element.getElementsByTag("span").first().getElementsByTag("a").first().attr("href");
//                break;
//            }
//        }

//        String url = "http://www.btyunsou.co/search/"+id+"_ctime_1.html";
//        String magnet = id;
//
//        String content = singletonClient.getContent(url);
//        Document document = Jsoup.parse(content);
//        Elements elements = document.select("div.media-body");
//        for(Element element:elements){
//            String size = element.select("span.label.label-warning").text();
//            if(size.contains("GB")) {
//                magnet = "magnet:?xt=urn:btih:" + element.select("h4 > a.title").first().attr("href").replace("/", "").replace(".html", "");
//                break;
//            }
//        }
        return magnet;
    }

    private static String wishFan(String fan) {
        fan = fan.replace("h_","");
        String f = fan.substring(fan.indexOf("cid") + 4, fan.length() - 1);
        if(f.contains("/")){
            f = f.substring(0,f.indexOf("/"));
        }
        Pattern p = Pattern.compile("\\d*([a-z]*\\d*).*");
        Matcher m = p.matcher(f);
        if (m.find()) {
            return m.group(1).replaceFirst("00", "-");
        } else {
            System.out.println("番号识别失败: " + f);
        }
        return "";
    }
}
