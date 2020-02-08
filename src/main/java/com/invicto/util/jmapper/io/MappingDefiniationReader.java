/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.invicto.util.jmapper.io;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;


/**
 * An Object to read contents of a file and creates hash map out of it
 */
public class MappingDefiniationReader {

    private Gson gson;


    /** Constructor
     * @param gson
     */
    public MappingDefiniationReader(Gson gson) {
        this.gson = gson;
    }

    /**
     * Read the contents of the and creates a hashmap out of it.
     * @param path
     * @return Map
     * @throws FileNotFoundException
     */
    public Map<String, String> read(String path) throws FileNotFoundException {
        return gson.fromJson(new FileReader(path), Map.class);
    }

    /**Reads the contents of the file and creats hashmap out of it
     * @param fileName
     * @return Map
     * @throws FileNotFoundException
     */
    public Map<String, String> read(File fileName) throws FileNotFoundException {
        return gson.fromJson(new FileReader(fileName), Map.class);
    }

}
