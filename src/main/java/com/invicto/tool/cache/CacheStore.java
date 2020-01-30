package com.invicto.tool.cache;

import com.invicto.tool.io.MappingFileReader;

import java.util.Map;
import java.util.Objects;

public class CacheStore {

    private Map<String, String> mapperData;
    private MappingFileReader mappingFileReader;

    public CacheStore(MappingFileReader mappingFileReader) {
        this.mappingFileReader = mappingFileReader;
    }

    public Map<String, String> getMapperData() {
        if (Objects.isNull(mapperData))
            mapperData = mappingFileReader.readMappingFile();
        return mapperData;
    }

    public void setMapperData(Map<String, String> mapperData) {
        this.mapperData = mapperData;
    }
}
