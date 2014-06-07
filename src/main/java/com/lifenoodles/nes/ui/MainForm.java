package com.lifenoodles.nes.ui;

import com.lifenoodles.nes.controller.LwjglController;

import javax.swing.*;
import java.awt.*;

/**
 * @author lifenoodles
 *         created on 06/06/2014.
 */
public class MainForm {
    private JFrame frame = new JFrame("NubNES");
    private Canvas renderCanvas = new Canvas();
    private LwjglController lwjglController =
            new LwjglController(renderCanvas);

    public MainForm() {
        // set up menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new JMenu("File"));
        frame.setJMenuBar(menuBar);

        // frame config
        frame.add(renderCanvas, BorderLayout.CENTER);
        frame.setPreferredSize(new Dimension(256, 240));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public MainForm show() {
//        frame.getContentPane().setMinimumSize(new Dimension(250, 250));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        lwjglController.run();
        return this;
    }
}
