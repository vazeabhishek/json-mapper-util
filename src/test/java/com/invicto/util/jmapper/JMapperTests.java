package com.invicto.util.jmapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.FileNotFoundException;

@RunWith(JUnit4.class)
public class JMapperTests {

    private JMapper jMapper;

    @Test
    public void testToTransformedJson() throws Exception {
        String input = "{\n" +
                "  \"key1\": \"hello\",\n" +
                "  \"key2\": \"abhishek\",\n" +
                "  \"key3\": \"samual\",\n" +
                "  \"key4\": { \"Master\" :  \"kallu\",\n" +
                "            \"dino\" :  [\"val\",\"val2\",\"val3\"]\n" +
                "          }\n" +
                "}";
        jMapper = new JMapper("src\\test\\resources\\test-mapping.json");
        System.out.println(jMapper.toTransformedJson(input));
    }

    @Test
    public void testToTransformedForJson() throws Exception {
        String input = "{\n" +
                "  \"skey5\": \"hello\",\n" +
                "  \"skey2\": \"abhishek\",\n" +
                "  \"skey3\": \"samual\",\n" +
                "  \"skey4\": { \"Master\" :  \"kallu\",\n" +
                "            \"dino\" :  [\"val\",\"val2\",\"val3\"]\n" +
                "          }\n" +
                "}";
        jMapper = new JMapper("src\\test\\resources\\test-mapping.json");
        System.out.println(jMapper.toTransformedJson(input));
    }

}
