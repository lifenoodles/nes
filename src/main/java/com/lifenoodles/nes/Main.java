package com.lifenoodles.nes;

import com.lifenoodles.nes.ui.MainForm;
import com.lifenoodles.nes.ui.TerminalUi;
import org.lifenoodles.jargparse.AutoOptionParser;
import org.lifenoodles.jargparse.Option;
import org.lifenoodles.jargparse.OptionSet;

public class Main {
    public static void main(String[] args) {
        OptionSet optionSet = new AutoOptionParser("nes")
                .add(Option.of("-r", "--rom").arguments(1))
                .add(Option.of("-t", "--terminal"))
                .add(Option.of("-d", "--debug"))
                .parse(args);
        if (optionSet.contains("--terminal")) {
            TerminalUi ui = new TerminalUi();
            ui.begin();
        } else {
            MainForm form = new MainForm();
            form.show();
        }
    }
}
