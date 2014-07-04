package com.lifenoodles.nes.emulator;

/**
 * Main class for representing the NES h/w, contains methods for managing
 * execution flow of the cpu and provides connectivity for modules
 * @author Donagh Hatton
 *         created on 08/06/2014.
 */
public class VirtualNes {
    private final CPU cpu;
    private ROM rom;

    public VirtualNes() {
        this.cpu = new CPU();
    }

    public VirtualNes(ROM rom) {
        this();
        this.rom = rom;
    }

    public ROM getRom() {
        return rom;
    }

    /**
    * Execute the specified number of cycles on the CPU
    *
    * @param cycles the number of cycles to execute
    * @return this
    */
    public VirtualNes executeCycles(final int cycles) {
        return this;
    }
}
