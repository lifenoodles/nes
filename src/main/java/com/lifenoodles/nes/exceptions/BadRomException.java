package com.lifenoodles.nes.exceptions;

/**
 * @author lifenoodles
 *         created on 06/06/2014.
 */
public class BadRomException extends Exception {
    public BadRomException() {}

    public BadRomException(String message) {
        super(message);
    }
}