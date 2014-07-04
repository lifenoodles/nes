package com.lifenoodles.nes.emulator;

/**
 * Representation of loaded ROM
 */
public class ROM {
    private final Mapper mapper;
    public final byte[] header;
    public final byte[] prgRom;
    public final byte[] chrRom;

    public ROM(final byte[] header, final byte[] prgRom, final byte[] chrRom) {
        this.header = new byte[header.length];
        this.prgRom = new byte[prgRom.length];
        this.chrRom = new byte[chrRom.length];
        System.arraycopy(header, 0, this.header, 0, header.length);
        System.arraycopy(prgRom, 0, this.prgRom, 0, prgRom.length);
        System.arraycopy(chrRom, 0, this.chrRom, 0, chrRom.length);
        this.mapper = Mapper.fromCode(extractMapper(this.header));
    }

    private int extractMapper(final byte[] header) {
        final int lowerMapper = header[6] & 0xF0;
        final int upperMapper = header[7] & 0xF0;
        return (upperMapper << 4) | lowerMapper;
    }
}
