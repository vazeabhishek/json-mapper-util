package com.invicto.util.jmapper.io;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class MappingDefiniationReader {
    private Gson gson = new Gson();

    public Map<String, String> read(String path) throws FileNotFoundException {
        return gson.fromJson(new FileReader(path), Map.class);
    }

    public Map<String, String> read(File fileName) throws FileNotFoundException {
        return gson.fromJson(new FileReader(fileName), Map.class);
    }

}
