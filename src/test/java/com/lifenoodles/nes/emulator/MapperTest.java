package com.lifenoodles.nes.emulator;

import junit.framework.TestCase;

/**
 * Unit tests for the Mapper
 * @author Donagh Hatton
 *         created on 08/06/2014.
 */
public class MapperTest extends TestCase {
    public void testReturnsCorrectMapper() {
        assertTrue("Incorrect mapper returned.",
                Mapper.fromCode(1) == Mapper.MMC1);
    }

    public void testBadMapper() {
        try {
            Mapper.fromCode(Integer.MAX_VALUE);
            fail("Exception not thrown.");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
}
