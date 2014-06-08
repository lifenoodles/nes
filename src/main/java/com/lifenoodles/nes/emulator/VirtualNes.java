package com.lifenoodles.nes.emulator;

/**
 * Main class for representing the NES h/w, contains methods for managing
 * execution flow of the cpu and provides connectivity for modules
 * @author Donagh Hatton
 *         created on 08/06/2014.
 */
public class VirtualNes {
    /**
     * Execute the specified number of cycles on the CPU
     *
     * @param cycles the number of cycles to execute
     * @return this
     */
    public VirtualNes executeCycles(int cycles) {
        return this;
    }
}
