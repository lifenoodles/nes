package com.lifenoodles.nes.controller;

/**
 * @author Donagh Hatton
 *         created on 20/09/2014.
 */
public class TerminalController extends EmulatorController {
    private int cyclesPerSecond;
    private int stepRate;
    private long lastTick;

    public TerminalController() {
        this(1, 1000);
    }

    public TerminalController(final int cyclesPerSecond, final int stepRate) {
        this.cyclesPerSecond = cyclesPerSecond;
        this.stepRate = stepRate;
        this.lastTick = 0;
    }

    public synchronized TerminalController setCyclesPerSecond(
            int cyclesPerSecond) {
        this.cyclesPerSecond = cyclesPerSecond;
        return this;
    }

    public synchronized TerminalController setStepRate(int stepRate) {
        this.stepRate = stepRate;
        return this;
    }

    @Override
    public void run() {
        while (true) {
            final long now = System.currentTimeMillis();
            if (now - lastTick >= stepRate) {
                lastTick = now;
                if (isRunning()) {
                    getVirtualNes().executeCycles(cyclesPerSecond);
                    System.out.printf("%d: Cycle%s", now,
                            System.lineSeparator());
                } else {
                    System.out.printf("%d: Not running%s", now,
                            System.lineSeparator());
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                setRunning(false);
                break;
            }
        }
    }
}
