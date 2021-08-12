package com.example.reproductordemusica.util;

import android.content.Context;
import android.os.Environment;

import com.example.reproductordemusica.model.Audio;

import java.io.File;
import java.util.ArrayList;

public class FileManager {

    private File myDirectory;
    private Context context;

    public FileManager(Context context) {
        myDirectory = new File(context.getExternalFilesDir(""), Environment.DIRECTORY_MUSIC);
    }

    public boolean initialize() {
        if(myDirectory.exists()) {
            return true;
        } else {
            return myDirectory.mkdir();
        }
    }

    public ArrayList<Audio> getAllAudio() {
        File[] output = myDirectory.listFiles(new AudioFilter());
        ArrayList<Audio> audios = new ArrayList();

        if(output.length > 0) {
            for (File audio : output) {
                audios.add(new Audio(audio.getName(), audio.getAbsolutePath()));
            }
        }
        return audios;
    }
}