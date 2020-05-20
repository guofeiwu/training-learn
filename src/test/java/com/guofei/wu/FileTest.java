package com.guofei.wu;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * @Author Mason
 * @Description
 * @Date 2020/5/9 15:22
 **/

public class FileTest {

    public static boolean isAcronym(String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test1() {
        List<String> keys = new ArrayList<>();
        keys.add("PT ");
        keys.add("AU ");
        keys.add("AF ");
        keys.add("TI ");
        keys.add("SO ");
        keys.add("LA ");

        /**
         * PT J
         * AU Bouchery, Y
         * AF Bouchery, Yann
         * TI Identifying the market areas of port-centric logistics and hinterland
         * SO EUROPEAN JOURNAL OF OPERATIONAL RESEARCH
         * LA English
         * DT Article
         * DE Logistics; Hinterland intermodal transportation; Port-centric
         * ID FREIGHT TRANSPORTATION; NETWORK DESIGN; MODE CHOICE; TIME RELIABILITY;
         * AB Many port authorities have developed ambitious strategies to foster hinterland intermoda
         * C1 [Bouchery, Yann] KEDGE Business Sch, Ctr Excellence Supply Chain CESIT, 680 Tours Liberat, F-33400 Talence, France.
         * EM yann.bouchery@kedgebs.com
         * RI Fransoo, Jan/A-1135-2011
         * OI Fransoo, Jan/0000-0001-7220-0851
         * FU Normandie Region under the FLUIDE project
         * FX Part of this work was supported by research grants from the Normandie
         * CR Agarwal R, 2010, OPER RES, V58, P1726, DOI 10.1287/opre.1100.0848
         * fafafafafa
         * NR 90
         * TC 0
         * Z9 0
         * U1 0
         * U2 0
         * PU ELSEVIER
         * PI AMSTERDAM
         * PA RADARWEG 29, 1043 NX AMSTERDAM, NETHERLANDS
         * SN 0377-2217
         * EI 1872-6860
         * J9 EUR J OPER RES
         * JI Eur. J. Oper. Res.
         * PD SEP 1
         * PY 2020
         * VL 285
         * IS 2
         * BP 599
         * EP 611
         * DI 10.1016/j.ejor.2020.02.015
         * PG 13
         * WC Management; Operations Research & Management Science
         * SC Business & Economics; Operations Research & Management Science
         * GA LF2VV
         * UT WOS:000527281600013
         * DA 2020-05-09
         * ER
         */
        try {
            Path path = Paths.get("C:\\Users\\Mason\\Desktop\\savedrecs (1).txt");
            List<String> lines = Files.readAllLines(path);

            List<List<String>> list = new ArrayList<>();
            List<String> strings = new ArrayList<>();

            for (String line : lines) {
                if (!line.equals("")) {
                    strings.add(line);
                } else {
                    list.add(strings);
                    strings = new ArrayList<>();
                }
            }

            List<Map<String, String>> maps = new ArrayList<>();

            for (List<String> subList : list) {
                Map<String, String> map = new HashMap<>();
                String key = "";
                for (String line : subList) {
                    String newKey = line.substring(0, 2);
                    String content="";
                    if(newKey.equals("")){
                        content += line;
                    }else {
                        key = line.substring(0, 2);
                        content = line.substring(2);
                    }
                    map.put(key, content);
                }
                maps.add(map);
            }
            System.out.println(maps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
