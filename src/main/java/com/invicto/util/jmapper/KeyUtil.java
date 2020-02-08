package com.invicto.util.jmapper;


/**
 * Class to find out substrings.
 */
class KeyUtil {

    private final String DOT = ".";


    /**
     * Takes input string and finds out a substring till the first occurance of .
     * @param key
     * @return String
     */
    public String findFirstKey(String key) {
        return key.substring(0, key.indexOf(DOT));
    }
    /**
     * Takes input string and finds out a substring till after the first occurance of .
     * @param key
     * @return String
     */

    public String findRemainingEndPartOfKey(String key) {

        return key.substring(key.indexOf(DOT) + 1);
    }

}
