package com.lifenoodles.nes.emulator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Enum for all supported mappers
 * @author Donagh Hatton
 *         created on 08/06/2014.
 */
public enum Mapper {
    MMC1(1),
    UNROM(2);

    private static Map<Integer, Mapper> map = new HashMap<>();
    private final int code;

    // set up the mapper map
    static {
        Arrays.stream(Mapper.values()).map(x -> map.put(x.code, x));
    }

    private Mapper(int code) {
        this.code = code;
    }
}
