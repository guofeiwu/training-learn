package com.crawer.demo.webmagic;

import com.crawer.demo.service.GlobalVarManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.selector.Selectable;

/**
 * @Author Mason
 * @Description
 * @Date 2020/5/18 9:29
 **/
public class MyMoJiPageProcessor implements PageProcessor {

    private Site site = Site.me()
        .setCharset("utf-8")
        .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36")
        .addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;"
            + "v=b3;q=0.9")
        .addHeader(":path", "/api/redirect/1286")
//            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\n")
//            .addHeader("Accept-Encoding", "gzip, deflate")
//            .addHeader("Cookie", "show_flex=show_flex; UM_distinctid=17222d2dfa0485-0969a1990c960b-396f7f07-13c680-17222d2dfa15d; f_city=%E6%9D%AD%E5%B7%9E%7C101210101%7C; cityListCmp=%E5%8C%97%E4%BA%AC-101010100-20200517%7C%E4%B8%8A%E6%B5%B7-101020100-20200518%7C%E5%B9%BF%E5%B7%9E-101280101-20200519%7C%E6%B7%B1%E5%9C%B3-101280601-20200520%2Cdefault%2C20200517; Hm_lvt_080dabacb001ad3dc8b9b9049b36d43b=1589722145,1589722207; Wa_lvt_1=1589722142,1589722145,1589722207,1589722207; CNZZDATA1278586247=457006941-1589721545-null%7C1589721545; vjuids=-133ec758f4.17222d80d5d.0.29fa2ea6902f2; vjlast=1589722484.1589722484.30; cityListHty=101271409%7C101210114%7C101230201%7C101030100%7C101240101%7C101240302%7C101211001%7C101210101%7C101190101%7C101211005%7C101010100%7C101020100%7C101280101%7C101280601%7C101010300; Hm_lpvt_080dabacb001ad3dc8b9b9049b36d43b=1589726038; Wa_lpvt_1=1589726039\n")
//            .addHeader("Upgrade-Insecure-Requests", "1")
//            .addHeader("Host","forecast.weather.com.cn")
        .setRetryTimes(3)
        .setSleepTime(100);

    private JsonMapper jsonMapper = new JsonMapper();

    @Override
    public void process(Page page) {
        if (page.getUrl().toString().contains("/api/citysearch/")) {
            Json json = page.getJson();
            String s = json.toString();
            String cityId = "";
            try {
                JsonNode cityList = jsonMapper.readTree(s).get("city_list");
                if (cityList != null) {
                    JsonNode jsonNode = cityList.get(0);
                    if (jsonNode != null) {
                        cityId = jsonNode.get("cityId").toString();
                    }
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            if (cityId != null && !cityId.equals("")) {
                String request = "https://tianqi.moji.com/api/redirect/" + cityId;
                page.addTargetRequest(request);
            }
        }
        if (page.getUrl().toString().contains("/api/redirect/")) {
            Map<String, Object> map = new HashMap<>();
            Html html = page.getHtml();

            Document currentDoc = Jsoup.parse(html.css("div.wea_weather").toString());
            String currentTemp = currentDoc.select("em").text();
            map.put("currentTemp", currentTemp);
            String infoUptime = currentDoc.select("strong").text();
            map.put("infoUptime", infoUptime);
            List<Selectable> nodes = html.css("div.forecast ul").nodes();
            Document parse = Jsoup.parse(nodes.get(1).toString());
            Elements elements = Jsoup.parse(parse.toString()).select("img[src]");
            if (elements != null && elements.size() > 0) {
                String src = elements.get(0).attr("src");
                String alt = elements.get(0).attr("alt");
                map.put("src", src);
                map.put("alt", alt);
            }
            String text = Jsoup.parse(parse.select("li").get(2).toString()).text();
            if (text != null && !"".equals(text)) {
                String[] split = text.split("/");
                map.put("min", split[0]);
                map.put("max", split[1]);
            }

            String wind = Jsoup.parse(parse.select("li").get(3).toString()).text();
            map.put("wind", wind);
            String air = parse.select("li strong").text();
            map.put("air", air);
            GlobalVarManager.setGlobalVars("weatherInfo", map);
            page.putField("weatherInfo", map);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }
}
