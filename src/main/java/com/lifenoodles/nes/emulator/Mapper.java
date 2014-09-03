package com.lifenoodles.nes.emulator;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Enum for all supported mappers
 *
 * @author Donagh Hatton
 *         created on 08/06/2014.
 */
public enum Mapper {
    NULL_MAPPER(0),
    MMC1(1),
    UNROM(2);

    private final static Map<Integer, Mapper> map;
    private final int code;

    // set up the mapper map
    static {
        map = Arrays.stream(Mapper.values())
                .collect(Collectors.toMap(x -> x.code, Function.identity()));
    }

    private Mapper(final int code) {
        this.code = code;
    }

    /**
     * returns the correct Mapper enum from the given code, throws
     * IllegalArgumentException if that mapper does not exist
     *
     * @param code mapper code
     * @return the mapper
     */
    public static Mapper fromCode(final int code) {
        if (!map.containsKey(code)) {
            throw new IllegalArgumentException("Bad Mapper number.");
        }
        return map.get(code);
    }
}
