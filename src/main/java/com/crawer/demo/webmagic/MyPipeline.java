package com.crawer.demo.webmagic;

import com.crawer.demo.service.GlobalVarManager;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.Map;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020/5/17 8:24 下午
 * @since v3.0
 */
public class MyPipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Map<String, Object>> allInfo = resultItems.get("allInfo");
        GlobalVarManager.setGlobalVars("jobInfo", allInfo);
    }
}
