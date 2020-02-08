package com.invicto.util.jmapper;


/**
 * Am exception to indicate the key is not found while the transformation process
 */
public class KeyNotFoundException extends RuntimeException {

    /**
     * Constructor
     * @param message
     */
    public KeyNotFoundException(String message) {
        super(message);
    }
}
