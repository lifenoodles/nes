package com.lifenoodles.nes.emulator;

import com.lifenoodles.nes.exceptions.BadRomException;

import java.io.*;

/**
 * @author lifenoodles
 *         created on 06/06/2014.
 */

public class FileLoader {


    private boolean isTrainerPresent(byte[] header) {
        assert header.length == 16;
        return (header[6] & 0b100 ) > 0;
    }

    public static ROM loadROM(File file) throws BadRomException, IOException {

        FileInputStream fr = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fr);

        if (!file.exists()) throw IOException;

        byte[] headerArray = new byte[16];

        int headerCode = bis.read(headerArray 0, 16);

        if headerCode != 16 throw BadRomException;

        // if  0-3: Constant $4E $45 $53 $1A ("NES" followed by MS-DOS end-of-file)
        if (headerArray[0] == 0x4E && headerArray[1] == 0x45 && headerArray[2] == 0x53 && headerArray[3] == 0x1A) {
            if (isTrainerPresent()) {
                byte[] trainerArray = new byte[512];
            } else {
                byte[] trainerArray = new byte[0];
            }
        }
        else {
            system.out.println("Incorrect byte values in header");
            throw BadRomException;
        }


        return new ROM();
    }


}
