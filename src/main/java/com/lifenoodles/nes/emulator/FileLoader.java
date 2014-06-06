package com.lifenoodles.nes.emulator;

import com.lifenoodles.nes.exceptions.BadRomException;

import java.io.*;

/**
 * @author lifenoodles
 *         created on 06/06/2014.
 */

public class FileLoader {


    private static boolean isTrainerPresent(byte[] header) {
        assert header.length == 16;
        return (header[6] & 0b100) > 0;
    }

    public static ROM loadROM(File file) throws BadRomException, IOException {
        if (!file.exists()) {
            throw new IOException();
        }

        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream =
                new BufferedInputStream(fileInputStream);

        byte[] headerArray = new byte[16];
        int headerCode = bufferedInputStream.read(headerArray, 0, 16);

        if (headerCode != 16) {
            throw new BadRomException();
        }

        // if  0-3: Constant $4E $45 $53 $1A ("NES" followed by MS-DOS end-of-file)
        if (headerArray[0] == 0x4E &&
                headerArray[1] == 0x45 &&
                headerArray[2] == 0x53 &&
                headerArray[3] == 0x1A) {
            if (isTrainerPresent(headerArray)) {
                byte[] trainerArray = new byte[512];
            } else {
                byte[] trainerArray = new byte[0];
            }
        } else {
            throw new BadRomException("Incorrect byte values in header.");
        }
        return new ROM();
    }
}
