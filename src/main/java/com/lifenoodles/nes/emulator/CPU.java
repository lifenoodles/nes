package com.lifenoodles.nes.emulator;

/**
 * Data class containing the CPU specific registers etc.
 * Most logic is not handled here, as it requires access to a lot of global state
 * Created by EricMcC on 05/06/2014.
 */

public class CPU {
    private int programCounter;
    private int stackPointer;
    private int processorStatus;
    private int accumulator;
    private int indexX;
    private int indexY;

    public int getProgramCounter() {
        return programCounter;
    }

    public CPU setProgramCounter(final int programCounter) {
        this.programCounter = programCounter;
        return this;
    }

    public int getStackPointer() {
        return stackPointer;
    }

    public CPU setStackPointer(final int stackPointer) {
        this.stackPointer = stackPointer;
        return this;
    }

    public int getProcessorStatus() {
        return processorStatus;
    }

    public CPU setProcessorStatus(final int processorStatus) {
        this.processorStatus = processorStatus;
        return this;
    }

    public int getAccumulator() {
        return accumulator;
    }

    public CPU setAccumulator(final int accumulator) {
        this.accumulator = accumulator;
        return this;
    }

    public int getIndexX() {
        return indexX;
    }

    public CPU setIndexX(final int indexX) {
        this.indexX = indexX;
        return this;
    }

    public int getIndexY() {
        return indexY;
    }

    public CPU setIndexY(final int indexY) {
        this.indexY = indexY;
        return this;
    }

    /**
     * Determine if the given status flag is set
     *
     * @param flag the flag to check
     * @return the status of the flag
     */
    public boolean isStatusFlagSet(final StatusFlag flag) {
        return (processorStatus & (0x80 >> flag.bitPosition)) > 0;
    }

    /**
     * Set a given status CPU status flag
     *
     * @param flag   the flag to set
     * @param status the new status for the flag
     * @return this
     */
    public CPU setStatusFlag(final StatusFlag flag, final boolean status) {
        if (status) {
            processorStatus |= (0x80 >> flag.bitPosition);
        } else {
            processorStatus &= ~(0x80 >> flag.bitPosition) & 0xFF;
        }
        return this;
    }

    public enum StatusFlag {
        CARRY_FLAG(0),
        ZERO_FLAG(1),
        INTERRUPT_DISABLE_FLAG(2),
        DECIMAL_MODE_FLAG(3),
        BREAK_FLAG(4),
        UNUSED_FLAG(5),
        OVERFLOW_FLAG(6),
        NEGATIVE_FLAG(7);

        public final int bitPosition;

        private StatusFlag(final int bitPosition) {
            this.bitPosition = bitPosition;
        }
    }
}
