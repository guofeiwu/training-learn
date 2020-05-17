package com.crawer.demo.controller;

import com.crawer.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020/5/17 3:09 下午
 * @since v3.0
 */
@RestController
public class ItemController {


    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/item")
    public Object getItem() {
        return itemService.getItem(1, 30);
    }


    @GetMapping(value = "/webMagic")
    public Object webMagic() {
        return itemService.webMagic();
    }

    @GetMapping(value = "/weather")
    public Object weather() {
//        for (int i = 0; i < 1000; i++) {
//            itemService.weather();
//        }
        return itemService.weather();
    }
}
