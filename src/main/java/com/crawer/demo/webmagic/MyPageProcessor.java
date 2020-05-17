package com.crawer.demo.webmagic;

import org.jsoup.Jsoup;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020/5/17 7:50 下午
 * @since v3.0
 */
public class MyPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    private List<Map<String, Object>> maps = new ArrayList<>();

    int i = 1;

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        List<Selectable> nodes = html.css("div#resultList div.el").nodes();

        if (nodes.size() == 0) {
            Map<String, Object> map = new HashMap<>();

            // 默认第一个
            String companyName = Jsoup.parse(html.css("div.cn p.cname a").toString()).text();

            map.put("companyName", companyName);


            String companyAddress = Jsoup.parse(html.css("div.bmsg").nodes().get(1).css("p.fp").toString()).text();
            map.put("companyAddress", companyAddress);
            // 页面
            String jobName = html.css("div.cn h1", "text").toString();
            // Jsoup.parse(html.toString()).select("div.cn h1").text();
            String salary = Jsoup.parse(html.toString()).select("div.cn strong").text();
            map.put("jobName", jobName);
            map.put("salary", salary);
            String jobInfo = Jsoup.parse(html.css("div.bmsg").nodes().get(0).css("p").all().toString()).text();
            map.put("jobInfo", jobInfo);
            String companyInfo = Jsoup.parse(html.css("div.tmsg").toString()).text();

            map.put("companyInfo", companyInfo);
            map.put("companyUrl", page.getUrl().toString());
            maps.add(map);
        } else {
            // 列表页面
            for (Selectable node : nodes) {
                String jobInfoUrl = node.links().toString();
                page.addTargetRequest(jobInfoUrl);
            }
            i++;
            if (i < 2) {
                // 只抓十页
                // 下一页
                String nextUrl = html.css("div.p_in li.bk").nodes().get(1).links().toString();
                System.out.println(nextUrl);
                page.addTargetRequest(nextUrl);
            }
        }

        page.putField("allInfo", maps);
    }

    @Override
    public Site getSite() {
        return site;
    }
}
