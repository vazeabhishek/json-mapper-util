package com.invicto.util.jmapper.cache;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class FileCache {

    private static Map<String, MappingCache> fileCache = new ConcurrentHashMap<>();

    public static Optional<MappingCache> find(String fileName) {
        return Optional.ofNullable(fileCache.get(fileName));
    }

    public static void put(String fileName, MappingCache value) {
        fileCache.put(fileName, value);
    }
}
