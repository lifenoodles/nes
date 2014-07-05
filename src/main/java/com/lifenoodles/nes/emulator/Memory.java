package com.lifenoodles.nes.emulator;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Class to represent an arbitrary slice of NES memory,
 * allows for read/write mirroring to be set up via registering addresses that
 * should resolve to the same base address
 *
 * Created by donagh on 7/4/14.
 */
public class Memory {
    private final int[] array;
    private final int[] readMirror;
    private final int[] writeMirror;

    /**
     * @param size The size in bytes of the memory region
     */
    public Memory(final int size) {
        array = new int[size];
        readMirror = IntStream.range(0, array.length - 1).toArray();
        writeMirror = Arrays.stream(readMirror).toArray();
    }

    /**
     * Writes a byte to the memory at the given address
     * @param address the address to write to, less than size of the Memory
     * @param value the value to write 0 <= value <= 255
     * @return this
     */
    public Memory write(final int address, final int value) {
        assert(writeMirror.length > address);
        assert(value < 256);
        array[writeMirror[address]] = value;
        return this;
    }

    /**
     * Read a byte from the memory
     * @param address the address to read from, less than the size of the Memory
     * @return the byte read
     */
    public int read(final int address) {
        assert(readMirror.length > address);
        return array[readMirror[address]];
    }

    /**
     * Register one or more address as read mirroring. Reads from these
     * addresses will return the value in the mirrored address.
     * @param address base address to mirror reads from
     * @param mirroredAddress list of address that should be mirrored from
     *                        the base address
     * @return this
     */
    public Memory registerReadMirror(final int address,
                                     final int ... mirroredAddress) {
        assert(readMirror.length > address);
        Arrays.stream(mirroredAddress).forEach(x -> readMirror[x] = address);
        return this;
    }

    /**
     * Register one or more address as write mirroring. Writes to these
     * addresses will write to the value in the mirrored address.
     * @param address base address to mirror write to
     * @param mirroredAddress list of address that should mirror writes to the
     *                        base address
     * @return this
     */
    public Memory registerWriteMirror(final int address,
                                      final int ... mirroredAddress) {
        assert (writeMirror.length > address);
        Arrays.stream(mirroredAddress).forEach(x -> writeMirror[x] = address);
        return this;
    }
}
