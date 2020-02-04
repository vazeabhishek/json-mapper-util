package com.invicto.util.jmapper.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MappingCache {
    private Map<String, String> cache = new ConcurrentHashMap<>();

    public void add(String key, String value) {
        cache.put(key, value);
    }

    public String get(String key) {
        return cache.get(key);
    }

    public void addAll(Map<String, String> map) {
        cache.putAll(map);
    }
}
