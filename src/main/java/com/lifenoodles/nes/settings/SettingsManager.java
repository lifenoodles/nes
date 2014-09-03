package com.lifenoodles.nes.settings;

import com.lifenoodles.nes.Main;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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



    public SettingsManager(String settingsIdentifier) {
        if (!settingsDir.exists()) {
            settingsDir.mkdir();
        }
        settingsFile = new File(settingsDir, settingsIdentifier);
        if (!settingsFile.exists()) {
            try {
                settingsFile.createNewFile();
            } catch (IOException e) {
                // Do something sensible here to handle errors
                e.printStackTrace();
            }
        }

        FileReader reader = null;
        try {
            reader = new FileReader(settingsFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineArray = line.trim().split(":");
                map.put(lineArray[0], lineArray[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clear the specific named setting from the settingsManager
     *
     * @param name the setting to remove
     */
    public void remove(final String name) {

    }

    /**
     * Return the string value for the setting requested or Empty
     *
     * @param setting the setting key
     * @return the setting value or empty
     */
    public Optional<String> get(String setting) {
        return Optional.empty();
    }

    /**
     * Delete the settings cache associated with this name
     */
    public void delete() {

    }

    /**
     * returns true if the setting exists
     *
     * @param setting the setting to check for
     * @return true if the setting exists
     */
    public boolean contains(String setting) {
        return false;
    }

    /**
     * Put this setting into the manager
     *
     * @param setting the setting to add
     * @param value the value of the setting
     */
    public void put(String setting, String value) {
    }

    //Files.exists(settings)
    //1. check if folder & file exits and if not, create & write test for that
    //2. read the file and pull out pairs into a map (file reader & buffered file reader)
    //3. method to check if a setting exits (hasSetting(), getSetting() & setSetting() ) write changes to file

    //4 Constructor takes a string (name of settings file in folder)


}
