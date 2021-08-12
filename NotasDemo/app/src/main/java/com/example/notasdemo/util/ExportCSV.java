package com.example.notasdemo.util;

import android.content.Context;
import android.os.Environment;

import com.example.notasdemo.RoomBD;
import com.example.notasdemo.model.Note;
import com.example.notasdemo.model.UserWithNote;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExportCSV {

    private List<Note> listaNotas = new ArrayList<>();
    private File directorio;
    private final String SEPARADOR = ", ";
    private final String FILE_EXPORT = "file_export.csv";

    public ExportCSV(Context context, List<Note> notas) {
        listaNotas = notas;
        directorio = new File(context.getExternalFilesDir(""), Environment.DIRECTORY_DOCUMENTS);
    }

    private boolean prepareDirectory() {
        boolean isOk = false;
        try {
            if(directorio.exists()) {
                isOk = true;
            } else {
                isOk = directorio.mkdir();
            }
        } catch (Exception exception) {
            isOk = false;
        }
        return isOk;
    }

    public void createCSV() {
        if(prepareDirectory()) {
            try {
                FileWriter fileWriter = new FileWriter(directorio.getAbsolutePath() + "/" + System.currentTimeMillis() + FILE_EXPORT);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                //Cabecera
                bufferedWriter.write("\ufeff");
                bufferedWriter.write(generateHeader());
                bufferedWriter.newLine();

                //Cuerpo
                bufferedWriter.write(generateBody());

                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

        }
    }

    private String generateHeader() {
        return "id" + SEPARADOR + "nota" + SEPARADOR + "idUser" ;
    }

    private String generateBody() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Note note: listaNotas) {
            stringBuilder.append(note.getID());
            stringBuilder.append( SEPARADOR);
            stringBuilder.append(note.getText());
            stringBuilder.append( SEPARADOR);
            stringBuilder.append(note.getIdUser());
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
