package com.invicto.util.jmapper;

import com.google.gson.Gson;
import com.invicto.util.jmapper.io.MappingDefiniationReader;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JMapper {

    private final Map<String, String> mappingDef;
    private final KeyUtil keyUtil = new KeyUtil();
    private final MappingDefiniationReader mappingDefiniationReader = new MappingDefiniationReader();
    private final Gson gson = new Gson();

    public JMapper(String path) throws FileNotFoundException {
        this.mappingDef = this.mappingDefiniationReader.read(path);
    }

    public JMapper(Map<String, String> mappingDef) {
        this.mappingDef = mappingDef;
    }

    public String toTransformedJson(String jsonString) throws Exception {
        Map<String, Object> sourceMap = gson.fromJson(jsonString, Map.class);
        Map<String, Object> targetMap = mapToNewMap(mappingDef, sourceMap);
        return gson.toJson(targetMap);
    }

    private void addToMap(String key, Map<String, Object> map, Object newValue) throws Exception {
        if (key.contains(".")) {
            String innerKey = keyUtil.findFirstKey(key);
            if (map.containsKey(innerKey)) {
                if (map.get(innerKey) instanceof Map)
                    addToMap(keyUtil.findRemainingEndPartOfKey(key), (Map<String, Object>) map.get(innerKey), newValue);
                else {
                    if (key.contains("."))
                        map.putAll(buildMap(key, newValue));
                    else
                        map.put(innerKey, newValue);
                }
            } else
                throw new Exception();
        } else
            map.put(key, newValue);
    }

    private Object parseMap(String key, Map<String, Object> map) throws Exception {
        if (key.contains(".")) {
            String innerKey = keyUtil.findFirstKey(key);
            if (map.containsKey(innerKey)) {
                if (map.get(innerKey) instanceof Map)
                    return parseMap(keyUtil.findRemainingEndPartOfKey(key), (Map<String, Object>) map.get(innerKey));
                else
                    return map.get(innerKey);
            } else
                throw new Exception("Invalid Key");
        }
        return map.get(key);
    }

    private Map<String, Object> buildMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        if (key.contains(".")) {
            map.put(keyUtil.findFirstKey(key), buildMap(keyUtil.findRemainingEndPartOfKey(key), value));
        } else
            map.put(key, value);
        return map;
    }

    private Map<String, Object> mapToNewMap(Map<String, String> mappingDef, Map<String, Object> sourceMap) throws Exception {
        Map<String, Object> targetMap = null;
        for (String sourceKey : sourceMap.keySet()) {
            if (mappingDef.containsKey(sourceKey)) {
                Object value = parseMap(sourceKey, sourceMap);
                if (Objects.isNull(targetMap)) {
                    targetMap = buildMap(mappingDef.get(sourceKey), value);
                } else
                    addToMap(mappingDef.get(sourceKey), targetMap, value);
            }
        }
        return targetMap;

    }

}
