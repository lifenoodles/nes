package com.lifenoodles.nes.ui;

import com.lifenoodles.nes.controller.TerminalController;

import java.util.*;

/**
 * Provides a menu driven interface to control and monitor the execution
 * of the emulator via a terminal
 *
 * @author Donagh Hatton
 *         created on 20/09/2014.
 */

public class TerminalUi {
    private boolean isRunning = true;
    private ArrayList<TerminalController> terminalControllers =
            new ArrayList<>();
    private Optional<Integer> activeController = Optional.empty();
    private Scanner scanner = new Scanner(System.in);

    public void begin() {
        printStatus();
        System.out.println("Please enter a command or press 'h' for help");
        while (isRunning) {
            handleInput();
        }
    }

    private void printHelp() {
        System.out.println("h|help -> Show this message");
        System.out.println("s|set x -> Set active controller to x");
    }

    private void printStatus() {
        System.out.printf("Controllers: %d%s", terminalControllers.size(),
                System.lineSeparator());
        System.out.printf("Active Controller: %s%s",
                activeController.map(String::valueOf).orElse("None"),
                System.lineSeparator());
    }

    private void handleInput() {
        final String command = scanner.nextLine();
        if (command.matches(Command.QUIT.pattern)) {
            System.out.println("Exiting");
            isRunning = false;
        } else if (command.matches(Command.HELP.pattern)) {
            printHelp();
        } else if (command.matches(Command.STATUS.pattern)) {
            printStatus();
        } else {
            System.out.println("Unrecognised command");
        }
    }

    enum Command {
        QUIT("q(uit)?"),
        HELP("h(elp)?"),
        STATUS("s(tatus)?");

        public final String pattern;

        private Command(final String pattern) {
            this.pattern = pattern;
        }
    }
}
