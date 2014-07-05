package com.lifenoodles.nes.emulator;

import java.util.Arrays;

/**
 * Representation of loaded ROM
 */
public class ROM {
    private final Mapper mapper;
    public final int[] header;
    public final int[] prgRom;
    public final int[] chrRom;

    public ROM(final int[] header, final int[] prgRom, final int[] chrRom) {
        this.header = Arrays.stream(header).toArray();
        this.prgRom = Arrays.stream(prgRom).toArray();
        this.chrRom = Arrays.stream(chrRom).toArray();
        this.mapper = Mapper.fromCode(extractMapper(this.header));
    }

    private int extractMapper(final int[] header) {
        return ((header[7] & 0xF0) << 4) | (header[6] & 0xF0);
    }
}
