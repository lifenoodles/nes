package com.lifenoodles.nes.emulator;

import com.lifenoodles.nes.exceptions.BadRomException;
import junit.framework.TestCase;

import java.io.File;
import java.io.IOError;
import java.io.IOException;

public class FileLoaderTest extends TestCase {
    public void testNoFileExists() {
        File noFile = new File("not-a-file");
        try {
            FileLoader.loadROM(noFile);
            fail("IOException not thrown.");
        } catch (Exception e) {
            assertTrue("IOException not thrown.",
                    e instanceof IOException);
        }
    }

    public void testBadRom() {
        File badRom = new File(".gitignore");
        try {
            FileLoader.loadROM(badRom);
            fail("BadRomException not thrown.");
        } catch (Exception e) {
            assertTrue("BadRomException not thrown.",
                    e instanceof BadRomException);
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
