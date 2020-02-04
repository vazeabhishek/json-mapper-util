package com.invicto.util.jmapper.io;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RunWith(JUnit4.class)
public class MappingDefinitionReaderTests {

    private MappingDefiniationReader mappingDefiniationReader = new MappingDefiniationReader();

    @Test
    public void testForFilePath() throws FileNotFoundException {
        Map<String, String> expectedData = createExpectedMap();
        Map<String, String> actualData = mappingDefiniationReader.read("C:\\Users\\Lucifer\\idea-workspace\\loyalty-connect\\jsontransformerutil\\src\\test\\resources\\test-mapping.json");
        Assert.assertEquals(expectedData, actualData);
    }


    @Test
    public void testForClassPath() throws FileNotFoundException {
        Map<String, String> expectedData = createExpectedMap();
        Map<String, String> actualData = mappingDefiniationReader.read("src\\test\\resources\\test-mapping.json");
        Assert.assertEquals(expectedData, actualData);

    }

    @Test
    public void testForFile() throws FileNotFoundException {
        Map<String, String> expectedData = createExpectedMap();
        File file = new File("C:\\Users\\Lucifer\\idea-workspace\\loyalty-connect\\jsontransformerutil\\src\\test\\resources\\test-mapping.json");
        Map<String, String> actualData = mappingDefiniationReader.read(file);
        Assert.assertEquals(expectedData, actualData);
    }

    private Map<String, String> createExpectedMap() {
        Map<String, String> expectedData = new HashMap<>();
        expectedData.put("key1", "value1");
        expectedData.put("key2", "value2");
        expectedData.put("key3", "value3");
        expectedData.put("key4", "value4");
        return expectedData;

    }
}
