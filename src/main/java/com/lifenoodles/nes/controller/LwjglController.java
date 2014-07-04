package com.lifenoodles.nes.controller;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.awt.*;

/**
 * EmulatorController implementation based on LWJGL for rendering and timing
 * @author Donagh Hatton
 *         created on 07/06/2014.
 */
public class LwjglController extends EmulatorController {
    private final int instructionsPerFrame;
    private final int targetFrameRate;
    private final Canvas parent;

    public LwjglController(final int instructionsPerFrame, final int targetFrameRate,
                           final Canvas parent) {
        this.instructionsPerFrame = instructionsPerFrame;
        this.targetFrameRate = targetFrameRate;
        this.parent = parent;
    }

    public LwjglController(final Canvas parent) {
        this(1, 60, parent);
    }

    /**
     * Launch this controller thread. Initialises the render window
     * correctly and begin an infinite loop of execution control.
     * VNES instructions will not execute until a ROM has been
     * loaded and the setRunning(boolean) method has been invoked
     */
    public void run() {
        setup();
        while (!Display.isCloseRequested()) {
            update();
            display();
            // Display.sync will sleep this thread until the correct time
            // has passed to ensure a frame rate of it's argument
            Display.sync(targetFrameRate);
        }
        cleanup();
    }

    private void update() {
        if (isRunning()) {
            getVirtualNes().executeCycles(instructionsPerFrame);
        }
    }

    private void display() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        Display.update();
    }

    private void setup() {
        try {
            Display.setParent(parent);
            Display.setVSyncEnabled(true);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    private void cleanup() {
        Display.destroy();
    }
}
