package com.guofei.wu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020/5/16 3:55 下午
 * @since v3.0
 */
public class AnalysisFile {

    @Test
    public void getKeyWords() {
        try {
            // 读取文件
            Path path = Paths.get("/Users/mason/Desktop/keywordsAndyears.txt");
            List<String> lines = Files.readAllLines(path);

            // 总的文件
            List<List<String>> list = new ArrayList<>();
            // 每个文件分组
            List<String> strings = new ArrayList<>();
            for (String line : lines) {
                if (!line.equals("") && line.length() > 0) {
                    strings.add(line);
                } else {
                    list.add(strings);
                    strings = new ArrayList<>();
                }
            }

            // 总的对象
            List<Map<String, Object>> keysWords = new ArrayList<>();
            // 总的关键字
            List<String> allKeyWords = new ArrayList<>();

            for (List<String> subList : list) {
                // 每个段落对象
                Map<String, String> map = new HashMap<>();
                String key = "";
                for (String line : subList) {
                    if (line.equals("") || line.length() <= 0) {
                        continue;
                    }
                    String newKey = line.substring(0, 2);
                    String content = "";
                    if (newKey.trim().equals("")) {
                        content = map.get(key);
                        content += line;
                    } else {
                        key = line.substring(0, 2);
                        content = line.substring(2).trim();
                    }
                    map.put(key, content);
                }
                List<String> keys = new ArrayList<>();
                String de = map.get("DE");
                String id = map.get("ID");
                // 还有其他的东西可以通过map.get(前缀)获取，看你的需求 做另外的分析;
                if (de != null && !"".equals(de)) {
                    String[] split = de.split(";");
                    keys.addAll(Stream.of(split).map(s -> s.trim().toLowerCase()).collect(Collectors.toList()));
                }
                if (id != null && !"".equals(id)) {
                    String[] split = id.split(";");
                    keys.addAll(Stream.of(split).map(s -> s.trim().toLowerCase()).collect(Collectors.toList()));
                }
                // 所有的关键字
                allKeyWords.addAll(keys);
                Map<String, Object> keyWord = new HashMap<>();
                keyWord.put("keyWord", keys);

                // 年份可能在py中，也可能在ea中
                String year = map.get("PY");
                if (year == null || "".equals(year)) {
                    String ea = map.get("EA");
                    // EA APR 2020
                    year = ea.substring(3).trim();
                }
                keyWord.put("year", year);
                keysWords.add(keyWord);
            }

            /**
             * 我的思路是先不管年份，统计各关键字数量，找出前二十，然后按照年份分类，分别找出这二十个关键字分别在不同年份的次数
             */
            Map<String, List<String>> keyGroup = allKeyWords.stream().collect(Collectors.groupingBy(key -> key));
            ArrayList<Map.Entry<String, List<String>>> entries = new ArrayList<>(keyGroup.entrySet());
            List<Map.Entry<String, List<String>>> sortKeyGroup = entries.stream()
                .sorted(Comparator.comparing(en -> ((Map.Entry<String, List<String>>) en).getValue().size()).reversed()).collect(Collectors.toList());
            List<Map<String, Object>> retList = new ArrayList<>();
            for (Map.Entry<String, List<String>> stringListEntry : sortKeyGroup) {
                String key = stringListEntry.getKey();
                Map<String, Object> map = new HashMap<>(2);
                // 关键字名称
                map.put("keyName", key);
                // 关键词出现的总次数
                map.put("keyCount", stringListEntry.getValue().size());
                List<String> years = new ArrayList<>();
                for (Map<String, Object> keysWord : keysWords) {
                    if (((List<String>) keysWord.get("keyWord")).contains(key)) {
                        Object year = keysWord.get("year");
                        if (year != null) {
                            years.add((String) keysWord.get("year"));
                        }
                    }
                }
                Map<String, Long> collect = years.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
                // 关键字在各个年份出现的次数
                map.put("keyYear", collect);
                retList.add(map);
            }
            // System.out.println(retList);
            //  写入到文件中
            Path write = Paths.get("/Users/mason/Desktop/keyWords.txt");
            long start = System.currentTimeMillis();
            System.out.println("正在写入文件...开始时间为：" + start);
            int index = 1;
            for (Map<String, Object> ret : retList) {
                HashMap<String, Long> keyYear = (HashMap<String, Long>) ret.get("keyYear");
                // 年份排序一下
                List<String> keys = keyYear.keySet().stream().sorted(Comparator.comparing(y -> Integer.parseInt((String) y)).reversed())
                    .collect(Collectors.toList());
                StringBuilder sb = new StringBuilder();
                for (String key : keys) {
                    sb.append("年份：" + key + ",次数：" + keyYear.get(key) + "\n");
                }
                String content = "第" + (index++) +
                    "次\n" + "关键字：" + ret.get("keyName") + "\n出现的次数：" + ret.get("keyCount") + "\n" + sb.toString() + "\n";
                try {
                    Files.write(write, content.getBytes(), StandardOpenOption.APPEND);
                } catch (IOException e) {
                    System.out.println("写入文件出现异常...");
                }
            }
            System.out.println("写入文件成功...耗时：" + (System.currentTimeMillis() - start) + "毫秒");
        } catch (Exception e) {
            System.out.println("出现异常..");
        }
    }
}
