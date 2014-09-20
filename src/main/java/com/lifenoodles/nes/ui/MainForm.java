package com.lifenoodles.nes.ui;

import com.lifenoodles.nes.controller.LwjglController;

import javax.swing.*;
import java.awt.*;

/**
 * Main Form for the emulator, contains a menu bar and a canvas
 * which contains an embedded lwjgl display
 * @author Donagh Hatton
 *         created on 06/06/2014.
 */
public class MainForm {
    private JFrame frame = new JFrame("NubNES");
    private Canvas renderCanvas = new Canvas();
    private LwjglController lwjglController = new LwjglController(renderCanvas);
    private Thread controllerThread = new Thread(lwjglController);

    public MainForm() {
        setupMenu();
        // frame config
        renderCanvas.setPreferredSize(new Dimension(256, 240));
        frame.add(renderCanvas);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Display the MainForm and initialise associated LwjglController
     * @return this
     */
    public MainForm show() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        lwjglController.setRunning(true);
        controllerThread.start();
        return this;
    }

    /**
     * Initialise the default MenuBar for this form
     * @return this
     */
    private MainForm setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new JMenu("File"));
        frame.setJMenuBar(menuBar);
        return this;
    }
}
