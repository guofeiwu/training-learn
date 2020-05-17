package com.crawer.demo.service;

import java.util.HashMap;
import java.util.Map;

public class GlobalVarManager {
    private static Map<String, Object> object = new HashMap<>();

    public static Object getGlobalVars(String key) {
        return object.get(key);
    }

    public static void setGlobalVars(String key, Object value) {
        object.put(key, value);
    }
}