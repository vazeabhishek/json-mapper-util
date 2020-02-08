package com.invicto.util.jmapper;

import com.google.gson.Gson;
import com.invicto.util.jmapper.io.MappingDefiniationReader;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * An Object that maps one json string to another by considering a mapping definition which
 * can be provided by a file or a Map
 */
public class JMapper {

    private final Map<String, String> mappingDef;
    private final KeyUtil keyUtil = new KeyUtil();
    private final Gson gson = new Gson();
    private final MappingDefiniationReader mappingDefiniationReader = new MappingDefiniationReader(gson);

    /**
     * Constructor to construct object, takes path as input parameter and
     * reads the contents of file and build a mpping definition out of it
     * Throws exception if file not found
     * @param path
     * @throws FileNotFoundException
     */
    public JMapper(String path) throws FileNotFoundException {
        this.mappingDef = this.mappingDefiniationReader.read(path);
    }


    /**
     * Constructor to construct object, takes a map as a input and uses it as a
     * mapping definition for transforming json objects
     * @param mappingDef
     */
    public JMapper(Map<String, String> mappingDef) {
        this.mappingDef = mappingDef;
    }

    /**
     * Takes json string as a input and transforms it into another json structure
     * @param jsonString
     * @return
     */
    public String toTransformedJson(String jsonString) {
        Map<String, Object> sourceMap = gson.fromJson(jsonString, Map.class);
        Map<String, Object> targetMap = mapToNewMap(mappingDef, sourceMap);
        return gson.toJson(targetMap);
    }

    /**
     * Takes an input key, parses the map for that key and fetches the associated value  from map,
     * searches that key in mapping defination and fetches the target key from the map
     * add the value to the target map with the target key
     * @param key
     * @param map
     * @param newValue
     */
    private void addToMap(String key, Map<String, Object> map, Object newValue) {
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
                throw new KeyNotFoundException("At " + key);
        } else
            map.put(key, newValue);
    }

    /**
     * Takes an input key, parses the map for that key and fetches the associated value  from
     * @param key
     * @param map
     * @return
     */
    private Object parseMap(String key, Map<String, Object> map) {
        if (key.contains(".")) {
            String innerKey = keyUtil.findFirstKey(key);
            if (map.containsKey(innerKey)) {
                if (map.get(innerKey) instanceof Map)
                    return parseMap(keyUtil.findRemainingEndPartOfKey(key), (Map<String, Object>) map.get(innerKey));
                else
                    return map.get(innerKey);
            } else
                throw new KeyNotFoundException("At " + key);
        }
        return map.get(key);
    }

    /**
     * build a map for a input key and the value
     * @param key
     * @param value
     * @return
     */
    private Map<String, Object> buildMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        if (key.contains(".")) {
            map.put(keyUtil.findFirstKey(key), buildMap(keyUtil.findRemainingEndPartOfKey(key), value));
        } else
            map.put(key, value);
        return map;
    }

    /**
     * transform structure of one map into other based on the mapping defination.
     * @param mappingDef
     * @param sourceMap
     * @return
     */
    private Map<String, Object> mapToNewMap(Map<String, String> mappingDef, Map<String, Object> sourceMap) {
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
