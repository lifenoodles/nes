package com.lifenoodles.nes;

import com.lifenoodles.nes.ui.MainForm;
import org.lifenoodles.jargparse.HelpfulOptionParser;
import org.lifenoodles.jargparse.Option;
import org.lifenoodles.jargparse.OptionSet;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        HelpfulOptionParser parser = new HelpfulOptionParser()
                .setApplicationName("nes");
        parser.addOption(Option.optional("-r").alias("--rom").make());
        parser.addOption(Option.optional("-t").alias("--test").arguments(0)
                .make());
        parser.addOption(Option.optional("-d").alias("--debug").arguments(0)
                .make());
        OptionSet optionSet = parser.parse(args);

        if (optionSet.contains("--test")) {
            // do something testy
        } else {
            MainForm form = new MainForm();
            form.show();
        }
    }
}
