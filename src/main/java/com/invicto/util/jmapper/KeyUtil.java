package com.invicto.util.jmapper;

class KeyUtil {

    private final String DOT = ".";

    public String findFirstKey(String key) {
        return key.substring(0, key.indexOf(DOT));
    }

    public String findRemainingEndPartOfKey(String key) {

        return key.substring(key.indexOf(DOT) + 1);
    }

    public String findLastKey(String key) {

        return key.substring(key.lastIndexOf(DOT) + 1);
    }

    public String findRemainingFirstPartOfKey(String key) {

        return key.substring(0, key.lastIndexOf(DOT));
    }
}
