package com.invicto.tool;

import com.invicto.tool.enviroment.EnviromentReader;

import java.util.HashMap;
import java.util.Map;

public class TestClass {

    private static HashMap<String,String> map = new HashMap<>();

    static {
        map.put("k1","dk1");
        map.put("k2","dk1.k2");
        map.put("k3","dk1.k3");
        map.put("k4","dk2");
        map.put("k5","dk1.k2.s2");
        map.put("k6","dk1.da.ff");
        map.put("k7","dk1.hh");
    }

    public static void main(String[] args) {
      Map<String,Object> stringObjectMap = createSourceData();
      Map<String,Object> destinationMap = new HashMap<>();
        for (String key:stringObjectMap.keySet()) {
            if(map.containsKey(key)){
                String destinationKey = map.get(key);
                if(destinationMap.containsKey(destinationKey)){
                    Map<String,Object> iMap = (Map<String, Object>) destinationMap.get(destinationKey);
                    addToMap(iMap,findWhateverLeftAfterRemovingFirstSubPart(destinationKey),stringObjectMap.get(key));
                }
                else
                {
                    destinationMap.putAll(buildMap(destinationKey,stringObjectMap.get(key)));
                }
            }
            else
            {
                HashMap<String,Object> innerObject = new HashMap<>();
                innerObject.put(key,stringObjectMap.get(key));
                destinationMap.put("UNMAPPED_FIELDS",innerObject);
            }
        }

    }


    private static Map<String,Object> createSourceData(){
        Map<String,Object> sourceData = new HashMap<>();
        sourceData.put("k1","a");
        sourceData.put("k2","b");
        sourceData.put("k3","c");
        sourceData.put("k4","d");
        sourceData.put("k5","e");
        sourceData.put("k6","e");
        sourceData.put("k7","f");
        sourceData.put("k8","g");
        sourceData.put("k9","h");
        return sourceData;
    }


    private static void addToMap(Map<String, Object> map, String key, Object value) {
        if (key.contains(".")) {
            String first = findFirstSubPartOfKey(key);
            if (map.containsKey(first)) {
                addToMap((Map<String, Object>) map.get(first), findWhateverLeftAfterRemovingFirstSubPart(key), value);
            } else {
                map.put(first, buildMap(findWhateverLeftAfterRemovingFirstSubPart(key),value));
            }
        }
        else
            map.put(key,value);
    }

    private static Map<String, Object> buildMap(String key, Object value) {
        Map<String, Object> imap = new HashMap<>();
        if (key.contains(".")) {
            String lastKey = findLastSubPartOfKey(key);
            imap.put(lastKey, value);
            return buildMap(findWhateverLeftAfterRemovingFirstSubPart(key), imap);
        } else {
            imap.put(key, value);
            return imap;
        }
    }

    private static String findFirstSubPartOfKey(String key) {
        String first = key.substring(0, key.indexOf("."));
        return first;
    }

    private static String findLastSubPartOfKey(String key) {
        String last = key.substring(key.lastIndexOf(".") + 1);
        return last;
    }

    private static String findWhateverLeftAfterRemovingFirstSubPart(String key) {
        String last = key.substring(key.indexOf(".") + 1);
        return last;
    }
}

