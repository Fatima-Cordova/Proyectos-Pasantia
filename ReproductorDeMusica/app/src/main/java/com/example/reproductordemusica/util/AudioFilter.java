package com.example.reproductordemusica.util;

import java.io.File;
import java.io.FileFilter;

public class AudioFilter implements FileFilter {

    private final String[] extensions = new String[] {"mp3", "ogg", "wav"};

    @Override
    public boolean accept(File file) {

        for (String extension : extensions) {
            if (file.getName().toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
}
