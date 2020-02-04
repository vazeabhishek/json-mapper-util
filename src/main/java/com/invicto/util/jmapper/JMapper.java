package com.invicto.util.jmapper;

import com.invicto.util.jmapper.cache.FileCache;
import com.invicto.util.jmapper.cache.MappingCache;
import com.invicto.util.jmapper.io.MappingDefiniationReader;

import java.io.FileNotFoundException;
import java.util.Optional;

public class JMapper {

    private MappingCache mappingCache;
    private final KeyUtil keyUtil = new KeyUtil();
    private final MappingDefiniationReader mappingDefiniationReader = new MappingDefiniationReader();

    public JMapper withMappingFile(String path) throws FileNotFoundException {
        Optional<MappingCache> cache = FileCache.find(path);
        if (cache.isPresent()) {
            mappingCache = cache.get();
        } else {
            mappingCache.addAll(mappingDefiniationReader.read(path));
            FileCache.put(path, mappingCache);
        }
        return this;
    }

    public String toTransformedJson() {
        return "";
    }





}
