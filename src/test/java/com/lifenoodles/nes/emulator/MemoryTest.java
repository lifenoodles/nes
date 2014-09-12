package com.lifenoodles.nes.emulator;

import junit.framework.TestCase;

/**
 * @author Donagh Hatton
 *         created on 7/4/14.
 */
public class MemoryTest extends TestCase {
    public void testReadWrite() {
        final Memory memory = new Memory(100);
        memory.write(10, (byte) 255);
        assertTrue(memory.read(10) == (byte) 255);
    }

    public void testWriteMirroring() {
        final Memory memory = new Memory(100);
        memory.registerWriteMirror(10, 20, 30, 40);
        memory.write(40, (byte) 255);
        assertTrue(memory.read(10) == (byte) 255);
        memory.write(30, (byte) 127);
        assertTrue(memory.read(10) == (byte) 127);
        memory.write(20, (byte) 63);
        assertTrue(memory.read(10) == (byte) 63);
    }

    public void testReadMirroring() {
        final Memory memory = new Memory(100);
        memory.registerReadMirror(15, 30, 45, 60);
        memory.write(15, (byte) 255);
        assertTrue(memory.read(30) == (byte) 255);
        assertTrue(memory.read(45) == (byte) 255);
        assertTrue(memory.read(60) == (byte) 255);
    }

    public void testWriteIllegalAddress() {
        final Memory memory = new Memory(100);
        try {
            memory.write(100, (byte) 255);
            fail("No assertion error thrown.");
        } catch (AssertionError e) {
            //pass
        }
    }

    public void testReadIllegalAddress() {
        final Memory memory = new Memory(100);
        try {
            memory.read(100);
            fail("No assertion error thrown.");
        } catch (AssertionError e) {
            //pass
        }
    }
}
