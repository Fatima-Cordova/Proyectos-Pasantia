package com.example.notas.util;

import com.example.notas.model.Notas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

    public String readFile(String path, String fileName) {
        File fileEvents = new File(path, fileName);
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileEvents));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) { }
        String result = text.toString();
        return result;
    }

    public ArrayList<Notas> getListFile(String path){
        ArrayList<Notas> list = new ArrayList<>();
        File directory = new File(path);
        File[] files = directory.listFiles();
        if (files.length > 0){
            for (int i = 0; i < files.length; i++){
                Notas nota = new Notas();
                nota.setId(Long.parseLong(files[i].getName()));
                list.add(nota);
            }
        }
        return list;
    }

    public void write(String path, String fileName, String addText){
        File file = new File(path, fileName);
        try {
            FileWriter writer = new FileWriter(file);
            writer.append(addText);
            writer.flush();
            writer.close();
        }
        catch (Exception e) {
        }
    }
}
