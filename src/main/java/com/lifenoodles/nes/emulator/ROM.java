package com.lifenoodles.nes.emulator;

/**
 * Representation of loaded ROM
 */
public class ROM {
    private Mapper mapper;
    public byte[] header;
    public byte[] prgRom;
    public byte[] chrRom;


    public ROM()
    {

    }

    public ROM(byte[] header, byte[] prgRom, byte[] chrRom) {
        this.header = new byte[header.length];
        this.prgRom = new byte[prgRom.length];
        this.chrRom = new byte[chrRom.length];
        System.arraycopy(header, 0, this.header, 0, header.length);
        System.arraycopy(prgRom, 0, this.prgRom, 0, prgRom.length);
        System.arraycopy(chrRom, 0, this.chrRom, 0, chrRom.length);
        this.mapper = Mapper.fromCode(extractMapper(this.header));
    }

    private int extractMapper(byte[] header) {
        int lowerMapper = header[6] & 0xF0;
        int upperMapper = header[7] & 0xF0;
        return (upperMapper << 4) | lowerMapper;
    }
}
