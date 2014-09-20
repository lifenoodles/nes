package com.lifenoodles.nes.controller;

import com.lifenoodles.nes.emulator.VirtualNes;

/**
 * Base class for anything that controls emulator execution.
 * The intention is that the controller can manage instruction execution
 * so that the apparent speed of the emulation is correct.
 *
 * @author Donagh Hatton
 *         created on 08/06/2014.
 */
public abstract class EmulatorController implements Runnable {
    private final VirtualNes virtualNes;
    private boolean isRunning;

    public EmulatorController() {
        virtualNes = new VirtualNes();
        setRunning(false);
    }

    /**
     * @return a boolean indicating whether the controlled VNES is running
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Start or stop the controlled VNES
     *
     * @param isRunning boolean
     */
    public synchronized void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * @return the VirtualNes instance being controlled
     */
    public VirtualNes getVirtualNes() {
        return virtualNes;
    }
}

