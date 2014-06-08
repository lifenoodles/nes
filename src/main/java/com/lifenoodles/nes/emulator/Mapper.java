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
    NULL_MAPPER(0),
    MMC1(1),
    UNROM(2);

    private static Map<Integer, Mapper> map = new HashMap<>();
    private final int code;

    // set up the mapper map
    static {
        Arrays.stream(Mapper.values()).forEach(x -> map.put(x.code, x));
    }

    private Mapper(int code) {
        this.code = code;
    }

    /**
     * returns the correct Mapper enum from the given code, throws
     * IllegalArgumentException if that mapper does not exist
     * @param code
     * @return the mapper
     */
    public static Mapper fromCode(int code) {
        if (!map.containsKey(code)) {
            throw new IllegalArgumentException("Bad Mapper number.");
        }
        return map.get(code);
    }
}
