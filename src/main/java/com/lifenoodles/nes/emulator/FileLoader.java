package com.lifenoodles.nes.emulator;

import com.lifenoodles.nes.exceptions.BadRomError;

import java.io.File;

/**
 * @author lifenoodles
 *         created on 06/06/2014.
 */

public class FileLoader {
    public static ROM loadROM(File file) throws BadRomError {
        return new ROM();
    }
}
