package com.crawer.demo.service;

import com.crawer.demo.util.HttpUtil;
import com.crawer.demo.webmagic.MyMoJiPageProcessor;
import com.crawer.demo.webmagic.MyPageProcessor;
import com.crawer.demo.webmagic.MyPipeline;
import com.crawer.demo.webmagic.MyWeatherPageProcessor;
import com.crawer.demo.webmagic.MyWeatherPipeline;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020/5/17 3:11 下午
 * @since v3.0
 */
@Service
public class ItemService {

    public static final JsonMapper MAPPER = new JsonMapper();
    public static final String JOB_51_URL = "https://search.51job.com/list/000000,000000,0000,00,9,99,java,2,1.html?" +
        "lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99" +
        "&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&" +
        "line=&specialarea=00&from=&welfare=l";
    @Autowired
    private HttpUtil httpUtil;

    public Object getItem(int page, int s) {
        String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&wq=%E6%89%8B%E6%9C%BA&click=0&page=" + page + "&s=" + s;
        String content = httpUtil.doGet(url);

        Document parse = Jsoup.parse(content);

        Elements elements = parse.select("div#J_goodsList>ul>li");
        List<Map<String, Object>> maps = new ArrayList<>();
        for (Element element : elements) {
            String spu = element.attr("data-spu");
            System.out.println("spu:" + spu);
            Map<String, Object> map = new HashMap<>();
            map.put("spu", spu);

            Elements skus = element.select("li.ps-item");
            List<Map<String, Object>> items = new ArrayList<>();

            for (Element sku : skus) {
                Map<String, Object> m = new HashMap<>();
                String dataSku = sku.select("[data-sku]").attr("data-sku");
                m.put("dataSku", dataSku);
                String detailUrl = "https://item.jd.com/" + dataSku + ".html";
                m.put("detailUrl", detailUrl);
                String src = sku.select("[data-lazy-img]").attr("data-lazy-img");
                src = "http:" + src;
                m.put("src", src);
                // httpUtil.doGetImage(src);

                String detail = this.httpUtil.doGet(detailUrl);
                Document p = Jsoup.parse(detail);
                String title = p.select("div.sku-name").text();
                m.put("title", title);
                String priceDetail = "https://p.3.cn/prices/mgets?skuIds=J_" + dataSku;
                String prices = this.httpUtil.doGet(priceDetail);
                try {
                    m.put("price", MAPPER.readTree(prices).get(0).get("p").asDouble());
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                items.add(m);
            }
            map.put("items", items);
            maps.add(map);
        }

        return maps;
    }


    public Object webMagic() {

        Spider.create(new MyPageProcessor())
            .addUrl(JOB_51_URL)
            .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000)))
            .addPipeline(new MyPipeline())
            .thread(5)
            .run();
        return GlobalVarManager.getGlobalVars("jobInfo");


    }

    public Object weather() {
        String town = "http://forecast.weather.com.cn/town/weather1dn/101240302001.shtml#input";
        town = "http://forecast.weather.com.cn/town/weather1dn/101240302006.shtml#input";
        String city = "http://www.weather.com.cn/weather1d/101210101.shtml#input";
        Spider.create(new MyWeatherPageProcessor())
            .addUrl(town)
            .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(1000)))
            .addPipeline(new MyWeatherPipeline())
            .thread(5)
            .run();
        return GlobalVarManager.getGlobalVars("weatherInfo");
    }

    public Object weather(String cityName) {
        String moji = "https://tianqi.moji.com/api/citysearch/" + cityName;
        Spider.create(new MyMoJiPageProcessor())
            .addUrl(moji)
            .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(1000)))
            .addPipeline(new MyWeatherPipeline())
            .thread(5)
            .run();
        return GlobalVarManager.getGlobalVars("weatherInfo");
    }
}
