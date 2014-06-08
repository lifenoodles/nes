package com.lifenoodles.nes.settings;

import com.lifenoodles.nes.Main;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

/**
 * Manager for settings files
 *
 * @author Cian Hatton
 *         created on 08/06/2014.
 */
public class SettingsManager {
    private File settingsDir = new File("Settings");
    private File settingsFile;
    private Map<String, String> map = new HashMap<>();



    public SettingsManager(String settingsIdentifier) throws IOException {
        if (!settingsDir.exists()) {
            settingsDir.mkdir();
        }
        settingsFile = new File(settingsDir, settingsIdentifier);
        if (!settingsFile.exists()) {

            settingsFile.createNewFile();


        }

        FileReader reader = new FileReader(settingsFile);

        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] lineArray = line.trim().split(":");
            map.put(lineArray[0], lineArray[1]);
        }
    }

    //Files.exists(settings)
    //1. check if folder & file exits and if not, create & write test for that
    //2. read the file and pull out pairs into a map (file reader & buffered file reader)
    //3. method to check if a setting exits (hasSetting(), getSetting() & setSetting() ) write changes to file

    //4 Constructor takes a string (name of settings file in folder)


}
