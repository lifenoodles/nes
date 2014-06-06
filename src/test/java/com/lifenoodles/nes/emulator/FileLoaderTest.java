package com.lifenoodles.nes.emulator;

import com.lifenoodles.nes.exceptions.BadRomError;
import junit.framework.TestCase;

import java.io.File;

public class FileLoaderTest extends TestCase {
    public void testLoadROM() {
        File nesFile = new File("nes.iws");
        if (!nesFile.exists()) {
            assertTrue(false);
        }
        try {
            FileLoader.loadROM(nesFile);
        } catch (BadRomError e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }
}
