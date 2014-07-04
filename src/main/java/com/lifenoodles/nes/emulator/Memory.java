package com.lifenoodles.nes.emulator;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Class to represent an arbitrary slice of NES memory,
 * allows for read/write mirroring to be set up via registering addresses that should resolve
 * to the same base address
 *
 * Created by donagh on 7/4/14.
 */
public class Memory {
    private final int[] array;
    private final int[] readMirror;
    private final int[] writeMirror;

    public Memory(final int size) {
        array = new int[size];
        readMirror = IntStream.range(0, array.length - 1).toArray();
        writeMirror = Arrays.stream(readMirror).toArray();
    }

    public Memory write(final int address, final int value) {
        assert(writeMirror.length > address);
        assert(value < 256);
        array[writeMirror[address]] = value;
        return this;
    }

    public int read(final int address) {
        assert(readMirror.length > address);
        return array[readMirror[address]];
    }

    public Memory registerReadMirror(final int address, final int ... mirroredAddress) {
        assert(readMirror.length > address);
        Arrays.stream(mirroredAddress).forEach(x -> readMirror[x] = address);
        return this;
    }

    public Memory registerWriteMirror(final int address, final int ... mirroredAddress) {
        assert (writeMirror.length > address);
        Arrays.stream(mirroredAddress).forEach(x -> writeMirror[x] = address);
        return this;
    }
}
