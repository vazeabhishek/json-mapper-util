package com.invicto.util.jmapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class KeyUtilTests {

    private KeyUtil keyUtil = new KeyUtil();

    @Test
    public void testFindFirstKey(){
        String value = "key.innerKey.furtherInnerKey";
        String expected = "key";
        String actual = keyUtil.findFirstKey(value);
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void testFindLastKey(){
        String value = "key.innerKey.furtherInnerKey";
        String expected = "furtherInnerKey";
        String actual = keyUtil.findLastKey(value);
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void testFindRemainingEndPartOfKey(){
        String value = "key.innerKey.furtherInnerKey";
        String expected = "innerKey.furtherInnerKey";
        String actual = keyUtil.findRemainingEndPartOfKey(value);
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void testFindRemainingFirstPartOfKey(){
        String value = "key.innerKey.furtherInnerKey";
        String expected = "key.innerKey";
        String actual = keyUtil.findRemainingFirstPartOfKey(value);
        Assert.assertEquals(expected,actual);
    }

}
