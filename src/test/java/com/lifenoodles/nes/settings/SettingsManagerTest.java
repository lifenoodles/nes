package com.lifenoodles.nes.settings;

import junit.framework.TestCase;

import java.io.File;

/**
 * @author Donagh Hatton
 *         created on 03/09/2014.
 */
public class SettingsManagerTest extends TestCase {
    public void tearDown() {
        new SettingsManager("test").delete();
    }

    public void testCreateAndClear() {
        assertFalse(new File("test.cfg").exists());
        SettingsManager settings = new SettingsManager("test");
        assertTrue(new File("test.cfg").exists());
        settings.delete();
        assertFalse(new File("test.cfg").exists());
    }

    public void testNewManagerHasNoEntries() {
        assertFalse(new SettingsManager("test").contains("foo"));
        assertFalse(new SettingsManager("test").contains("bar"));
        assertFalse(new SettingsManager("test").contains("baz"));
    }

    public void testEntryAdd() {
        SettingsManager manager = new SettingsManager("test");
        assertFalse(manager.contains("foo"));
        manager.put("foo", "fooValue");
        assertTrue(manager.contains("foo"));
    }

    public void testEntryRemove() {
        SettingsManager manager = new SettingsManager("test");
        manager.put("foo", "fooValue");
        assertTrue(manager.contains("foo"));
        manager.remove("foo");
        assertFalse(manager.contains("foo"));
    }

    public void testEntryGet() {
        SettingsManager manager = new SettingsManager("test");
        manager.put("foo", "fooValue");
        assertTrue(manager.get("foo").orElse("").equals("foo"));
        assertFalse(manager.get("bar").isPresent());
    }

    public void testEntryPersists() {
        SettingsManager manager = new SettingsManager("test");
        manager.put("foo", "fooValue");
        SettingsManager otherManager = new SettingsManager("test");
        assertTrue(otherManager.contains("foo"));
        assertTrue(otherManager.get("foo").orElse("").equals("foo"));
    }
}
