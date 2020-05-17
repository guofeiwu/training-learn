package com.crawer.demo.webmagic;

import com.crawer.demo.service.GlobalVarManager;
import org.jsoup.Jsoup;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020/5/17 9:41 下午
 * @since v3.0
 */


public class MyWeatherPageProcessor implements PageProcessor {
    /**
     * // http://www.weather.com.cn/weather1d/101210101.shtml#input 杭州
     * // http://forecast.weather.com.cn/town/weather1dn/101211005009.shtml#input 上余镇
     * // http://www.weather.com.cn/weather1d/101211005.shtml#input 江山
     * // http://www.weather.com.cn/weather1d/101211001.shtml#input 衢州
     * // http://www.weather.com.cn/weather1d/101240302.shtml#input 鄱阳
     * // http://forecast.weather.com.cn/town/weather1dn/101211005004.shtml#input
     *
     * @param page
     */

    private Site site = Site.me()
            .setCharset("utf-8")
//            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36")
//            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\n")
//            .addHeader("Accept-Encoding", "gzip, deflate")
//            .addHeader("Cookie", "show_flex=show_flex; UM_distinctid=17222d2dfa0485-0969a1990c960b-396f7f07-13c680-17222d2dfa15d; f_city=%E6%9D%AD%E5%B7%9E%7C101210101%7C; cityListCmp=%E5%8C%97%E4%BA%AC-101010100-20200517%7C%E4%B8%8A%E6%B5%B7-101020100-20200518%7C%E5%B9%BF%E5%B7%9E-101280101-20200519%7C%E6%B7%B1%E5%9C%B3-101280601-20200520%2Cdefault%2C20200517; Hm_lvt_080dabacb001ad3dc8b9b9049b36d43b=1589722145,1589722207; Wa_lvt_1=1589722142,1589722145,1589722207,1589722207; CNZZDATA1278586247=457006941-1589721545-null%7C1589721545; vjuids=-133ec758f4.17222d80d5d.0.29fa2ea6902f2; vjlast=1589722484.1589722484.30; cityListHty=101271409%7C101210114%7C101230201%7C101030100%7C101240101%7C101240302%7C101211001%7C101210101%7C101190101%7C101211005%7C101010100%7C101020100%7C101280101%7C101280601%7C101010300; Hm_lpvt_080dabacb001ad3dc8b9b9049b36d43b=1589726038; Wa_lpvt_1=1589726039\n")
//            .addHeader("Upgrade-Insecure-Requests", "1")
//            .addHeader("Host","forecast.weather.com.cn")
            .setRetryTimes(3)
            .setSleepTime(100);


    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        System.out.println(html);
        String minTemp = Jsoup.parse(html.css("div#minTempDiv").toString()).text();
        String maxTemp = Jsoup.parse(html.css("div#maxTempDiv").toString()).text();
        Map<String, Object> map = new HashMap<>();
        map.put("minTemp", minTemp);
        map.put("maxTemp", maxTemp);
        String weather = Jsoup.parse(html.css("div.todayLeft div.weather").toString()).text();
        map.put("weather", weather);
        page.putField("weatherInfo", map);

        // System.out.println(html);
    }

    @Override
    public Site getSite() {
        return site;
    }
}
