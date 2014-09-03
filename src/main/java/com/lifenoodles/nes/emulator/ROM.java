package com.lifenoodles.nes.emulator;

/**
 * Representation of loaded ROM
 *
 * @author Donagh Hatton
 *         created on 07/04/2014.
 */
public class ROM {
    public final int[] header;
    public final int[] prgRom;
    public final int[] chrRom;
    private final Mapper mapper;

    public ROM(final byte[] header, final byte[] prgRom, final byte[] chrRom) {
        this.header = new int[header.length];
        for (int i = 0; i < header.length; ++i) {
            this.header[i] = header[i];
        }
        this.prgRom = new int[prgRom.length];
        for (int i = 0; i < prgRom.length; ++i) {
            this.prgRom[i] = prgRom[i];
        }
        this.chrRom = new int[chrRom.length];
        for (int i = 0; i < chrRom.length; ++i) {
            this.chrRom[i] = chrRom[i];
        }
        this.mapper = Mapper.fromCode(extractMapper(this.header));
    }

    private int extractMapper(final int[] header) {
        return ((header[7] & 0xF0) << 4) | (header[6] & 0xF0);
    }
}
