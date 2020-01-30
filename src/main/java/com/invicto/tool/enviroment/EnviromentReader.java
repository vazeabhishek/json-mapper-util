package com.invicto.tool.enviroment;

import java.util.Objects;

public class EnviromentReader {

    private String jMapperConfigFileLocation = "JMAPPER-CONFIG-LOC";

    public void readEnviromentConfiguration() {
        if (Objects.nonNull(System.getenv(jMapperConfigFileLocation))) { // VM arguments has highest Value
            System.setProperty("com.invicto.jsonmapper.config.file.location", System.getenv(jMapperConfigFileLocation));
        }
    }
}
