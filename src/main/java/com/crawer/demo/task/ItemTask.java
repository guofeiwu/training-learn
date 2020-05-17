package com.crawer.demo.task;

import com.crawer.demo.util.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020/5/17 2:41 下午
 * @since v3.0
 */
@Component
public class ItemTask {


    @Autowired
    private HttpUtil httpUtil;

    // @Scheduled(fixedDelay = 10 * 1000)
    public void itemTask() {
        for (int i = 1; i < 10; i = i + 2) {
            int s = i * 30;
            String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&wq=%E6%89%8B%E6%9C%BA&click=0&page=" + i + "&s=" + s;
            String content = httpUtil.doGet(url);


            Document parse = Jsoup.parse(content);


            Elements elements = parse.select("div#J_goodsList");
            for (Element element : elements) {
                System.out.println(element);
            }




        }


    }
}
