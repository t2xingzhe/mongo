package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.mongo.MongoDao;
import com.example.demo.mongo.Search;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {

	@Autowired
	private MongoDao mongoDao;

	@Test
	public void contextLoads() {
		Long start = System.currentTimeMillis();
		Search search = mongoDao.findById("5fffeadb9ade9e80af1a6750");
		Long end = System.currentTimeMillis();
		System.out.println(search.getCase_id() + " :" + (end-start));
		String json = JSON.toJSONString(search);
		System.out.println(System.currentTimeMillis()-end);
	}

	public static void main(String[] args) throws Exception {
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2,SSLv3");
		HttpHost proxy = new HttpHost("127.0.0.1", 1080);
		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
		CloseableHttpClient httpProxyClient = HttpClients.custom()
				.setRoutePlanner(routePlanner)
				.build();
		for(int i=1;i<=20;i++) {
			File file = new File("d:\\best-videos-2019-"+i+".txt");
			FileWriter fileWriter = new FileWriter(file,true);
			HttpGet get = new HttpGet();
			get.setURI(new URI("https://www.legalporno.com/best-videos/2019/page/"+i));
			CloseableHttpResponse response = httpProxyClient.execute(get);
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() != 200) {
				System.out.println("status != 200 ");
			}
			String content = EntityUtils.toString(entity, "utf-8");
			System.out.println(i);
			fileWriter.write(content);
			fileWriter.flush();
			fileWriter.close();
		}
	}

	private static String wishFan(String fan) {
		String f = fan.substring(fan.indexOf("cid") + 4, fan.length() - 1);
		if(f.contains("/")){
			f = f.substring(0,f.indexOf("/"));
		}
		Pattern p = Pattern.compile("\\d*([a-z]*\\d*)\\D*");
		Matcher m = p.matcher(f);
		if (m.find()) {
			return m.group(1).replaceFirst("00", "-");
		} else {
			System.out.println("番号识别失败: " + f);
		}
		return "";
	}
}
