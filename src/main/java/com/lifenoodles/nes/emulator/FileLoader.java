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

        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream =
                new BufferedInputStream(fileInputStream);

        byte[] headerArray = new byte[16];
        int headerCode = bufferedInputStream.read(headerArray, 0, headerArray.length);

        if (headerCode != 16) {
            throw new BadRomException();
        }

        byte[] trainerArray = new byte[0];
        // if  0-3: Constant $4E $45 $53 $1A ("NES" followed by MS-DOS end-of-file)
        if (headerArray[0] == 0x4E &&
                headerArray[1] == 0x45 &&
                headerArray[2] == 0x53 &&
                headerArray[3] == 0x1A) {
            if (isTrainerPresent(headerArray)) {
                trainerArray = new byte[512];
            }
            int trainerData = bufferedInputStream.read(trainerArray, 0, trainerArray.length);

            if (trainerData != trainerArray.length) {
                throw new BadRomException();
            }
            byte[] prgArray = new byte[16384 * headerArray[4]];

            int prgData = bufferedInputStream.read(prgArray, 0, prgArray.length);

            if (prgData != prgArray.length) {
                throw new BadRomException();
            }
            byte[] chrArray = new byte[8192 * headerArray[5]];

            int chrData = bufferedInputStream.read(chrArray, 0, chrArray.length);

            if (chrData != chrArray.length) {
                throw new BadRomException();
            }

        } else {
            throw new BadRomException("Incorrect byte values in header.");
        }
        return new ROM();
    }
}
