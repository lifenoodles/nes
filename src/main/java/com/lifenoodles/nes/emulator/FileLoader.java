package com.lifenoodles.nes.emulator;

import com.lifenoodles.nes.exceptions.BadRomException;

import java.io.File;
import java.io.IOException;

/**
 * @author lifenoodles
 *         created on 06/06/2014.
 */

public class FileLoader {
    public static ROM loadROM(File file) throws BadRomException, IOException {
        return new ROM();
    }
}
