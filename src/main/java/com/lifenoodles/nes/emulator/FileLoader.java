package com.lifenoodles.nes.emulator;

import com.lifenoodles.nes.exceptions.BadRomException;

import java.io.*;

/**
 * @author lifenoodles
 *         created on 06/06/2014.
 */

public class FileLoader {
    private static final int HEADER_LENGTH = 16;
    private static final int TRAINER_LENGTH = 512;
    private static final int PRG_ROM_LENGTH = 16384;
    private static final int CHR_ROM_LENGTH = 8192;

    private static boolean isTrainerPresent(byte[] header) {
        assert header.length == HEADER_LENGTH;
        return (header[6] & 0b100) > 0;
    }

    /**
     * Determines if the header is good
     * @param header the header array
     * @return boolean indicating whether or not the header is good
     */
    private static boolean isGoodHeader(final byte[] header) {
        assert(header.length == 16);
        // if  0-3: Constant $4E $45 $53 $1A
        return header[0] == 0x4E && header[1] == 0x45 &&
                header[2] == 0x53 && header[3] == 0x1A;
    }

    public static ROM loadROM(File file) throws BadRomException, IOException {
        if (!file.exists()) {
            throw new IOException("File does not exist.");
        }

        final FileInputStream fileInputStream = new FileInputStream(file);
        final BufferedInputStream bufferedInputStream =
                new BufferedInputStream(fileInputStream);

        final byte[] headerArray = new byte[HEADER_LENGTH];
        final int headerBytesRead = bufferedInputStream.read(
                headerArray, 0, headerArray.length);
        if (headerBytesRead != HEADER_LENGTH) {
            throw new BadRomException("Incorrect header length.");
        }

        if (!isGoodHeader(headerArray)) {
            throw new BadRomException("Incorrect byte values in header");
        }

        final byte[] trainerArray = isTrainerPresent(headerArray)
                ? new byte[TRAINER_LENGTH] : new byte[0];
        final int trainerBytesRead = bufferedInputStream.read(
                trainerArray, 0, trainerArray.length);
        if (trainerBytesRead != trainerArray.length) {
            throw new BadRomException(String.format(
                    "Trainer in ROM is incorrect length, expected: %d, found: %d",
                    trainerArray.length, trainerBytesRead));
        }

        final byte[] prgRomArray = new byte[PRG_ROM_LENGTH * headerArray[4]];
        final int prgRomBytesRead = bufferedInputStream.read(
                prgRomArray, 0, prgRomArray.length);
        if (prgRomBytesRead != prgRomArray.length) {
            throw new BadRomException(String.format(
                    "PRGROM is incorrect length, expected: %d, found: %d",
                    prgRomArray.length, prgRomBytesRead));
        }

        final byte[] chrRomArray = new byte[CHR_ROM_LENGTH * headerArray[5]];
        final int chrBytesRead = bufferedInputStream.read(
                chrRomArray, 0, chrRomArray.length);

        if (chrBytesRead != chrRomArray.length) {
            throw new BadRomException(String.format(
                    "CHRROM is incorrect length, expected: %d, found: %d",
                    chrRomArray.length, chrBytesRead));
        }

        return new ROM(headerArray, prgRomArray, chrRomArray);
    }
}
