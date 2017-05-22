package com.muskteer.db.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class InOutUtil {

    public static InputStream readStreamFromFile(String path) {
        try {
            InputStream in = new FileInputStream(new File(path));
            return in;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
