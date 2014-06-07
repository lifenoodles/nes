package com.lifenoodles.nes.controller;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.awt.*;

/**
 * @author lifenoodles
 *         created on 07/06/2014.
 */
public class LwjglController implements Runnable {
    Canvas parent;
    public LwjglController(Canvas parent) {
        this.parent = parent;
    }

    @Override
    public void run() {
        setup();
        while (!Display.isCloseRequested()) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
            Display.update();
            Display.sync(60);
        }
        cleanup();
    }

    private void setup() {
        //initialise LWJGL to use this canvas
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
