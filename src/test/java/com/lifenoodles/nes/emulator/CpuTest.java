package com.lifenoodles.nes.emulator;

import junit.framework.TestCase;

import java.util.Arrays;

/**
 * @author Donagh Hatton
 *         created on 7/4/14.
 */
public class CpuTest extends TestCase {
    public void testCpuStatusFlags() {
        final CPU cpu = new CPU();
        Arrays.stream(CPU.StatusFlag.values()).forEach(flag -> {
            cpu.setStatusFlag(flag, false);
            assertFalse(cpu.isStatusFlagSet(flag));
            cpu.setStatusFlag(flag, true);
            assertTrue(cpu.isStatusFlagSet(flag));
            cpu.setStatusFlag(flag, false);
            assertFalse(cpu.isStatusFlagSet(flag));
        });
    }
}
