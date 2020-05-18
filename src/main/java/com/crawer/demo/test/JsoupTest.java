package com.crawer.demo.test;

import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 * @Author Mason
 * @Description
 * @Date 2020/5/18 15:27
 **/
public class JsoupTest {

    String html = "<html><head><title>First parse</title></head>"
        + "<body><p>Parsed HTML into a doc.</p></body></html>";


    @Test
    public void test() throws IOException {

        File input = new File("C:\\Users\\Mason\\Desktop\\test.html");
        Document doc = Jsoup.parse(input, "UTF-8");

        Element skin = doc.getElementById("skin");
        System.out.println(skin);
        Elements a = skin.getElementsByTag("a");
        System.out.println(a);

        String attr = skin.attr("data-data");
        System.out.println(attr);
        System.out.println("---------------------------");

        Elements select = doc.select("div[data-data]");
        for (Element element : select) {
            System.out.println(element.toString());
        }
        System.out.println("---------------------------");

        Elements srcs = doc.select("img[src]");
        for (Element element : srcs) {
            // System.out.println(element.toString());
        }

        System.out.println("---------------------------");
        Attributes attributes = skin.attributes();
        for (Attribute attribute : attributes) {
            System.out.println(attribute.getKey()+"==="+attribute.getValue());
        }

        System.out.println(skin.className());
        System.out.println("---------------------------aaa");
        Elements elements = doc.select("[^data-]");
        for (Element element : elements) {
             System.out.println(element.toString());
        }

        Whitelist basic = Whitelist.basic();
        Whitelist whitelist = Whitelist.basicWithImages();
        Whitelist relaxed = Whitelist.relaxed();
        Whitelist simpleText = Whitelist.simpleText();
    }



}
