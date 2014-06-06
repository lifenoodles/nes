package com.lifenoodles.nes.emulator;

import com.lifenoodles.nes.exceptions.BadRomError;
import junit.framework.TestCase;

import java.io.File;
import java.io.IOError;

public class FileLoaderTest extends TestCase {
    public void testNoFileExists() {
        File noFile = new File("not-a-file");
        try {
            FileLoader.loadROM(noFile);
            fail("IOException not thrown.");
        } catch (IOError e) {
            assertTrue(true);
        } catch (BadRomError e) {
            assertTrue(false);
        }
    }

    public void testBadRom() {
        File badRom = new File(".gitignore");
        try {
            FileLoader.loadROM(badRom);
            fail("BadRomException not thrown.");
        } catch (IOError e) {
            assertTrue(false);
        } catch (BadRomError e) {
            assertTrue(true);
        }
    }

    public void testLoadROM() {
        File nesFile = new File("real-nes-file");
        try {
            FileLoader.loadROM(nesFile);
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
