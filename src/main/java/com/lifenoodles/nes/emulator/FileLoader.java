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
            throw new IOException("File does not exist.");
        }

        final FileInputStream fileInputStream = new FileInputStream(file);
        final BufferedInputStream bufferedInputStream =
                new BufferedInputStream(fileInputStream);

        final byte[] headerArray = new byte[16];
        final int headerBytesRead = bufferedInputStream.read(
                headerArray, 0, headerArray.length);
        if (headerBytesRead != 16) {
            throw new BadRomException("Incorrect header length.");
        }

        // if  0-3: Constant $4E $45 $53 $1A ("NES" followed by MS-DOS end-of-file)
        if (headerArray[0] == 0x4E &&
                headerArray[1] == 0x45 &&
                headerArray[2] == 0x53 &&
                headerArray[3] == 0x1A) {
        } else {
            throw new BadRomException("Incorrect byte values in header");
        }

        final byte[] trainerArray;
        if (isTrainerPresent(headerArray)) {
            trainerArray = new byte[512];
        } else {
            trainerArray = new byte[0];
        }

        final int trainerBytesRead = bufferedInputStream.read(
                trainerArray, 0, trainerArray.length);
        if (trainerBytesRead != trainerArray.length) {
            throw new BadRomException(String.format(
                    "Trainer in ROM is incorrect length, expected: %i, found: %i",
                    trainerArray.length, trainerBytesRead));
        }

        final byte[] prgRomArray = new byte[16384 * headerArray[4]];
        final int prgRomBytesRead = bufferedInputStream.read(
                prgRomArray, 0, prgRomArray.length);
        if (prgRomBytesRead != prgRomArray.length) {
            throw new BadRomException(String.format(
                    "PRGROM is incorrect length, expected: %i, found: %i",
                    prgRomArray.length, prgRomBytesRead));
        }

        final byte[] chrRomArray = new byte[8192 * headerArray[5]];
        final int chrBytesRead = bufferedInputStream.read(
                chrRomArray, 0, chrRomArray.length);

        if (chrBytesRead != chrRomArray.length) {
            throw new BadRomException(String.format(
                    "CHRROM is incorrect length, expected: %i, found: %i",
                    chrRomArray.length, chrBytesRead));
        }

        return new ROM(headerArray, prgRomArray, chrRomArray);
    }
}
