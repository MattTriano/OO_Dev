package org.bitbucket.mtriano;

/**
 * Exception Class for invalid data or parameters
 */
public class InvalidDataException extends Exception {

    public InvalidDataException(String msg) {
        super(msg);
    }
}
