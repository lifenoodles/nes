package com.lifenoodles.nes.ui;

import javax.swing.*;

/**
 * @author lifenoodles
 *         created on 06/06/2014.
 */
public class MainForm {
    private JFrame frame = new JFrame("NubNES");

    // auto generated identifiers
    private JPanel MainPanel;

    public MainForm() {
        frame.setContentPane(MainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
    }

    public MainForm show() {
        frame.setVisible(true);
        return this;
    }
}
